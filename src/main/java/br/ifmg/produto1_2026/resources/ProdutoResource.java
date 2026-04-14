package br.ifmg.produto1_2026.resources;

import br.ifmg.produto1_2026.dto.ProdutoDTO;
import br.ifmg.produto1_2026.services.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.awt.print.Pageable;
import java.net.URI;

@RestController
@RequestMapping("/produto")
@Tag(name="produtos",description = "Essa API é responsável por gerenciar produtos na plataforma.")
public class ProdutoResource {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(produtoService.findById(id));
    };

    @GetMapping
    @Operation(
            description = "A plataforma precisa disponibilizar um cadastro e produtos...",
            summary = "Endpoint para inserir um produto" ,
            responses = {
                    @ApiResponse(description = "Registro Criado", responseCode = "201"),
                    @ApiResponse(description = "Requisição mal feita", responseCode = "400", content = {}), // requisição mal feita
                    @ApiResponse(description = "Não autorizado", responseCode = "401"),
                    @ApiResponse(description = "Proibido no seu perfil", responseCode = "403"),
                    @ApiResponse(description = "Erro ao processar", responseCode = "422"),
                    @ApiResponse(description = "Erro interno no servidor", responseCode = "500"),
            }
    )
    public ResponseEntity<Page<ProdutoDTO>> produtos(Pageable pageable)(
//            @RequestParam(value = "page", defaultValue = "0") Integer page,
//            @RequestParam(value = "linerPerPage", defaultValue = "10") Integer linerPerPage,
//            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
//            @RequestParam(value = "sort", defaultValue = "id") String sort
            Pageable pageable
    ) {
        return ResponseEntity.ok().body(produtoService.findAll(pageable));
    };

    @PostMapping(produces = "application/json")
    @Operation(
            description = "A plataforma precisa disponibilizar um cadastro e produtos...",
            summary = "Endpoint para inserir um produto" ,
            responses = {
                    @ApiResponse(description = "Registro Criado", responseCode = "201"),
                    @ApiResponse(description = "Requisição mal feita", responseCode = "400", content = {}), // requisição mal feita
                    @ApiResponse(description = "Não autorizado", responseCode = "401"),
                    @ApiResponse(description = "Proibido no seu perfil", responseCode = "403"),
                    @ApiResponse(description = "Erro ao processar", responseCode = "422"),
                    @ApiResponse(description = "Erro interno no servidor", responseCode = "500"),
            }
    )
    public ResponseEntity<ProdutoDTO> insert(@RequestBody ProdutoDTO dto){
        ProdutoDTO categoria = produtoService.insert(dto);65
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoria.getId())
                .toUri();

        return ResponseEntity.created(location).body(categoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> update(@PathVariable Long id, @RequestBody ProdutoDTO dto) {
        ProdutoDTO categoria = produtoService.update(id, dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoria.getId())
                .toUri();

        return ResponseEntity.created(location).body(categoria);
    }

    @DeleteMapping(value = "/{id}",produces = "aplication/json")
    @Operation(
            description = "A plataforma precisa disponibilizar um cadastro e produtos...",
            summary = "Endpoint para inserir um produto" ,
            responses = {
                    @ApiResponse(description = "Retorna a informação pesquisada por ID", responseCode = "200"),
                    @ApiResponse(description = "Informação não encontrada", responseCode = "404"),
            }
    )
    public ResponseEntity<ProdutoDTO> delete(@PathVariable Long id){
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
