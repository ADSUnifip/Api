package com.project.api.repositories;

import com.project.api.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository <Patient, UUID> {
    List<Patient> findAll();

    Optional<Patient> findById(UUID id);

    Optional<Patient> findByFullName(String fullName);

    Optional<Patient> findByCpf(String cpf);

}
