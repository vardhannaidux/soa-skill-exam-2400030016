package com.klef.ms.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TourPackageResponse {

    private Long tourPackageId;

    private String name;

    private String type;

    private String status;

    private String location;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}