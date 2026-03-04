package com.name.trabajo1implementacion.services;

import com.name.trabajo1implementacion.entities.CrimeCase;
import com.name.trabajo1implementacion.repositories.CrimeCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CrimeCaseService {
    @Autowired
    private CrimeCaseRepository crimeCaseRepository;
    @Transactional(readOnly = true)
    public List<CrimeCase> obtenerCasosAbiertos() {
        List<CrimeCase> casos = crimeCaseRepository.findBySolvedFalse();
        if (casos.isEmpty()) {
            throw new RuntimeException("No hay casos abiertos actualmente. La ciudad parece estar en paz... por ahora.");
        }
        return casos;
    }
    @Transactional(readOnly = true)
    public CrimeCase buscarPorNumeroExpediente(String numero) {
        CrimeCase caso = crimeCaseRepository.findByCaseNumber(numero);
        if (caso == null) {
            throw new RuntimeException("El expediente " + numero + " no existe en los archivos de Luna Lunera y Asociados S.A.");
        }
        return caso;
    }
    @Transactional
    public CrimeCase crearNuevoCaso(CrimeCase nuevoCaso) {
        if (nuevoCaso.getCaseNumber() == null) {
            throw new RuntimeException("Error: Todo crimen debe tener un número de expediente único.");
        }
        return crimeCaseRepository.save(nuevoCaso);
    }
}