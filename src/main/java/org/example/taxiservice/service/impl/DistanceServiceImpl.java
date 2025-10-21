package org.example.taxiservice.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.taxiservice.service.DistanceService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Locale;

@Service
public class DistanceServiceImpl implements DistanceService {

    private final WebClient webClient = WebClient.create();
    private final ObjectMapper mapper = new ObjectMapper();

    // Вспомогательный record для хранения координат
    private record Location(double lat, double lon) {}

    // Получение координат по адресу через Nominatim API
    private Location getCoordinates(String address) throws Exception {
        String url = UriComponentsBuilder
                .fromUriString("https://nominatim.openstreetmap.org/search")
                .queryParam("q", address + ", Moldova") // уточняем страну
                .queryParam("format", "json")
                .queryParam("limit", 1)
                .build()
                .toUriString();

        String response = webClient.get()
                .uri(url)
                .header("User-Agent", "TaxiService/1.0 (roman.gulica@example.com)")
                .retrieve()
                .bodyToMono(String.class)
                .block();

        if (response == null || response.isEmpty()) {
            throw new IllegalStateException("Пустой ответ от Nominatim API");
        }

        JsonNode arr = mapper.readTree(response);
        if (!arr.isArray() || arr.isEmpty()) {
            throw new IllegalArgumentException("Не удалось найти координаты для: " + address);
        }

        double lat = arr.get(0).get("lat").asDouble();
        double lon = arr.get(0).get("lon").asDouble();
        return new Location(lat, lon);
    }

    // Получение расстояния в км между двумя адресами
    @Override
    public double getDistanceInKm(String from, String to) throws Exception {
        Location locFrom = getCoordinates(from);
        Location locTo = getCoordinates(to);

        // Формируем URL для OSRM с использованием точки как десятичного разделителя
        String routeUrl = String.format(Locale.US,
                "https://router.project-osrm.org/route/v1/driving/%f,%f;%f,%f?overview=false",
                locFrom.lon, locFrom.lat, locTo.lon, locTo.lat
        );

        String response = webClient.get()
                .uri(routeUrl)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        if (response == null || response.isEmpty()) {
            throw new IllegalStateException("Пустой ответ от OSRM API");
        }

        JsonNode root = mapper.readTree(response);

        if (!root.has("code") || !root.get("code").asText().equals("Ok")) {
            throw new RuntimeException("Ошибка маршрута: " + root.toPrettyString());
        }

        double distanceMeters = root.get("routes").get(0).get("distance").asDouble();
        return Math.round(distanceMeters / 10.0) / 100.0; // округляем до сотых
    }
}
