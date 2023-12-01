package com.project.api.controllers.PatienController;

import com.project.api.dtos.PatientDto.PatientDto;
import com.project.api.dtos.PatientDto.UpdatePatientDto;
import com.project.api.models.Patient.Patient;
import com.project.api.services.PatientService.PatientService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("api/patient")
@CrossOrigin(origins = "*")
public class PatientController {
    @Autowired()
    PatientService patientService;

    ///Mapeamento para puxar todo os pacientes
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatient(){
        return ResponseEntity.status(HttpStatus.OK).body(patientService.findAll());
    }

    ///Mapemamento para puxar o paciente pelo ID, sendo passado o ID como paramento da URL
    @GetMapping("/{id}")
    public ResponseEntity<Object> getPatientById(@PathVariable(value = "id")UUID id){
        Optional<Patient> patientOptional = patientService.findById(id);
        if (patientOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(patientOptional.get());
    }


    ///Mapeamento para puxar o paciente pelo seu CPF, sendo passado o CPF no body da requisição
    @GetMapping("/findByCPF/{cpf}")
    public ResponseEntity<List<Patient>> getFindByCpf(@PathVariable(value = "cpf") String cpf){
        return ResponseEntity.status(HttpStatus.OK).body(patientService.findByCpf(cpf));
    }

    ///Mapeamento para puxar o paciente pelo seu NOME, sendo passado o NOME no boyd da requisição
    @GetMapping("/findByFullName")
    public ResponseEntity<List<Patient>> getFindByFullName(@Validated @RequestParam(value = "fullName")String fullName){
        return  ResponseEntity.status(HttpStatus.OK).body(patientService.findByFullName(fullName));
    }

    ///Método para adicionar um paciente
    @PostMapping
    public ResponseEntity savePatient (@ModelAttribute @Valid PatientDto patientDto, HttpServletRequest request) {
        //MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        var patient = patientService.save(patientDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(patient);
    }

    ///Método para deletar um cliente, porém ele não é exluido do banco de dados apenas recebe o false no ACTIVE
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePatient (@PathVariable(value = "id")UUID id){
        Optional<Patient> patientOptional = patientService.findById(id);
        if (patientOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found");
        }
        patientService.softDelete(patientOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Patient deleted succesfuly");
    }

    ///Método para atualizar algum atributo do paciente
    @PatchMapping("/{id}")
    public ResponseEntity updatePatient(@PathVariable UUID id, @RequestBody @Valid UpdatePatientDto dto){
        var patient = patientService.updatePatient(id, dto);
        if (patient !=null) {
            return ResponseEntity.status(HttpStatus.OK).body(patient);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
