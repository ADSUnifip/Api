package com.project.api.services.PatientService;

import com.project.api.dtos.PatientDto.PatientDto;
import com.project.api.dtos.PatientDto.UpdatePatientDto;
import com.project.api.models.Patient.Patient;
import com.project.api.repositories.PatientRepository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    public List<Patient> findAll(){
        return patientRepository.findAll();
    }

    public Optional<Patient> findById(UUID id){
        return patientRepository.findById(id);
    }

    public List<Patient> findByCpf(String cpf){
        return patientRepository.findBycpf(cpf);
    }

    public List<Patient> findByFullName(String fullName){
        return patientRepository.findByfullName(fullName);
    }

    @Transactional
    public Patient save(PatientDto patientDto){
        var patient = new Patient(patientDto);
        return patientRepository.save(patient);
    }

    @Transactional
    public ResponseEntity updatePatient(UUID id, UpdatePatientDto dto){
        Optional<Patient> patientUpdate = findById(id);
        if (patientUpdate.isPresent()){
            Patient patient = patientUpdate.get();

            if (dto.fullName() !=null){
                patient.setFullName(dto.fullName());
            }
            if (dto.cpf() !=null){
                patient.setCpf(dto.cpf());
            }
            if (dto.sex() !=null){
                patient.setSex(dto.sex());
            }
            if (dto.birthDate() !=null){
                patient.setBirthDate(dto.birthDate());
            }

            if (dto.telephone() !=null){
                patient.setTelephone(dto.telephone());
            }
            if (dto.email() !=null){
                patient.setEmail(dto.email());
            }

            patientRepository.save(patient);
            return ResponseEntity.status(HttpStatus.OK).body(patient);

        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Atualização não realizada");
    }

    @Transactional
    public void softDelete(Patient patient){
        ///Optional<Patient> patientDelete = patientRepository.findById(patient.getId());
        patient.setActive(false);
        patientRepository.save(patient);
        ///patientDelete.ifPresent(value -> value.setActive(false));
    }

}
