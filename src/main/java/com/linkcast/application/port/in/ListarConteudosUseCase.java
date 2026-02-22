package com.linkcast.application.port.in;

import com.linkcast.domain.model.Conteudo;

import java.util.List;
import java.util.UUID;

public interface ListarConteudosUseCase {
    
    List<Conteudo> executarTodos();
    
    List<Conteudo> executarPorUsuario(UUID usuarioId);
}