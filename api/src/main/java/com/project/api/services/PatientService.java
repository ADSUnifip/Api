package com.project.api.services;

import com.project.api.models.Patient;
import com.project.api.repositories.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class PatientService {

    @Autowired
    final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    public List<Patient> findAll(){
        return patientRepository.findAll();
    }

    public Optional<Patient> findById(UUID id){
        return patientRepository.findById(id);
    }

    public Optional<Patient> findByCpf(String cpf){
        return patientRepository.findByCpf(cpf);
    }

    public Optional<Patient> findByFullName(String fullName){
        return patientRepository.findByFullName(fullName);
    }

    @Transactional
    public Patient save(Patient patient){
        return patientRepository.save(patient);
    }

    public Patient partialUpdate(Patient patient, Map<Object, Object> objectMap){
        objectMap.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Patient.class, (String) key);
            field.setAccessible(true);

            try{
                value = BigDecimal.valueOf((double) value);
            }
            catch (ClassCastException ignored){}
            ReflectionUtils.setField(field, patient, value);
        });
        return patientRepository.save(patient);
    }

    public void softDelete(Patient patient){
        Optional<Patient> patientDelete = patientRepository.findById(patient.getId());
        patientDelete.ifPresent(value -> value.setActive(false));
    }

}
