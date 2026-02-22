package com.linkcast.adapters.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Resposta padrão de erro")
public record ErrorResponse(
    
    @Schema(description = "Data e hora do erro", example = "2024-01-15T10:30:00")
    LocalDateTime timestamp,
    
    @Schema(description = "Código do status HTTP", example = "404")
    int status,
    
    @Schema(description = "Mensagem de erro", example = "Conteúdo não encontrado")
    String erro
) {
    public static ErrorResponse of(int status, String erro) {
        return new ErrorResponse(LocalDateTime.now(), status, erro);
    }
}