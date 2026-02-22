package com.linkcast.adapters.in.web.dto;

import com.linkcast.domain.enums.StatusEpisodio;
import com.linkcast.domain.model.Episodio;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "Resposta de episódio")
public record EpisodioResponse(
    
    @Schema(description = "ID do episódio", example = "550e8400-e29b-41d4-a716-446655440000")
    UUID id,
    
    @Schema(description = "ID do conteúdo", example = "550e8400-e29b-41d4-a716-446655440001")
    UUID conteudoId,
    
    @Schema(description = "Título do episódio", example = "Episódio 1: Introdução")
    String titulo,
    
    @Schema(description = "Descrição do episódio", example = "Neste episódio discutimos os conceitos básicos...")
    String descricao,
    
    @Schema(description = "Duração em segundos", example = "1800")
    Integer duracaoSegundos,
    
    @Schema(description = "Status do episódio", example = "GERANDO")
    StatusEpisodio status,
    
    @Schema(description = "Data de criação", example = "2024-01-15T10:30:00")
    LocalDateTime criadoEm
) {
    public static EpisodioResponse from(Episodio episodio) {
        return new EpisodioResponse(
            episodio.getId(),
            episodio.getConteudoId(),
            episodio.getTitulo(),
            episodio.getDescricao(),
            episodio.getDuracaoSegundos(),
            episodio.getStatus(),
            episodio.getCriadoEm()
        );
    }
}