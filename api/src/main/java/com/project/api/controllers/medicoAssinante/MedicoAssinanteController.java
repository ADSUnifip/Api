package com.project.api.controllers.medicoAssinante;

import com.project.api.dtos.medicoAssinante.DadosAtualizacaoMedicoAssinante;
import com.project.api.dtos.medicoAssinante.DadosCadastroMediccoAssinante;
import com.project.api.models.medicoAssinante.MedicoAssinante;
import com.project.api.services.medicoAssinante.MedicoAssinanteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/medicoassinante")
@CrossOrigin(origins = "*", maxAge = 3600)
public class MedicoAssinanteController {

    @Autowired
    private MedicoAssinanteService medicoAssinanteService;

    @GetMapping
    public ResponseEntity<List<MedicoAssinante>> listarMedicoAssinante() {
        var medicosAssinantes = medicoAssinanteService.listarMedicoAssinante();

        return ResponseEntity.status(HttpStatus.OK).body(medicosAssinantes);
    }

    @PostMapping
    public ResponseEntity<MedicoAssinante> cadastrarMedicoAssinante(@RequestBody @Valid DadosCadastroMediccoAssinante dados) {

        var medicoAssinante = medicoAssinanteService.criarMedicoAssinante(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoAssinante);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity softDeleteMedicoAssinante(@PathVariable UUID id) {

        var deuCerto = medicoAssinanteService.softDeleteMedico(id);
        if (deuCerto) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PatchMapping("/{id}")
    public ResponseEntity ativarMedicoAssinante(@PathVariable UUID id) {

        var deuCerto = medicoAssinanteService.ativarMedico(id);
        if (deuCerto) {
            return ResponseEntity.status(HttpStatus.OK).body(medicoAssinanteService.buscarMedicoAssinantePorId(id));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity buscarMedicoAssinantePorID(@PathVariable UUID id) {

        var medico = medicoAssinanteService.buscarMedicoAssinantePorId(id);
        if (medico.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(medico.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PatchMapping("atualizar/{id}")
    public ResponseEntity atualizarMedicoAssinante(@PathVariable UUID id, @RequestBody DadosAtualizacaoMedicoAssinante dados) {
        var medico = medicoAssinanteService.atualizarMedico(id, dados);
        return medico;
    }
}
