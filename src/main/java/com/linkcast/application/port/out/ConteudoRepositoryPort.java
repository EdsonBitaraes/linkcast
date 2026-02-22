package com.linkcast.application.port.out;

import com.linkcast.domain.model.Conteudo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ConteudoRepositoryPort {
    
    Conteudo salvar(Conteudo conteudo);
    
    Optional<Conteudo> buscarPorId(UUID id);
    
    List<Conteudo> buscarTodos();
    
    List<Conteudo> buscarPorUsuarioId(UUID usuarioId);
    
    void deletar(UUID id);
}