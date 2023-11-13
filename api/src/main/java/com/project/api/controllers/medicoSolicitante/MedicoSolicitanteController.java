package com.project.api.controllers.medicoSolicitante;

import com.project.api.dtos.medicoSolicitante.MedicoSolicitanteDto;
import com.project.api.models.medicoSolicitante.MedicoSolicitante;
import com.project.api.repositories.medicoSolicitante.MedicoSolicitedRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.*;

@RestController
@RequestMapping("/api/med")
@CrossOrigin(origins = "*")
public class MedicoSolicitanteController{

    @Autowired
    MedicoSolicitedRepository medicoSolicitanteRepository;

    //criação do medico
    @PostMapping("/medicoSolicitante")
    public ResponseEntity<MedicoSolicitante> saveMedicoSolicitante(@ModelAttribute @Valid MedicoSolicitanteDto medicoSolicitanteDto, HttpServletRequest request){
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        var medicoSolicitante = new MedicoSolicitante();
        BeanUtils.copyProperties(medicoSolicitanteDto, medicoSolicitante);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoSolicitanteRepository.save(medicoSolicitante));
    }

    //listagem geral
    @GetMapping("/medicoSolicitante")
    public ResponseEntity<List<MedicoSolicitante>> getAllMedicoSolicitante(){
        return ResponseEntity.status(HttpStatus.OK).body(medicoSolicitanteRepository.findAll());
    }

    //pesquisa por UUID
    @GetMapping("/medicoSolicitante/{id}")
    public ResponseEntity<Object> getOneMedicoSolicitante(@PathVariable(value="id") UUID id){
        Optional<MedicoSolicitante> medicoSolicitante0 = medicoSolicitanteRepository.findById(id);
        if(medicoSolicitante0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("UUID nao cadastrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(medicoSolicitante0.get());
    }

    //pesquisa por crm
    @GetMapping("/medicoSolicitante/crm/{crm}")
    public ResponseEntity<List<Object>> getOneMedicoSolicitanteCrm(@PathVariable(value="crm") String crm){
        var medicoSolicitante0 = medicoSolicitanteRepository.findByCrm(crm);
        if(medicoSolicitante0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonList("Crm nao cadastrado"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonList(medicoSolicitante0.get()));
    }



    //pesquisa por uf do crm
    @GetMapping("/medicoSolicitante/uf/{ufCrm}")
    public ResponseEntity<List<Object>> getOneMedicoSolicitanteUf(@PathVariable(value="ufCrm") String ufCrm){
        var medicoSolicitante = medicoSolicitanteRepository.findByUfCrm(ufCrm);
        if(medicoSolicitante.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonList("Crm nao cadastrado"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonList(medicoSolicitante));
    }

        //pesquisa por nome do medico
        @GetMapping("/medicoSolicitante/nome/{nomeCompl}")
        public ResponseEntity<Object> getOneMedicoSolicitanteNome(@PathVariable(value="nomeCompl") String nomeCompl){
            Optional<MedicoSolicitante> medicoSolicitante0 = medicoSolicitanteRepository.findByNomeCompl(nomeCompl);
            if(medicoSolicitante0.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Crm nao cadastrado");
            }
            return ResponseEntity.status(HttpStatus.OK).body(medicoSolicitante0.get());
        }

    // ALTERA
    @PutMapping("/medicoSolicitante/{id}")
    public ResponseEntity<Object> updateMedicoSolicitante(@PathVariable(value = "id") UUID id, @RequestBody @Valid MedicoSolicitanteDto medicoSolicitanteDto){
        Optional<MedicoSolicitante> medicoSolicitante0 = medicoSolicitanteRepository.findById(id);
        if (medicoSolicitante0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Crm nao cadastrado");
        }
        var medicoSolicitante = medicoSolicitante0.get();
        BeanUtils.copyProperties(medicoSolicitanteDto, medicoSolicitante);
        return ResponseEntity.status(HttpStatus.OK).body(medicoSolicitanteRepository.save(medicoSolicitante));
    }


    //metodo para deletar da base toda a informação do medico
    @DeleteMapping("/medicoSolicitante/delete/{id}")
    public ResponseEntity<Object> deleteMedicoSolicitante(@PathVariable(value="id") UUID id){
        Optional<MedicoSolicitante> medicoSolicitante0 = medicoSolicitanteRepository.findById(id);
        if (medicoSolicitante0.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Crm nao cadastrado");
        }
        medicoSolicitanteRepository.delete(medicoSolicitante0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Crm Deletado");
    }



}

