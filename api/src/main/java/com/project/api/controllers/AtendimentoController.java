package com.project.api.controllers;

import com.project.api.dtos.ProcedimentoDto.ResponsePorcedimentoDto;
import com.project.api.dtos.ProcedimentoDto.UpdateProcedimentoDto;
import com.project.api.dtos.medicoAssinante.DadosCadastroMediccoAssinante;
import com.project.api.exeptions.NotFoundException;
import com.project.api.models.Atendimento;
import com.project.api.models.medicoAssinante.MedicoAssinante;
import com.project.api.services.AtendimentoService;
import com.project.api.services.ProcedimentoServicie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/atendimento")
@CrossOrigin(origins = "*")
public class AtendimentoController {

    @Autowired()
    private AtendimentoService serviceAtendimento;

    //alterações
    @GetMapping
    public ResponseEntity<List<Atendimento>> listaAtendimentos() {
        var atendimentos = serviceAtendimento.listaAtendimeto();

        return ResponseEntity.status(HttpStatus.OK).body(atendimentos);
    }

    @PostMapping
    public ResponseEntity<Atendimento> cadastrarAtendimento(@ModelAttribute @Valid Atendimento obj, HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        var atendimento = serviceAtendimento.cadastrarAtendimento(obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(atendimento);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateAtendimento(@PathVariable UUID id, @RequestBody Atendimento obj){

        var atendimentoAlterado = serviceAtendimento.updateAtendimento(id,obj);
        if (atendimentoAlterado == null){
            throw new NotFoundException("Atendimento não encontrado com ID: " + id);
        }

        return ResponseEntity.ok(atendimentoAlterado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAtendimento(@PathVariable UUID id){
        try {
            serviceAtendimento.delete(id);
        } catch (Exception e) {
            throw new NotFoundException("Atendimento não encontrado com ID: " + id, e);
        }

        return ResponseEntity.noContent().build();
    }
}