package com.linkcast.adapters.out.persistence.mapper;

import com.linkcast.adapters.out.persistence.entity.EpisodioJpaEntity;
import com.linkcast.domain.model.Episodio;

public final class EpisodioMapper {
    
    private EpisodioMapper() {}
    
    public static Episodio toDomain(EpisodioJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Episodio(
            entity.getId(),
            entity.getConteudoId(),
            entity.getTitulo(),
            entity.getDescricao(),
            entity.getDuracaoSegundos(),
            entity.getStatus(),
            entity.getCriadoEm()
        );
    }
    
    public static EpisodioJpaEntity toEntity(Episodio episodio) {
        if (episodio == null) {
            return null;
        }
        return new EpisodioJpaEntity(
            episodio.getId(),
            episodio.getConteudoId(),
            episodio.getTitulo(),
            episodio.getDescricao(),
            episodio.getDuracaoSegundos(),
            episodio.getStatus(),
            episodio.getCriadoEm()
        );
    }
}