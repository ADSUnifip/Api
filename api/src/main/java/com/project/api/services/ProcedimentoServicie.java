package com.project.api.services;

import com.project.api.dtos.ProcedimentoDto.CreateProcedimentoDto;
import com.project.api.dtos.ProcedimentoDto.UpdateProcedimentoDto;
import com.project.api.models.Procedimento;
import com.project.api.repositories.ProcedimentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProcedimentoServicie {

    @Autowired
    private ProcedimentoRepository repository;

    @Transactional
    public Procedimento createProcedimento(CreateProcedimentoDto dto){
        var procedimento = new Procedimento(dto);

        repository.save(procedimento);

        return procedimento;
    }

    public List<Procedimento> getByActiveTrue() {
        return repository.findByAtivoTrue();
    }

    public Procedimento findById(UUID id){
        return repository.getReferenceById(id);
    }

    @Transactional
    public Procedimento updateProcedimento(UUID id, UpdateProcedimentoDto dto){
        var procedimentoAlterado = findById(id);

        procedimentoAlterado.edit(dto);

        return procedimentoAlterado;
    }

    @Transactional
    public  void  delete(UUID id){
        var procedimento = findById(id);
        procedimento.delete();
    }
}
