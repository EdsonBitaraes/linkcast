package com.linkcast.domain.enums;

public enum StatusConteudo {
    IMPORTADO,
    PROCESSANDO,
    PRONTO,
    ERRO;

    public boolean podeGerarEpisodio() {
        return this != ERRO;
    }
}