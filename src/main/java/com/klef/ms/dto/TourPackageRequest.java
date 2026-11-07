package com.klef.ms.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TourPackageRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Type is required")
    private String type;

    @NotBlank(message = "Status is required")
    private String status;

    @NotBlank(message = "Location is required")
    private String location;

}