package com.linkcast.adapters.out.persistence.entity;

import com.linkcast.domain.enums.StatusEpisodio;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "episodios")
public class EpisodioJpaEntity {
    
    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "conteudo_id", nullable = false)
    private UUID conteudoId;

    @Column(name = "titulo", length = 300)
    private String titulo;

    @Column(name = "descricao", columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "duracao_segundos")
    private Integer duracaoSegundos;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 30, nullable = false)
    private StatusEpisodio status;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    public EpisodioJpaEntity() {}

    public EpisodioJpaEntity(UUID id, UUID conteudoId, String titulo, String descricao,
                             Integer duracaoSegundos, StatusEpisodio status, LocalDateTime criadoEm) {
        this.id = id;
        this.conteudoId = conteudoId;
        this.titulo = titulo;
        this.descricao = descricao;
        this.duracaoSegundos = duracaoSegundos;
        this.status = status;
        this.criadoEm = criadoEm;
    }

    @PrePersist
    protected void onCreate() {
        if (criadoEm == null) {
            criadoEm = LocalDateTime.now();
        }
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getConteudoId() { return conteudoId; }
    public void setConteudoId(UUID conteudoId) { this.conteudoId = conteudoId; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Integer getDuracaoSegundos() { return duracaoSegundos; }
    public void setDuracaoSegundos(Integer duracaoSegundos) { this.duracaoSegundos = duracaoSegundos; }
    public StatusEpisodio getStatus() { return status; }
    public void setStatus(StatusEpisodio status) { this.status = status; }
    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
}