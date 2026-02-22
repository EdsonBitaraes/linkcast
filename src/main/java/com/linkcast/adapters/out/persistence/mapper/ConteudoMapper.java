package com.linkcast.adapters.out.persistence.mapper;

import com.linkcast.adapters.out.persistence.entity.ConteudoJpaEntity;
import com.linkcast.domain.model.Conteudo;

public final class ConteudoMapper {
    
    private ConteudoMapper() {}
    
    public static Conteudo toDomain(ConteudoJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Conteudo(
            entity.getId(),
            entity.getUsuarioId(),
            entity.getTitulo(),
            entity.getUrlOrigem(),
            entity.getTextoOriginal(),
            entity.getStatus(),
            entity.getCriadoEm()
        );
    }
    
    public static ConteudoJpaEntity toEntity(Conteudo conteudo) {
        if (conteudo == null) {
            return null;
        }
        return new ConteudoJpaEntity(
            conteudo.getId(),
            conteudo.getUsuarioId(),
            conteudo.getTitulo(),
            conteudo.getUrlOrigem(),
            conteudo.getTextoOriginal(),
            conteudo.getStatus(),
            conteudo.getCriadoEm()
        );
    }
}