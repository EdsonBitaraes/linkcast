package com.linkcast.adapters.out.persistence;

import com.linkcast.adapters.out.persistence.entity.AudioJpaEntity;
import com.linkcast.adapters.out.persistence.mapper.AudioMapper;
import com.linkcast.adapters.out.persistence.repository.AudioJpaRepository;
import com.linkcast.application.port.out.AudioRepositoryPort;
import com.linkcast.domain.model.Audio;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@Transactional
public class AudioRepositoryAdapter implements AudioRepositoryPort {
    
    private final AudioJpaRepository jpaRepository;

    public AudioRepositoryAdapter(AudioJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Audio salvar(Audio audio) {
        AudioJpaEntity entity = AudioMapper.toEntity(audio);
        AudioJpaEntity savedEntity = jpaRepository.save(entity);
        return AudioMapper.toDomain(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Audio> buscarPorId(UUID id) {
        return jpaRepository.findById(id)
            .map(AudioMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Audio> buscarPorEpisodioId(UUID episodioId) {
        return jpaRepository.findByEpisodioId(episodioId).stream()
            .map(AudioMapper::toDomain)
            .toList();
    }

    @Override
    public void deletar(UUID id) {
        jpaRepository.deleteById(id);
    }
}