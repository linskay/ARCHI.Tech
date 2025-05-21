package com.coworking.dto;

import com.coworking.model.Workspace;
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
public class WorkspaceDTO {
    @NotNull(message = "Идентификатор рабочего пространства обязателен")
    private UUID workspaceId;

    @NotBlank(message = "Название рабочего пространства обязательно")
    @Size(max = 100, message = "Название не должно превышать 100 символов")
    private String name;

    private String description;

    @NotNull(message = "Цена за час обязательна")
    @DecimalMin(value = "0.0", inclusive = false, message = "Цена за час должна быть больше 0")
    private Double pricePerHour;

    @NotNull(message = "Статус рабочего пространства обязателен")
    private Workspace.WorkspaceStatus status;
}
