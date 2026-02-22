package com.linkcast.application.port.out;

import com.linkcast.domain.model.Usuario;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepositoryPort {
    
    Usuario salvar(Usuario usuario);
    
    Optional<Usuario> buscarPorId(UUID id);
    
    Optional<Usuario> buscarPorEmail(String email);
}