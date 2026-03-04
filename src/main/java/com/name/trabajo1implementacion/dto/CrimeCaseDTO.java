package com.name.trabajo1implementacion.dto;

import java.time.LocalDateTime;

public record CrimeCaseDTO(
        Long id,
        String caseNumber,
        String description,
        boolean isResolved,
        LocalDateTime createdAt,
        int victimCount
) {}