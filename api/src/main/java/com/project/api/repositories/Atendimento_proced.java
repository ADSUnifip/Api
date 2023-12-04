package com.project.api.repositories;

import com.project.api.models.AtendimentoProcedi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface Atendimento_proced extends JpaRepository<AtendimentoProcedi, UUID> {

    List<AtendimentoProcedi>findByatendimento(UUID id);
}
