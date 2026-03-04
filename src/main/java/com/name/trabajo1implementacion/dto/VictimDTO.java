package com.name.trabajo1implementacion.dto;

import java.util.UUID;

public record VictimDTO(
        UUID id,
        String name,
        String lineage,
        String causeOfDeath,
        String status
) {}
