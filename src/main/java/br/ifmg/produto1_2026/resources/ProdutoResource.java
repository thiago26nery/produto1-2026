package br.ifmg.produto1_2026.resources;


import br.ifmg.produto1_2026.dto.ProdutoDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/produto")
@Tag(name="Produtos", description = "Essa API é responsável por gerenciar produtos na plataforma.")
public class ProdutoResource {

    @Autowired
    private br.ifmg.produto1_2026.services.ProdutoService produtoService;


    @GetMapping(produces = "application/json")
    @Operation(
            summary = "Endpoint retornar todos os produto",
            description = "A plataforma precisa disponibilibiar uma listagem de produtos....",
            responses = {
                    @ApiResponse(description = "Lista retornada com sucesso", responseCode = "200"),
                    @ApiResponse(description = "Erro interno", responseCode = "500"),
            }
    )
    public ResponseEntity<Page<ProdutoDTO>> produtos(Pageable pageable){

        Page<ProdutoDTO> produtos =
                produtoService.findAll(pageable);
        return ResponseEntity.ok().body(produtos);
    };

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(
            summary = "Endpoint retornar todos os produto",
            description = "A plataforma precisa disponibilibiar uma listagem de produtos....",
            responses = {
                    @ApiResponse(description = "Retorna a informação pesquisada por ID", responseCode = "200"),
                    @ApiResponse(description = "Infomação não encontrada.", responseCode = "404"),
            }
    )
    public ResponseEntity<ProdutoDTO> produto(@PathVariable Long id){
        ProdutoDTO dto= produtoService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping(produces = "application/json")
    @Operation(
            summary = "Endpoint para inserir um produto",
            description = "A plataforma precisa disponibilibiar um cadastro e produtos....",
            responses = {
                    @ApiResponse(description = "Registro Criado", responseCode = "201"),
                    @ApiResponse(description = "Requisição mal-feita", responseCode = "400", content = {}),
                    @ApiResponse(description = "Não autorizado", responseCode = "401"),
                    @ApiResponse(description = "Proibido no seu perfil", responseCode = "403"),
                    @ApiResponse(description = "Erro ao processar", responseCode = "422"),
                    @ApiResponse(description = "Erro interno no servidor", responseCode = "500"),
            }
    )
    public ResponseEntity<ProdutoDTO> insert(
            @RequestBody @Valid ProdutoDTO dto){
        //inserindo no BD e pegando o objeto inserido.
        ProdutoDTO retorno
                = produtoService.insert(dto);
        //criando um link para acessa a categoria criada.
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(retorno.getId())
                .toUri();

        //enviando a categoria criada.
        return  ResponseEntity
                .created(location)
                .body(retorno);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Endpoint para apagar um produto",
            description = "A plataforma precisa disponibilibiar um cadastro e produtos....",
            responses = {
                    @ApiResponse(description = "Sucesso", responseCode = "204"),
                    @ApiResponse(description = "Requisição mal-feita", responseCode = "400", content = {}),
                    @ApiResponse(description = "Não autorizado", responseCode = "401"),
                    @ApiResponse(description = "Proibido no seu perfil", responseCode = "403"),
                    @ApiResponse(description = "Não encontrado", responseCode = "404"),
                    @ApiResponse(description = "Erro ao processar", responseCode = "422"),
                    @ApiResponse(description = "Erro interno no servidor", responseCode = "500"),
            }
    )
    public ResponseEntity<Void> delete(@PathVariable Long id){

        produtoService.delete(id);


        return ResponseEntity.noContent().build();
    }

    @PutMapping(value="/{id}", produces = "application/json")
    @Operation(
            summary = "Endpoint para atualizar um produto",
            description = "A plataforma precisa disponibilibiar um cadastro e produtos....",
            responses = {
                    @ApiResponse(description = "OK", responseCode = "200"),
                    @ApiResponse(description = "Requisição mal-feita", responseCode = "400", content = {}),
                    @ApiResponse(description = "Não autorizado", responseCode = "401"),
                    @ApiResponse(description = "Proibido no seu perfil", responseCode = "403"),
                    @ApiResponse(description = "Não encontrado", responseCode = "404"),
                    @ApiResponse(description = "Erro ao processar", responseCode = "422"),
                    @ApiResponse(description = "Erro interno no servidor", responseCode = "500"),
            }
    )
    public ResponseEntity<ProdutoDTO> update(
            @PathVariable Long id,
            @RequestBody  @Valid  ProdutoDTO dto){

        ProdutoDTO retorno =  produtoService.update(id,dto);

        return  ResponseEntity.ok().body(retorno);
    }



}






