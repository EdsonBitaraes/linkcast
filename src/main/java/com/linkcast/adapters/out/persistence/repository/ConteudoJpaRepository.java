package com.linkcast.adapters.out.persistence.repository;

import com.linkcast.adapters.out.persistence.entity.ConteudoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ConteudoJpaRepository extends JpaRepository<ConteudoJpaEntity, UUID> {
    
    List<ConteudoJpaEntity> findByUsuarioId(UUID usuarioId);
}