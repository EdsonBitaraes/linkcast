package com.linkcast.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Audio {
    
    private final UUID id;
    private final UUID episodioId;
    private final String urlArquivo;
    private final String formato;
    private Long tamanhoBytes;
    private final LocalDateTime geradoEm;

    public Audio(UUID id, UUID episodioId, String urlArquivo, String formato,
                 Long tamanhoBytes, LocalDateTime geradoEm) {
        this.id = Objects.requireNonNull(id, "ID não pode ser nulo");
        this.episodioId = Objects.requireNonNull(episodioId, "Episódio ID não pode ser nulo");
        this.urlArquivo = Objects.requireNonNull(urlArquivo, "URL do arquivo não pode ser nula");
        this.formato = Objects.requireNonNull(formato, "Formato não pode ser nulo");
        this.geradoEm = Objects.requireNonNull(geradoEm, "Data de geração não pode ser nula");
        this.tamanhoBytes = tamanhoBytes;
        validar();
    }

    public static Audio criar(UUID episodioId, String urlArquivo, String formato, Long tamanhoBytes) {
        return new Audio(
            UUID.randomUUID(),
            episodioId,
            urlArquivo,
            formato,
            tamanhoBytes,
            LocalDateTime.now()
        );
    }

    private void validar() {
        if (urlArquivo.trim().isEmpty()) {
            throw new IllegalArgumentException("URL do arquivo não pode ser vazia");
        }
        if (formato.trim().isEmpty()) {
            throw new IllegalArgumentException("Formato não pode ser vazio");
        }
    }

    public UUID getId() { return id; }
    public UUID getEpisodioId() { return episodioId; }
    public String getUrlArquivo() { return urlArquivo; }
    public String getFormato() { return formato; }
    public Long getTamanhoBytes() { return tamanhoBytes; }
    public LocalDateTime getGeradoEm() { return geradoEm; }

    public void setTamanhoBytes(Long tamanhoBytes) { this.tamanhoBytes = tamanhoBytes; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Audio audio = (Audio) o;
        return Objects.equals(id, audio.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}