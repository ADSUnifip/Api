package com.project.api.repositories;

import com.project.api.models.Procedimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProcedimentoRepository extends JpaRepository<Procedimento, UUID> {

    List<Procedimento> findByAtivoTrue();
}
