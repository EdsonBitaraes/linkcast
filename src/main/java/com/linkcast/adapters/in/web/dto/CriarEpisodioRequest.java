package com.linkcast.adapters.in.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Requisição para criar um novo episódio")
public record CriarEpisodioRequest(
    
    @Schema(description = "Título do episódio", example = "Episódio 1: Introdução")
    String titulo,
    
    @Schema(description = "Descrição do episódio", example = "Neste episódio discutimos os conceitos básicos...")
    String descricao
) {}