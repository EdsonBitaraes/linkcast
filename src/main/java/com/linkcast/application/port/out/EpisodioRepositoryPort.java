package com.linkcast.application.port.out;

import com.linkcast.domain.model.Episodio;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EpisodioRepositoryPort {
    
    Episodio salvar(Episodio episodio);
    
    Optional<Episodio> buscarPorId(UUID id);
    
    List<Episodio> buscarPorConteudoId(UUID conteudoId);
    
    void deletar(UUID id);
}