package com.project.api.repositories.usuario;

import com.project.api.models.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.data.repository.query.Param;
//import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository  extends JpaRepository<Usuario, UUID> {
    @Query("SELECT u FROM Usuario u WHERE u.nome = :nome")
    Optional<Usuario> encontrarPorNome(@Param("nome") String nome);
    Optional<Usuario> findByNome(String nome);

    @Query("SELECT u.id, u.cpf, u.dataNacimento, u.email, u.nome, u.sexo FROM Usuario u")
    List<Usuario> listarUsuarios();

    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    Optional<Usuario> buscarPorEmail(String email);

    Optional<Usuario> findByCpf(String cpf);

    UserDetails findByEmail(String email);
}
