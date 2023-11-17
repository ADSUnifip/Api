package com.project.api.repositories.PatientRepository;

import com.project.api.models.Patient.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository <Patient, UUID> {
    List<Patient> findAll();

    Optional<Patient> findById(UUID id);

    List<Patient> findByfullName(String fullName);

    List<Patient> findBycpf(String cpf);

}
