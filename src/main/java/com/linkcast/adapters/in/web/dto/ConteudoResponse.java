package com.linkcast.adapters.in.web.dto;

import com.linkcast.domain.enums.StatusConteudo;
import com.linkcast.domain.model.Conteudo;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "Resposta de conteúdo")
public record ConteudoResponse(
    
    @Schema(description = "ID do conteúdo", example = "550e8400-e29b-41d4-a716-446655440000")
    UUID id,
    
    @Schema(description = "ID do usuário", example = "550e8400-e29b-41d4-a716-446655440001")
    UUID usuarioId,
    
    @Schema(description = "Título do conteúdo", example = "Introdução ao Spring Boot")
    String titulo,
    
    @Schema(description = "URL de origem do conteúdo", example = "https://exemplo.com/artigo")
    String urlOrigem,
    
    @Schema(description = "Texto original do conteúdo", example = "Este é um artigo sobre Spring Boot...")
    String textoOriginal,
    
    @Schema(description = "Status do conteúdo", example = "IMPORTADO")
    StatusConteudo status,
    
    @Schema(description = "Data de criação", example = "2024-01-15T10:30:00")
    LocalDateTime criadoEm
) {
    public static ConteudoResponse from(Conteudo conteudo) {
        return new ConteudoResponse(
            conteudo.getId(),
            conteudo.getUsuarioId(),
            conteudo.getTitulo(),
            conteudo.getUrlOrigem(),
            conteudo.getTextoOriginal(),
            conteudo.getStatus(),
            conteudo.getCriadoEm()
        );
    }
}