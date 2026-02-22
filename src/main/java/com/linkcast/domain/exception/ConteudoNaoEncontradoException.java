package com.linkcast.domain.exception;

import java.util.UUID;

public class ConteudoNaoEncontradoException extends RuntimeException {
    
    public ConteudoNaoEncontradoException(UUID id) {
        super("Conteúdo não encontrado com ID: " + id);
    }
    
    public ConteudoNaoEncontradoException(String message) {
        super(message);
    }
}