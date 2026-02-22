package com.linkcast.application.service;

import com.linkcast.application.port.in.BuscarConteudoPorIdUseCase;
import com.linkcast.application.port.out.ConteudoRepositoryPort;
import com.linkcast.domain.model.Conteudo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class BuscarConteudoPorIdService implements BuscarConteudoPorIdUseCase {
    
    private final ConteudoRepositoryPort conteudoRepository;

    public BuscarConteudoPorIdService(ConteudoRepositoryPort conteudoRepository) {
        this.conteudoRepository = conteudoRepository;
    }

    @Override
    public Optional<Conteudo> executar(UUID id) {
        return conteudoRepository.buscarPorId(id);
    }
}