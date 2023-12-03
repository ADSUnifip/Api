package com.project.api.services;

import com.project.api.models.AtendimentoProcedi;
import com.project.api.repositories.Atendimento_proced;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AtendimentoProcediService {

    @Autowired
    private Atendimento_proced atendimento_proced;

    public void Cadastro(AtendimentoProcedi atd){
        atendimento_proced.save(atd);
    }

    public List<AtendimentoProcedi> listaAtendimeto(UUID id) {
        return atendimento_proced.findByatendimento(id);
    }
}
