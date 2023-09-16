package com.project.api.services.medicoAssinante;

import com.project.api.dtos.medicoAssinante.DadosCadastroMediccoAssinante;
import com.project.api.models.medicoAssinante.MedicoAssinante;
import com.project.api.repositories.medicoAssinante.MedicoAssinanteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MedicoAssinanteService {

    @Autowired
    private MedicoAssinanteRepository medicoAssinanteRepository;

    public List<MedicoAssinante> listarMedicoAssinante() {
        return medicoAssinanteRepository.findAll();
    }

    @Transactional
    public MedicoAssinante criarMedicoAssinante (DadosCadastroMediccoAssinante dados) {
        var medicoAssinante = new MedicoAssinante(dados.numeroConselho(), dados.ufConselho(), dados.tipoConselho(), dados.nomeMedicoAssinante());
        medicoAssinanteRepository.save(medicoAssinante);
        return medicoAssinante;
    }

    public Optional<MedicoAssinante> buscarMedicoAssinantePorId(UUID id) {
        return medicoAssinanteRepository.findById(id);
    }

    @Transactional
    public boolean softDeleteMedico(UUID id) {
        Optional<MedicoAssinante> medicoOptional = buscarMedicoAssinantePorId(id);

        if (medicoOptional.isPresent()) {
            MedicoAssinante medico = medicoOptional.get();
            medico.setIsActive(false);
            medicoAssinanteRepository.save(medico);
            return true;
        }

        return false;
    }

    @Transactional
    public boolean ativarMedico(UUID id) {
        Optional<MedicoAssinante> medicoOptional = buscarMedicoAssinantePorId(id);

        if (medicoOptional.isPresent()) {
            MedicoAssinante medico = medicoOptional.get();
            medico.setIsActive(true);
            medicoAssinanteRepository.save(medico);
            return true;
        }

        return false;
    }
}
