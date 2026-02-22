package com.linkcast.application.service;

import com.linkcast.application.port.in.CriarConteudoUseCase;
import com.linkcast.application.port.out.ConteudoRepositoryPort;
import com.linkcast.domain.model.Conteudo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CriarConteudoService implements CriarConteudoUseCase {
    
    private final ConteudoRepositoryPort conteudoRepository;

    public CriarConteudoService(ConteudoRepositoryPort conteudoRepository) {
        this.conteudoRepository = conteudoRepository;
    }

    @Override
    public Conteudo executar(CriarConteudoCommand command) {
        Conteudo conteudo = Conteudo.criar(
            command.usuarioId(),
            command.titulo(),
            command.urlOrigem(),
            command.textoOriginal()
        );
        
        return conteudoRepository.salvar(conteudo);
    }
}