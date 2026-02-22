package com.linkcast.application.service;

import com.linkcast.application.port.in.ListarConteudosUseCase;
import com.linkcast.application.port.out.ConteudoRepositoryPort;
import com.linkcast.domain.model.Conteudo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class ListarConteudosService implements ListarConteudosUseCase {
    
    private final ConteudoRepositoryPort conteudoRepository;

    public ListarConteudosService(ConteudoRepositoryPort conteudoRepository) {
        this.conteudoRepository = conteudoRepository;
    }

    @Override
    public List<Conteudo> executarTodos() {
        return conteudoRepository.buscarTodos();
    }

    @Override
    public List<Conteudo> executarPorUsuario(UUID usuarioId) {
        return conteudoRepository.buscarPorUsuarioId(usuarioId);
    }
}