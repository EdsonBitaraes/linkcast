package com.linkcast.adapters.out.persistence.mapper;

import com.linkcast.adapters.out.persistence.entity.UsuarioJpaEntity;
import com.linkcast.domain.model.Usuario;

public final class UsuarioMapper {
    
    private UsuarioMapper() {}
    
    public static Usuario toDomain(UsuarioJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Usuario(
            entity.getId(),
            entity.getNome(),
            entity.getEmail(),
            entity.getSenhaHash(),
            entity.getCriadoEm()
        );
    }
    
    public static UsuarioJpaEntity toEntity(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        return new UsuarioJpaEntity(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getSenhaHash(),
            usuario.getCriadoEm()
        );
    }
}