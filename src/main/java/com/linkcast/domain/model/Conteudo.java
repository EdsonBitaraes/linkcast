package com.linkcast.domain.model;

import com.linkcast.domain.enums.StatusConteudo;
import com.linkcast.domain.exception.RegraDeNegocioException;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Conteudo {
    
    private final UUID id;
    private final UUID usuarioId;
    private String titulo;
    private final String urlOrigem;
    private String textoOriginal;
    private StatusConteudo status;
    private final LocalDateTime criadoEm;

    public Conteudo(UUID id, UUID usuarioId, String titulo, String urlOrigem, 
                    String textoOriginal, StatusConteudo status, LocalDateTime criadoEm) {
        this.id = Objects.requireNonNull(id, "ID não pode ser nulo");
        this.usuarioId = Objects.requireNonNull(usuarioId, "Usuário ID não pode ser nulo");
        this.urlOrigem = Objects.requireNonNull(urlOrigem, "URL de origem não pode ser nula");
        this.status = Objects.requireNonNull(status, "Status não pode ser nulo");
        this.criadoEm = Objects.requireNonNull(criadoEm, "Data de criação não pode ser nula");
        this.titulo = titulo;
        this.textoOriginal = textoOriginal;
        validar();
    }

    public static Conteudo criar(UUID usuarioId, String titulo, String urlOrigem, String textoOriginal) {
        return new Conteudo(
            UUID.randomUUID(),
            usuarioId,
            titulo,
            urlOrigem,
            textoOriginal,
            StatusConteudo.IMPORTADO,
            LocalDateTime.now()
        );
    }

    public void iniciarProcessamento() {
        if (status == StatusConteudo.ERRO) {
            throw new RegraDeNegocioException("Não é possível processar conteúdo com status ERRO");
        }
        this.status = StatusConteudo.PROCESSANDO;
    }

    public void marcarComoPronto() {
        this.status = StatusConteudo.PRONTO;
    }

    public void marcarComoErro() {
        this.status = StatusConteudo.ERRO;
    }

    public boolean podeGerarEpisodio() {
        return status.podeGerarEpisodio();
    }

    private void validar() {
        if (urlOrigem.trim().isEmpty()) {
            throw new IllegalArgumentException("URL de origem não pode ser vazia");
        }
    }

    public UUID getId() { return id; }
    public UUID getUsuarioId() { return usuarioId; }
    public String getTitulo() { return titulo; }
    public String getUrlOrigem() { return urlOrigem; }
    public String getTextoOriginal() { return textoOriginal; }
    public StatusConteudo getStatus() { return status; }
    public LocalDateTime getCriadoEm() { return criadoEm; }

    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setTextoOriginal(String textoOriginal) { this.textoOriginal = textoOriginal; }
    public void setStatus(StatusConteudo status) { this.status = status; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conteudo conteudo = (Conteudo) o;
        return Objects.equals(id, conteudo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}