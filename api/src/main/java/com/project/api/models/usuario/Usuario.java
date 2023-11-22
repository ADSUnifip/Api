package com.project.api.models.usuario;

import com.project.api.dtos.DadosAtualizacaoEndereco;
import com.project.api.dtos.DadosEndereco;
import com.project.api.models.Endereco;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;

@Entity
@Data
@Table(name = "TB_USUARIO")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private LocalDate dataNacimento;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @Column()
    private String telefone;

    @Embedded
    private Endereco endereco;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    public Usuario(String nome, String cpf, LocalDate dataNacimento, String sexo, String telefone, DadosEndereco endereco, String email, String senha, TipoUsuario tipoUsuario) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNacimento = dataNacimento;
        this.sexo = Sexo.valueOf(sexo);
        this.telefone = telefone;
        this.endereco = new Endereco(endereco.rua(), endereco.bairro(), endereco.cep(), endereco.numero(), endereco.cidade(), endereco.estado());
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    public void atualizarEndereco(DadosAtualizacaoEndereco dados) {
        if (dados.rua() != null) {
            endereco.setRua(dados.rua());
        }

        if (dados.bairro() != null) {
            endereco.setBairro(dados.bairro());
        }

        if (dados.cep() != null) {
            endereco.setCep(dados.cep());
        }

        if (dados.numero() != null) {
            endereco.setNumero(dados.numero());
        }

        if (dados.cidade() != null) {
            endereco.setCidade(dados.cidade());
        }

        if (dados.estado() != null) {
            endereco.setEstado(dados.estado());
        }
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Usuario() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(id, usuario.id) && Objects.equals(nome, usuario.nome) && Objects.equals(cpf, usuario.cpf) && Objects.equals(dataNacimento, usuario.dataNacimento) && sexo == usuario.sexo && Objects.equals(email, usuario.email) && Objects.equals(senha, usuario.senha) && tipoUsuario == usuario.tipoUsuario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cpf, dataNacimento, sexo, email, senha, tipoUsuario);
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNacimento() {
        return dataNacimento;
    }

    public void setDataNacimento(LocalDate dataNacimento) {
        this.dataNacimento = dataNacimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = Sexo.valueOf(sexo);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
