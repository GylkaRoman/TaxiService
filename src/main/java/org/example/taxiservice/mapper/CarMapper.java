package org.example.taxiservice.mapper;

import org.example.taxiservice.dto.car.CarRequestDTO;
import org.example.taxiservice.dto.car.CarResponseDTO;
import org.example.taxiservice.model.Car;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {
    public Car toEntity(CarRequestDTO dto) {
        if (dto == null) return null;
        Car car = new Car();
        car.setModel(dto.getModel());
        car.setCarPlate(dto.getCarPlate());
        car.setTaxiType(Enum.valueOf(org.example.taxiservice.model.TaxiType.class, dto.getTaxiType()));
        return car;
    }

    public CarResponseDTO toDTO(Car car) {
        if (car == null) return null;
        CarResponseDTO dto = new CarResponseDTO();
        dto.setId(car.getId());
        dto.setModel(car.getModel());
        dto.setCarPlate(car.getCarPlate());
        dto.setTaxiType(car.getTaxiType().name());
        return dto;
    }
}
