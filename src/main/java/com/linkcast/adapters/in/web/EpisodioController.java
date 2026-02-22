package com.linkcast.adapters.in.web;

import com.linkcast.adapters.in.web.dto.EpisodioResponse;
import com.linkcast.application.port.out.EpisodioRepositoryPort;
import com.linkcast.domain.model.Episodio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/conteudos")
@Tag(name = "Episódios", description = "Endpoints para listar episódios")
public class EpisodioController {
    
    private final EpisodioRepositoryPort episodioRepository;

    public EpisodioController(EpisodioRepositoryPort episodioRepository) {
        this.episodioRepository = episodioRepository;
    }

    @GetMapping("/{conteudoId}/episodios")
    @Operation(summary = "Listar episódios", description = "Lista todos os episódios de um conteúdo")
    public ResponseEntity<List<EpisodioResponse>> listarPorConteudo(
            @Parameter(description = "ID do conteúdo")
            @PathVariable UUID conteudoId) {
        
        List<Episodio> episodios = episodioRepository.buscarPorConteudoId(conteudoId);
        
        List<EpisodioResponse> response = episodios.stream()
            .map(EpisodioResponse::from)
            .toList();
        
        return ResponseEntity.ok(response);
    }
}