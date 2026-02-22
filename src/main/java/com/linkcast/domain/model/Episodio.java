package com.linkcast.domain.model;

import com.linkcast.domain.enums.StatusEpisodio;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Episodio {
    
    private final UUID id;
    private final UUID conteudoId;
    private String titulo;
    private String descricao;
    private Integer duracaoSegundos;
    private StatusEpisodio status;
    private final LocalDateTime criadoEm;

    public Episodio(UUID id, UUID conteudoId, String titulo, String descricao,
                    Integer duracaoSegundos, StatusEpisodio status, LocalDateTime criadoEm) {
        this.id = Objects.requireNonNull(id, "ID não pode ser nulo");
        this.conteudoId = Objects.requireNonNull(conteudoId, "Conteúdo ID não pode ser nulo");
        this.status = Objects.requireNonNull(status, "Status não pode ser nulo");
        this.criadoEm = Objects.requireNonNull(criadoEm, "Data de criação não pode ser nula");
        this.titulo = titulo;
        this.descricao = descricao;
        this.duracaoSegundos = duracaoSegundos;
    }

    public static Episodio criar(UUID conteudoId, String titulo, String descricao) {
        return new Episodio(
            UUID.randomUUID(),
            conteudoId,
            titulo,
            descricao,
            null,
            StatusEpisodio.GERANDO,
            LocalDateTime.now()
        );
    }

    public void marcarComoPronto(Integer duracaoSegundos) {
        if (duracaoSegundos != null && duracaoSegundos < 0) {
            throw new IllegalArgumentException("Duração não pode ser negativa");
        }
        this.duracaoSegundos = duracaoSegundos;
        this.status = StatusEpisodio.PRONTO;
    }

    public void marcarComoFalha() {
        this.status = StatusEpisodio.FALHA;
    }

    public UUID getId() { return id; }
    public UUID getConteudoId() { return conteudoId; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public Integer getDuracaoSegundos() { return duracaoSegundos; }
    public StatusEpisodio getStatus() { return status; }
    public LocalDateTime getCriadoEm() { return criadoEm; }

    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setStatus(StatusEpisodio status) { this.status = status; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Episodio episodio = (Episodio) o;
        return Objects.equals(id, episodio.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}