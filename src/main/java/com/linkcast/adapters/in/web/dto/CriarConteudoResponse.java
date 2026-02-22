package com.linkcast.adapters.in.web.dto;

import com.linkcast.domain.enums.StatusConteudo;
import com.linkcast.domain.enums.StatusEpisodio;
import com.linkcast.domain.model.Conteudo;
import com.linkcast.domain.model.Episodio;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "Resposta de criação de conteúdo com episódio")
public record CriarConteudoResponse(
    
    @Schema(description = "Conteúdo criado")
    ConteudoInfo conteudo,
    
    @Schema(description = "Episódio gerado")
    EpisodioInfo episodio
) {
    public static CriarConteudoResponse from(Conteudo conteudo, Episodio episodio) {
        return new CriarConteudoResponse(
            new ConteudoInfo(
                conteudo.getId(),
                conteudo.getUsuarioId(),
                conteudo.getTitulo(),
                conteudo.getUrlOrigem(),
                conteudo.getTextoOriginal(),
                conteudo.getStatus(),
                conteudo.getCriadoEm()
            ),
            new EpisodioInfo(
                episodio.getId(),
                episodio.getConteudoId(),
                episodio.getTitulo(),
                episodio.getDescricao(),
                episodio.getDuracaoSegundos(),
                episodio.getStatus(),
                episodio.getCriadoEm()
            )
        );
    }
    
    public record ConteudoInfo(
        @Schema(description = "ID do conteúdo")
        UUID id,
        
        @Schema(description = "ID do usuário")
        UUID usuarioId,
        
        @Schema(description = "Título do conteúdo")
        String titulo,
        
        @Schema(description = "URL de origem")
        String urlOrigem,
        
        @Schema(description = "Texto original")
        String textoOriginal,
        
        @Schema(description = "Status do conteúdo")
        StatusConteudo status,
        
        @Schema(description = "Data de criação")
        LocalDateTime criadoEm
    ) {}
    
    public record EpisodioInfo(
        @Schema(description = "ID do episódio")
        UUID id,
        
        @Schema(description = "ID do conteúdo")
        UUID conteudoId,
        
        @Schema(description = "Título do episódio")
        String titulo,
        
        @Schema(description = "Descrição")
        String descricao,
        
        @Schema(description = "Duração em segundos")
        Integer duracaoSegundos,
        
        @Schema(description = "Status do episódio")
        StatusEpisodio status,
        
        @Schema(description = "Data de criação")
        LocalDateTime criadoEm
    ) {}
}