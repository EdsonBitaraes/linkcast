package com.linkcast.adapters.out.persistence.entity;

import com.linkcast.domain.enums.StatusConteudo;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "conteudos")
public class ConteudoJpaEntity {
    
    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "usuario_id", nullable = false)
    private UUID usuarioId;

    @Column(name = "titulo", length = 300)
    private String titulo;

    @Column(name = "url_origem", columnDefinition = "TEXT", nullable = false)
    private String urlOrigem;

    @Column(name = "texto_original", columnDefinition = "TEXT")
    private String textoOriginal;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 30, nullable = false)
    private StatusConteudo status;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    public ConteudoJpaEntity() {}

    public ConteudoJpaEntity(UUID id, UUID usuarioId, String titulo, String urlOrigem, 
                             String textoOriginal, StatusConteudo status, LocalDateTime criadoEm) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.titulo = titulo;
        this.urlOrigem = urlOrigem;
        this.textoOriginal = textoOriginal;
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
    public UUID getUsuarioId() { return usuarioId; }
    public void setUsuarioId(UUID usuarioId) { this.usuarioId = usuarioId; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getUrlOrigem() { return urlOrigem; }
    public void setUrlOrigem(String urlOrigem) { this.urlOrigem = urlOrigem; }
    public String getTextoOriginal() { return textoOriginal; }
    public void setTextoOriginal(String textoOriginal) { this.textoOriginal = textoOriginal; }
    public StatusConteudo getStatus() { return status; }
    public void setStatus(StatusConteudo status) { this.status = status; }
    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }
}