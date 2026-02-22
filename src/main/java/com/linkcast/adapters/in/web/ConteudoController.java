package com.linkcast.adapters.in.web;

import com.linkcast.adapters.in.web.dto.ConteudoResponse;
import com.linkcast.adapters.in.web.dto.CriarConteudoRequest;
import com.linkcast.application.port.in.BuscarConteudoPorIdUseCase;
import com.linkcast.application.port.in.CriarConteudoUseCase;
import com.linkcast.application.port.in.ListarConteudosUseCase;
import com.linkcast.domain.exception.ConteudoNaoEncontradoException;
import com.linkcast.domain.model.Conteudo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/conteudos")
@Tag(name = "Conteúdos", description = "Endpoints para gerenciamento de conteúdos")
public class ConteudoController {
    
    private final CriarConteudoUseCase criarConteudoUseCase;
    private final ListarConteudosUseCase listarConteudosUseCase;
    private final BuscarConteudoPorIdUseCase buscarConteudoPorIdUseCase;

    public ConteudoController(
            CriarConteudoUseCase criarConteudoUseCase,
            ListarConteudosUseCase listarConteudosUseCase,
            BuscarConteudoPorIdUseCase buscarConteudoPorIdUseCase) {
        this.criarConteudoUseCase = criarConteudoUseCase;
        this.listarConteudosUseCase = listarConteudosUseCase;
        this.buscarConteudoPorIdUseCase = buscarConteudoPorIdUseCase;
    }

    @PostMapping
    @Operation(summary = "Criar novo conteúdo", description = "Cria um novo conteúdo no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Conteúdo criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<ConteudoResponse> criar(@RequestBody CriarConteudoRequest request) {
        CriarConteudoUseCase.CriarConteudoCommand command = 
            new CriarConteudoUseCase.CriarConteudoCommand(
                request.usuarioId(),
                request.titulo(),
                request.urlOrigem(),
                request.textoOriginal()
            );
        
        Conteudo conteudo = criarConteudoUseCase.executar(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(ConteudoResponse.from(conteudo));
    }

    @GetMapping
    @Operation(summary = "Listar conteúdos", description = "Lista todos os conteúdos ou filtra por usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de conteúdos retornada com sucesso")
    })
    public ResponseEntity<List<ConteudoResponse>> listar(
            @Parameter(description = "ID do usuário para filtrar conteúdos")
            @RequestParam(required = false) UUID usuarioId) {
        
        List<Conteudo> conteudos = usuarioId != null 
            ? listarConteudosUseCase.executarPorUsuario(usuarioId)
            : listarConteudosUseCase.executarTodos();
        
        List<ConteudoResponse> response = conteudos.stream()
            .map(ConteudoResponse::from)
            .toList();
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar conteúdo por ID", description = "Retorna um conteúdo específico pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Conteúdo encontrado"),
        @ApiResponse(responseCode = "404", description = "Conteúdo não encontrado")
    })
    public ResponseEntity<ConteudoResponse> buscarPorId(
            @Parameter(description = "ID do conteúdo")
            @PathVariable UUID id) {
        
        return buscarConteudoPorIdUseCase.executar(id)
            .map(conteudo -> ResponseEntity.ok(ConteudoResponse.from(conteudo)))
            .orElseThrow(() -> new ConteudoNaoEncontradoException(id));
    }
}