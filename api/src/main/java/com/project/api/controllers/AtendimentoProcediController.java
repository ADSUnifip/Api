package com.project.api.controllers;

import com.project.api.models.Atendimento;
import com.project.api.models.AtendimentoProcedi;
import com.project.api.models.Procedimento;
import com.project.api.services.AtendimentoProcediService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("api/atdpr")
@CrossOrigin(origins = "*")
public class AtendimentoProcediController {

    @Autowired()
    private AtendimentoProcediService atendimentoProcediService;

    @PostMapping("list/{id}")
    public ResponseEntity<List<AtendimentoProcedi>> listaAtendimentos(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(atendimentoProcediService.listaAtendimeto(id));
    }

    @PostMapping("/{id}")
    public void atendproc(@PathVariable UUID id, @RequestBody List<Procedimento>Lista){
        //MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        for(int i = 0; i < Lista.size(); i++){
            AtendimentoProcedi atd = new AtendimentoProcedi();
            atd.setAtendimento(id);
            atd.setProcedimento(Lista.get(i).getId());
            atendimentoProcediService.Cadastro(atd);
            System.out.println("deu certo!");
        }

    }
}
