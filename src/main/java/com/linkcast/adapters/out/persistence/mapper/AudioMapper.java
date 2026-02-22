package com.linkcast.adapters.out.persistence.mapper;

import com.linkcast.adapters.out.persistence.entity.AudioJpaEntity;
import com.linkcast.domain.model.Audio;

public final class AudioMapper {
    
    private AudioMapper() {}
    
    public static Audio toDomain(AudioJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Audio(
            entity.getId(),
            entity.getEpisodioId(),
            entity.getUrlArquivo(),
            entity.getFormato(),
            entity.getTamanhoBytes(),
            entity.getGeradoEm()
        );
    }
    
    public static AudioJpaEntity toEntity(Audio audio) {
        if (audio == null) {
            return null;
        }
        return new AudioJpaEntity(
            audio.getId(),
            audio.getEpisodioId(),
            audio.getUrlArquivo(),
            audio.getFormato(),
            audio.getTamanhoBytes(),
            audio.getGeradoEm()
        );
    }
}