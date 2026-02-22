package com.linkcast.application.service;

import com.linkcast.application.port.in.CriarConteudoUseCase;
import com.linkcast.application.port.out.ConteudoRepositoryPort;
import com.linkcast.application.port.out.EpisodioRepositoryPort;
import com.linkcast.domain.model.Conteudo;
import com.linkcast.domain.model.Episodio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CriarConteudoService implements CriarConteudoUseCase {
    
    private final ConteudoRepositoryPort conteudoRepository;
    private final EpisodioRepositoryPort episodioRepository;

    public CriarConteudoService(ConteudoRepositoryPort conteudoRepository, 
                                EpisodioRepositoryPort episodioRepository) {
        this.conteudoRepository = conteudoRepository;
        this.episodioRepository = episodioRepository;
    }

    @Override
    public CriarConteudoResult executar(CriarConteudoCommand command) {
        Conteudo conteudo = Conteudo.criar(
            command.usuarioId(),
            command.titulo(),
            command.urlOrigem(),
            command.textoOriginal()
        );
        
        Conteudo conteudoSalvo = conteudoRepository.salvar(conteudo);
        
        Episodio episodio = Episodio.criar(
            conteudoSalvo.getId(),
            command.titulo(),
            null
        );
        
        Episodio episodioSalvo = episodioRepository.salvar(episodio);
        
        return new CriarConteudoResult(conteudoSalvo, episodioSalvo);
    }
}