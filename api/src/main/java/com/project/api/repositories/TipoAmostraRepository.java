package com.project.api.repositories;

import com.project.api.models.TipoAmostra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TipoAmostraRepository extends JpaRepository<TipoAmostra, UUID> {
}
