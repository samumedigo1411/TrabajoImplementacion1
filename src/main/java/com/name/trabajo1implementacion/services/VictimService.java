package com.name.trabajo1implementacion.services;

import com.name.trabajo1implementacion.entities.Victim;
import com.name.trabajo1implementacion.repositories.VictimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VictimService {
    @Autowired
    private VictimRepository victimRepository;

    // Usamos @Transactional para asegurar la integridad de los datos en la BD
    @Transactional(readOnly = true)
    public List<Victim> obtenerCasosDeFamiliasRicas(String apellido) {
        List<Victim> victimas = victimRepository.findByLineage(apellido);

        if (victimas.isEmpty()) {
            throw new RuntimeException("No hay reportes de crímenes para la familia " + apellido);
        }
        return victimas;
    }
    @Transactional(readOnly = true)
    public List<Victim> listarTodasLasVictimas() {
        return victimRepository.findAll();
    }
    @Transactional
    public Victim registrarNuevaVictima(Victim victim) {
        if (victim.getName() == null || victim.getName().isEmpty()) {
            throw new RuntimeException("Error: El nombre de la víctima es obligatorio para el expediente.");
        }
        return victimRepository.save(victim);
    }
    @Transactional(readOnly = true)
    public Victim buscarPorId(Long id) {
        return victimRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Víctima no encontrada con el ID: " + id));
    }
}