package com.linkcast.application.service;

import com.linkcast.application.port.in.CriarConteudoUseCase;
import com.linkcast.application.port.out.ConteudoRepositoryPort;
import com.linkcast.application.port.out.EpisodioRepositoryPort;
import com.linkcast.application.port.out.ScrapingPort;
import com.linkcast.domain.model.Conteudo;
import com.linkcast.domain.model.Episodio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CriarConteudoService implements CriarConteudoUseCase {
    
    private final ConteudoRepositoryPort conteudoRepository;
    private final EpisodioRepositoryPort episodioRepository;
    private final ScrapingPort scrapingPort;

    public CriarConteudoService(ConteudoRepositoryPort conteudoRepository, 
                                EpisodioRepositoryPort episodioRepository,
                                ScrapingPort scrapingPort) {
        this.conteudoRepository = conteudoRepository;
        this.episodioRepository = episodioRepository;
        this.scrapingPort = scrapingPort;
    }

    @Override
    public CriarConteudoResult executar(CriarConteudoCommand command) {
        // Extrai o texto da URL via scraping
        String textoExtraido = scrapingPort.extrairTexto(command.urlOrigem());
        
        // Usa o texto extraído ou o texto original fornecido
        String textoFinal = command.textoOriginal() != null && !command.textoOriginal().isBlank() 
                ? command.textoOriginal() 
                : textoExtraido;
        
        // Cria o conteúdo com o texto extraído
        Conteudo conteudo = Conteudo.criar(
            command.usuarioId(),
            command.titulo(),
            command.urlOrigem(),
            textoFinal
        );
        
        Conteudo conteudoSalvo = conteudoRepository.salvar(conteudo);
        
        // Cria o episódio automaticamente
        Episodio episodio = Episodio.criar(
            conteudoSalvo.getId(),
            command.titulo(),
            null
        );
        
        Episodio episodioSalvo = episodioRepository.salvar(episodio);
        
        return new CriarConteudoResult(conteudoSalvo, episodioSalvo);
    }
}