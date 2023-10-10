package com.project.api.repositories.usuario;

import com.project.api.models.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository  extends JpaRepository<Usuario, UUID> {
    /*@Query("SELECT u FROM Usuario u WHERE u.nome = :nome")
    Optional<Usuario> encontrarPorNome(@Param("nome") String nome);*/
    Optional<Usuario> findByNome(String nome);

    Optional<Usuario> findByCpf(String cpf);

    UserDetails findByEmail(String email);
}
