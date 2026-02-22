package com.linkcast.application.port.in;

import com.linkcast.domain.model.Conteudo;

import java.util.UUID;

public interface CriarConteudoUseCase {
    
    Conteudo executar(CriarConteudoCommand command);
    
    record CriarConteudoCommand(
        UUID usuarioId,
        String titulo,
        String urlOrigem,
        String textoOriginal
    ) {}
}