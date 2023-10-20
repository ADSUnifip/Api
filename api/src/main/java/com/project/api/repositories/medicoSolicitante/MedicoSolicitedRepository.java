package com.project.api.repositories.medicoSolicitante;

import com.project.api.models.medicoSolicitante.MedicoSolicitante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MedicoSolicitedRepository extends JpaRepository <MedicoSolicitante, UUID> {


    Optional<MedicoSolicitante> findByCrm(String crm);

    List<MedicoSolicitante> findByUfCrm(String ufCrm);

    Optional<MedicoSolicitante> findByNomeCompl(String nomeCompl);
}


