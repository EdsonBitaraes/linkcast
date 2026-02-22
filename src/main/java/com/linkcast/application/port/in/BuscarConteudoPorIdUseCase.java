package com.linkcast.application.port.in;

import com.linkcast.domain.model.Conteudo;

import java.util.Optional;
import java.util.UUID;

public interface BuscarConteudoPorIdUseCase {
    
    Optional<Conteudo> executar(UUID id);
}