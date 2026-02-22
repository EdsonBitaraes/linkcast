package com.linkcast.adapters.out.persistence.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "audios")
public class AudioJpaEntity {
    
    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "episodio_id", nullable = false)
    private UUID episodioId;

    @Column(name = "url_arquivo", columnDefinition = "TEXT", nullable = false)
    private String urlArquivo;

    @Column(name = "formato", length = 20, nullable = false)
    private String formato;

    @Column(name = "tamanho_bytes")
    private Long tamanhoBytes;

    @Column(name = "gerado_em", nullable = false, updatable = false)
    private LocalDateTime geradoEm;

    public AudioJpaEntity() {}

    public AudioJpaEntity(UUID id, UUID episodioId, String urlArquivo, String formato,
                          Long tamanhoBytes, LocalDateTime geradoEm) {
        this.id = id;
        this.episodioId = episodioId;
        this.urlArquivo = urlArquivo;
        this.formato = formato;
        this.tamanhoBytes = tamanhoBytes;
        this.geradoEm = geradoEm;
    }

    @PrePersist
    protected void onCreate() {
        if (geradoEm == null) {
            geradoEm = LocalDateTime.now();
        }
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getEpisodioId() { return episodioId; }
    public void setEpisodioId(UUID episodioId) { this.episodioId = episodioId; }
    public String getUrlArquivo() { return urlArquivo; }
    public void setUrlArquivo(String urlArquivo) { this.urlArquivo = urlArquivo; }
    public String getFormato() { return formato; }
    public void setFormato(String formato) { this.formato = formato; }
    public Long getTamanhoBytes() { return tamanhoBytes; }
    public void setTamanhoBytes(Long tamanhoBytes) { this.tamanhoBytes = tamanhoBytes; }
    public LocalDateTime getGeradoEm() { return geradoEm; }
    public void setGeradoEm(LocalDateTime geradoEm) { this.geradoEm = geradoEm; }
}