package com.linkcast.application.port.out;

public interface ScrapingPort {
    
    /**
     * Extrai o texto relevante de uma URL.
     *
     * @param url URL da página a ser extraída
     * @return texto limpo extraído da página
     * @throws com.linkcast.domain.exception.RegraDeNegocioException se a URL for inválida ou inacessível
     */
    String extrairTexto(String url);
}