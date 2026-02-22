package com.linkcast.application.port.in;

import com.linkcast.domain.model.Episodio;

import java.util.UUID;

public interface GerarEpisodioUseCase {
    
    Episodio executar(GerarEpisodioCommand command);
    
    record GerarEpisodioCommand(
        UUID conteudoId,
        String titulo,
        String descricao
    ) {}
}