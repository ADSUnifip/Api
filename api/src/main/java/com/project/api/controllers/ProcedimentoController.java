package com.project.api.controllers;

import com.project.api.dtos.ProcedimentoDto.CreateProcedimentoDto;
import com.project.api.dtos.ProcedimentoDto.ResponsePorcedimentoDto;
import com.project.api.dtos.ProcedimentoDto.UpdateProcedimentoDto;
import com.project.api.services.ProcedimentoServicie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("api/procedimento")
@CrossOrigin(origins = "*")
public class ProcedimentoController {

    @Autowired
    private ProcedimentoServicie servicie;

    @PostMapping()
    public ResponseEntity<Object> createProcedimento(@ModelAttribute @Valid CreateProcedimentoDto dto, UriComponentsBuilder uribuilder,HttpServletRequest request){
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        var procedimento = servicie.createProcedimento(dto);

        var uri = uribuilder.path("api/procedimento/{id}").buildAndExpand(procedimento.getId()).toUri();

        return ResponseEntity.created(uri).body(new ResponsePorcedimentoDto(procedimento));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateProcedimento(@PathVariable UUID id, @RequestBody UpdateProcedimentoDto dto){
        var procedimentoAlterado = servicie.updateProcedimento(id,dto);

        return ResponseEntity.ok(new ResponsePorcedimentoDto(procedimentoAlterado));
    }

    @GetMapping
    public ResponseEntity<Object> getByActiveTrue(){
        return ResponseEntity.ok().body(servicie.getByActiveTrue());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProcediemnto(@PathVariable UUID id){
        servicie.delete(id);
        return ResponseEntity.noContent().build();
    }

}
