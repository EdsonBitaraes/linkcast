package com.linkcast.application.service;

import com.linkcast.application.port.in.GerarEpisodioUseCase;
import com.linkcast.application.port.out.ConteudoRepositoryPort;
import com.linkcast.application.port.out.EpisodioRepositoryPort;
import com.linkcast.domain.exception.ConteudoNaoEncontradoException;
import com.linkcast.domain.exception.RegraDeNegocioException;
import com.linkcast.domain.model.Conteudo;
import com.linkcast.domain.model.Episodio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class GerarEpisodioService implements GerarEpisodioUseCase {
    
    private final EpisodioRepositoryPort episodioRepository;
    private final ConteudoRepositoryPort conteudoRepository;

    public GerarEpisodioService(EpisodioRepositoryPort episodioRepository, 
                                ConteudoRepositoryPort conteudoRepository) {
        this.episodioRepository = episodioRepository;
        this.conteudoRepository = conteudoRepository;
    }

    @Override
    public Episodio executar(GerarEpisodioCommand command) {
        Conteudo conteudo = conteudoRepository.buscarPorId(command.conteudoId())
            .orElseThrow(() -> new ConteudoNaoEncontradoException(command.conteudoId()));

        if (!conteudo.podeGerarEpisodio()) {
            throw new RegraDeNegocioException(
                "Não é possível gerar episódio para conteúdo com status: " + conteudo.getStatus()
            );
        }

        Episodio episodio = Episodio.criar(
            command.conteudoId(),
            command.titulo(),
            command.descricao()
        );

        return episodioRepository.salvar(episodio);
    }
}