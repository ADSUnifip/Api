package com.project.api.services;


import com.project.api.exeptions.NotFoundException;
import com.project.api.models.Atendimento;

import com.project.api.repositories.AtendimentoRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository atendimentoRepository;
    public List<Atendimento> listaAtendimeto() {

        return atendimentoRepository.findAll();
    }

    @Transactional
    public Atendimento cadastrarAtendimento(Atendimento obj) {
        return atendimentoRepository.save(obj);
    }

    @Transactional
    public Atendimento updateAtendimento(UUID id, Atendimento obj) {
        // Recupere o atendimento a ser atualizado
        Optional<Atendimento> atendimentoOptional = atendimentoRepository.findById(id);

        if (atendimentoOptional.isEmpty()) {
            return null;
        }


        Atendimento atendimentoAlterado = atendimentoOptional.get();

        atendimentoAlterado.setDate(obj.getDate());
        atendimentoAlterado.setPaciente(obj.getPaciente());
        atendimentoAlterado.setProcedimento(obj.getProcedimento());
        atendimentoAlterado.setMedicoSolictante(obj.getMedicoSolicitante());



        return atendimentoAlterado;
    }

    @Transactional
    public void delete(UUID id) {
        Optional<Atendimento> atendimentoOptional = atendimentoRepository.findById(id);

        if (atendimentoOptional.isPresent()) {
            Atendimento atendimento = atendimentoOptional.get();
            atendimento.setActive(false);
        } else {
            throw new NotFoundException("Atendimento não encontrado com ID: " + id);
        }
    }

}