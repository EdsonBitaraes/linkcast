package com.linkcast.application.port.out;

import com.linkcast.domain.model.Audio;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AudioRepositoryPort {
    
    Audio salvar(Audio audio);
    
    Optional<Audio> buscarPorId(UUID id);
    
    List<Audio> buscarPorEpisodioId(UUID episodioId);
    
    void deletar(UUID id);
}