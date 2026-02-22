package com.linkcast.application.port.in;

import com.linkcast.domain.model.Conteudo;
import com.linkcast.domain.model.Episodio;

import java.util.UUID;

public interface CriarConteudoUseCase {
    
    CriarConteudoResult executar(CriarConteudoCommand command);
    
    record CriarConteudoCommand(
        UUID usuarioId,
        String titulo,
        String urlOrigem,
        String textoOriginal
    ) {}
    
    record CriarConteudoResult(
        Conteudo conteudo,
        Episodio episodio
    ) {}
}