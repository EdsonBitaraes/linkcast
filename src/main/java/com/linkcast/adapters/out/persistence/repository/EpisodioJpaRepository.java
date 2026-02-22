package com.linkcast.adapters.out.persistence.repository;

import com.linkcast.adapters.out.persistence.entity.EpisodioJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EpisodioJpaRepository extends JpaRepository<EpisodioJpaEntity, UUID> {
    
    List<EpisodioJpaEntity> findByConteudoId(UUID conteudoId);
}