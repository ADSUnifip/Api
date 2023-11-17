package com.project.api.repositories.medicoAssinante;

import com.project.api.models.medicoAssinante.MedicoAssinante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MedicoAssinanteRepository extends JpaRepository <MedicoAssinante, UUID> {

}
