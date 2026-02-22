package com.linkcast.adapters.in.web;

import com.linkcast.adapters.in.web.dto.CriarEpisodioRequest;
import com.linkcast.adapters.in.web.dto.EpisodioResponse;
import com.linkcast.application.port.in.GerarEpisodioUseCase;
import com.linkcast.domain.model.Episodio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/conteudos")
@Tag(name = "Episódios", description = "Endpoints para gerenciamento de episódios")
public class EpisodioController {
    
    private final GerarEpisodioUseCase gerarEpisodioUseCase;

    public EpisodioController(GerarEpisodioUseCase gerarEpisodioUseCase) {
        this.gerarEpisodioUseCase = gerarEpisodioUseCase;
    }

    @PostMapping("/{conteudoId}/episodios")
    @Operation(summary = "Gerar episódio", description = "Gera um novo episódio para um conteúdo específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Episódio gerado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Conteúdo com status inválido para gerar episódio"),
        @ApiResponse(responseCode = "404", description = "Conteúdo não encontrado")
    })
    public ResponseEntity<EpisodioResponse> gerarEpisodio(
            @Parameter(description = "ID do conteúdo")
            @PathVariable UUID conteudoId,
            @RequestBody CriarEpisodioRequest request) {
        
        GerarEpisodioUseCase.GerarEpisodioCommand command = 
            new GerarEpisodioUseCase.GerarEpisodioCommand(
                conteudoId,
                request.titulo(),
                request.descricao()
            );
        
        Episodio episodio = gerarEpisodioUseCase.executar(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(EpisodioResponse.from(episodio));
    }
}