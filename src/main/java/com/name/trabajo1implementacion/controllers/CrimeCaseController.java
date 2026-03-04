package com.name.trabajo1implementacion.controllers;

import com.name.trabajo1implementacion.dto.CrimeCaseDTO;
import com.name.trabajo1implementacion.entities.CrimeCase;
import com.name.trabajo1implementacion.services.CrimeCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cases")
public class CrimeCaseController {

    @Autowired
    private CrimeCaseService crimeCaseService;

    @GetMapping("/unsolved")
    @PreAuthorize("hasRole('DETECTIVE')")
    public List<CrimeCaseDTO> getUnsolvedCases() {
        return crimeCaseService.obtenerCasosAbiertos()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{caseNumber}")
    @PreAuthorize("hasAnyRole('DETECTIVE', 'ADMIN')")
    public CrimeCaseDTO getCaseByNumber(@PathVariable String caseNumber) {
        CrimeCase entity = crimeCaseService.buscarPorNumeroExpediente(caseNumber);
        return convertToDTO(entity);
    }

    @PostMapping("/new")
    @PreAuthorize("hasRole('ADMIN')")
    public CrimeCaseDTO createCase(@RequestBody CrimeCase nuevoCaso) {
        return convertToDTO(crimeCaseService.crearNuevoCaso(nuevoCaso));
    }

    private CrimeCaseDTO convertToDTO(CrimeCase entity) {
        return new CrimeCaseDTO(
                entity.getId(),
                entity.getCaseNumber(),
                entity.getDescription(),
                entity.isSolved(),
                entity.getCreatedAt(),
                entity.getVictim() != null ? 1 : 0
        );
    }
}