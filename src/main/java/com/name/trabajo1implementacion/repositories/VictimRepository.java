package com.name.trabajo1implementacion.repositories;

import com.name.trabajo1implementacion.entities.Victim;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VictimRepository extends JpaRepository<Victim, Long> {

    // Método 1: Para filtrar por las familias
    List<Victim> findByLineage(String lineage);

    // Método 2: Para buscar pistas
    List<Victim> findByCrimeSceneDetailsContaining(String keyword);
}