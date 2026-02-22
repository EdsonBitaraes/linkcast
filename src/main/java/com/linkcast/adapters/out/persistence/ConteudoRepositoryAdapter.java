package com.linkcast.adapters.out.persistence;

import com.linkcast.adapters.out.persistence.entity.ConteudoJpaEntity;
import com.linkcast.adapters.out.persistence.mapper.ConteudoMapper;
import com.linkcast.adapters.out.persistence.repository.ConteudoJpaRepository;
import com.linkcast.application.port.out.ConteudoRepositoryPort;
import com.linkcast.domain.model.Conteudo;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@Transactional
public class ConteudoRepositoryAdapter implements ConteudoRepositoryPort {
    
    private final ConteudoJpaRepository jpaRepository;

    public ConteudoRepositoryAdapter(ConteudoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Conteudo salvar(Conteudo conteudo) {
        ConteudoJpaEntity entity = ConteudoMapper.toEntity(conteudo);
        ConteudoJpaEntity savedEntity = jpaRepository.save(entity);
        return ConteudoMapper.toDomain(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Conteudo> buscarPorId(UUID id) {
        return jpaRepository.findById(id)
            .map(ConteudoMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Conteudo> buscarTodos() {
        return jpaRepository.findAll().stream()
            .map(ConteudoMapper::toDomain)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Conteudo> buscarPorUsuarioId(UUID usuarioId) {
        return jpaRepository.findByUsuarioId(usuarioId).stream()
            .map(ConteudoMapper::toDomain)
            .toList();
    }

    @Override
    public void deletar(UUID id) {
        jpaRepository.deleteById(id);
    }
}