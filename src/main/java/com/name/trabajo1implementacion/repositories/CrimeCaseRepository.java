package com.name.trabajo1implementacion.repositories;

import com.name.trabajo1implementacion.entities.CrimeCase;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CrimeCaseRepository extends JpaRepository<CrimeCase, Long> {

    // Método para buscar todos los casos que aún no han sido resueltos
    List<CrimeCase> findBySolvedFalse();
    // Método para buscar el caso por el número de caso
    CrimeCase findByCaseNumber(String caseNumber);
}
