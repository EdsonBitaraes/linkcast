package com.linkcast.adapters.out.persistence.repository;

import com.linkcast.adapters.out.persistence.entity.UsuarioJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioJpaRepository extends JpaRepository<UsuarioJpaEntity, UUID> {
    
    Optional<UsuarioJpaEntity> findByEmail(String email);
}