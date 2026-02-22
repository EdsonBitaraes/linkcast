package com.linkcast.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Usuario {
    
    private final UUID id;
    private String nome;
    private String email;
    private String senhaHash;
    private final LocalDateTime criadoEm;

    public Usuario(UUID id, String nome, String email, String senhaHash, LocalDateTime criadoEm) {
        this.id = Objects.requireNonNull(id, "ID não pode ser nulo");
        this.nome = Objects.requireNonNull(nome, "Nome não pode ser nulo");
        this.email = Objects.requireNonNull(email, "Email não pode ser nulo");
        this.senhaHash = Objects.requireNonNull(senhaHash, "Senha hash não pode ser nula");
        this.criadoEm = Objects.requireNonNull(criadoEm, "Data de criação não pode ser nula");
        validar();
    }

    public static Usuario criar(String nome, String email, String senhaHash) {
        return new Usuario(
            UUID.randomUUID(),
            nome,
            email,
            senhaHash,
            LocalDateTime.now()
        );
    }

    private void validar() {
        if (nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
        if (senhaHash.trim().isEmpty()) {
            throw new IllegalArgumentException("Senha não pode ser vazia");
        }
    }

    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenhaHash() { return senhaHash; }
    public LocalDateTime getCriadoEm() { return criadoEm; }

    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setSenhaHash(String senhaHash) { this.senhaHash = senhaHash; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}