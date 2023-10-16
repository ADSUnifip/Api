package com.project.api.controllers;

import com.project.api.dtos.ProcedimentoDto.ResponsePorcedimentoDto;
import com.project.api.dtos.ProcedimentoDto.UpdateProcedimentoDto;
import com.project.api.dtos.medicoAssinante.DadosCadastroMediccoAssinante;
import com.project.api.models.Atendimento;
import com.project.api.models.medicoAssinante.MedicoAssinante;
import com.project.api.services.AtendimentoService;
import com.project.api.services.ProcedimentoServicie;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public class AtendimentoController {

    @Autowired
    private AtendimentoService serviceAtendimento;

    @GetMapping
    public ResponseEntity<List<Atendimento>> listaAtendimentos() {
        var atendimentos = serviceAtendimento.listaAtendimeto();

        return ResponseEntity.status(HttpStatus.OK).body(atendimentos);
    }
    @PostMapping
    public ResponseEntity<Atendimento> cadastrarAtendimento(@RequestBody @Valid Atendimento obj) {

        var atendimento = serviceAtendimento.cadastrarAtendimento(obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(atendimento);

    }
    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateAtendimento(@PathVariable UUID id, @RequestBody Atendimento obj){
        var atendimentoAlterado = serviceAtendimento.updateAtendimento(id,obj);

        return ResponseEntity.ok(atendimentoAlterado);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAtendimento(@PathVariable UUID id){
        serviceAtendimento.delete(id);
        return ResponseEntity.noContent().build();
    }
}
