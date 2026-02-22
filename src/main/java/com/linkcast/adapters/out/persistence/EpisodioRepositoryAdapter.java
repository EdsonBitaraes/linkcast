package com.linkcast.adapters.out.persistence;

import com.linkcast.adapters.out.persistence.entity.EpisodioJpaEntity;
import com.linkcast.adapters.out.persistence.mapper.EpisodioMapper;
import com.linkcast.adapters.out.persistence.repository.EpisodioJpaRepository;
import com.linkcast.application.port.out.EpisodioRepositoryPort;
import com.linkcast.domain.model.Episodio;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@Transactional
public class EpisodioRepositoryAdapter implements EpisodioRepositoryPort {
    
    private final EpisodioJpaRepository jpaRepository;

    public EpisodioRepositoryAdapter(EpisodioJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Episodio salvar(Episodio episodio) {
        EpisodioJpaEntity entity = EpisodioMapper.toEntity(episodio);
        EpisodioJpaEntity savedEntity = jpaRepository.save(entity);
        return EpisodioMapper.toDomain(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Episodio> buscarPorId(UUID id) {
        return jpaRepository.findById(id)
            .map(EpisodioMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Episodio> buscarPorConteudoId(UUID conteudoId) {
        return jpaRepository.findByConteudoId(conteudoId).stream()
            .map(EpisodioMapper::toDomain)
            .toList();
    }

    @Override
    public void deletar(UUID id) {
        jpaRepository.deleteById(id);
    }
}