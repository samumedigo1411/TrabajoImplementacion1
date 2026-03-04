package com.name.trabajo1implementacion.controllers;

import com.name.trabajo1implementacion.entities.Victim;
import com.name.trabajo1implementacion.services.VictimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/victims")
public class VictimController {
    @Autowired
    private VictimService victimService;
    // Obtener todas las víctimas, solo para detective o admin
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('DETECTIVE', 'ADMIN')")
    public List<Victim> getAllVictims() {
        return victimService.listarTodasLasVictimas();
    }
    // Buscar víctimas por linaje
    @GetMapping("/family/{apellido}")
    @PreAuthorize("hasRole('DETECTIVE')")
    public List<Victim> getVictimsByFamily(@PathVariable String apellido) {
        return victimService.obtenerCasosDeFamiliasRicas(apellido);
    }
    // Registrar una nueva víctima hallada
    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public Victim registerVictim(@RequestBody Victim victim) {
        return victimService.registrarNuevaVictima(victim);
    }
    // Buscar una víctima específica por su ID
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('DETECTIVE', 'ADMIN')")
    public Victim getVictimById(@PathVariable Long id) {
        return victimService.buscarPorId(id);
    }
}