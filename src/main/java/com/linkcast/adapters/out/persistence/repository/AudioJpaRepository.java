package com.linkcast.adapters.out.persistence.repository;

import com.linkcast.adapters.out.persistence.entity.AudioJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AudioJpaRepository extends JpaRepository<AudioJpaEntity, UUID> {
    
    List<AudioJpaEntity> findByEpisodioId(UUID episodioId);
}