package com.linkcast.adapters.out.persistence;

import com.linkcast.adapters.out.persistence.entity.UsuarioJpaEntity;
import com.linkcast.adapters.out.persistence.mapper.UsuarioMapper;
import com.linkcast.adapters.out.persistence.repository.UsuarioJpaRepository;
import com.linkcast.application.port.out.UsuarioRepositoryPort;
import com.linkcast.domain.model.Usuario;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Component
@Transactional
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {
    
    private final UsuarioJpaRepository jpaRepository;

    public UsuarioRepositoryAdapter(UsuarioJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        UsuarioJpaEntity entity = UsuarioMapper.toEntity(usuario);
        UsuarioJpaEntity savedEntity = jpaRepository.save(entity);
        return UsuarioMapper.toDomain(savedEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorId(UUID id) {
        return jpaRepository.findById(id)
            .map(UsuarioMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorEmail(String email) {
        return jpaRepository.findByEmail(email)
            .map(UsuarioMapper::toDomain);
    }
}