package com.linkcast.adapters.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Requisição para criar um novo conteúdo")
public record CriarConteudoRequest(
    
    @Schema(description = "ID do usuário", example = "550e8400-e29b-41d4-a716-446655440000")
    UUID usuarioId,
    
    @Schema(description = "Título do conteúdo", example = "Introdução ao Spring Boot")
    String titulo,
    
    @Schema(description = "URL de origem do conteúdo", example = "https://exemplo.com/artigo")
    String urlOrigem,
    
    @Schema(description = "Texto original do conteúdo", example = "Este é um artigo sobre Spring Boot...")
    String textoOriginal
) {}