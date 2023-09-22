package com.project.api.controllers;

import com.project.api.dtos.PatientDto;
import com.project.api.models.Patient;
import com.project.api.services.PatientService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("api/patient")
@CrossOrigin(origins = "*", maxAge = 3600)
public class PatientController {
    @Autowired
    PatientService patientService;

    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatient(){
        return ResponseEntity.status(HttpStatus.OK).body(patientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPatientById(@PathVariable(value = "id")UUID id){
        Optional<Patient> patientOptional = patientService.findById(id);
        if (patientOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(patientOptional.get());
    }

    @GetMapping("/findByCpf")
    public ResponseEntity<Optional<Patient>> getFindByCpf(@Validated @RequestParam(value = "cpf")String cpf){
        return ResponseEntity.status(HttpStatus.OK).body(patientService.findByCpf(cpf));
    }

    @GetMapping("/findByFullName")
    public ResponseEntity<Optional<Patient>> getFindByFullName(@Validated @RequestParam(value = "fullName")String fullName){
        return  ResponseEntity.status(HttpStatus.OK).body(patientService.findByFullName(fullName));
    }

    @PostMapping
    public ResponseEntity<Object> savePatient (@Valid PatientDto patientDto, HttpServletRequest request) throws IOException {
        var patient = new Patient();
        BeanUtils.copyProperties(patientDto, patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.save(patient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePatient (@PathVariable(value = "id")UUID id){
        Optional<Patient> patientOptional = patientService.findById(id);
        if (patientOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found");
        }
        patientService.softDelete(patientOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Patient deleted succesfuly");
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> updatePatient (@PathVariable(value = "id")UUID id, HttpServletRequest request) {
        Optional<Patient> patientOptional = patientService.findById(id);
        if (patientOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found");
        }
        Map<Object, Object> objectMap = new HashMap<>();
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()){
            objectMap.put(entry.getKey(), entry.getValue()[0]);
        }
        patientService.partialUpdate(patientOptional.get(), objectMap);
        return ResponseEntity.status(HttpStatus.OK).body(patientOptional.get());
    }
}
