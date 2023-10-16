package com.project.api.services;


import com.project.api.models.Atendimento;

import com.project.api.repositories.AtendimentoRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    public Optional<Atendimento> updateAtendimento(UUID id, Atendimento obj) {

        var atendimentoAlterado = atendimentoRepository.findById(id);


        atendimentoAlterado.get().setDate(obj.getDate());
        atendimentoAlterado.get().setPaciente(obj.getPaciente());
        atendimentoAlterado.get().setProcedimento(obj.getProcedimento());
        atendimentoAlterado.get().setMedicoAssinante(obj.getMedicoAssinante());


        return atendimentoAlterado;
    }

    public void delete(UUID id) {
        var atendimento = atendimentoRepository.findById(id);
        atendimento.get().setActive(false);
    }
}
