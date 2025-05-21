package com.coworking.dto;

import com.coworking.model.ParkingSpace;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSpaceDTO {
    @NotNull(message = "Идентификатор парковочного места обязателен")
    private UUID parkingSpaceId;

    @NotBlank(message = "Номер парковочного места обязателен")
    @Size(min = 1, max = 10, message = "Номер парковочного места должен содержать от 1 до 10 символов")
    private String spaceNumber;

    @NotNull(message = "Цена за день обязательна")
    @DecimalMin(value = "0.0", inclusive = false, message = "Цена за день должна быть больше 0")
    private Double pricePerDay;

    @NotNull(message = "Статус парковочного места обязателен")
    private ParkingSpace.ParkingSpaceStatus status;
}
