package br.ifmg.produto1_2026.resources;

import br.ifmg.produto1_2026.dto.CategoriaDTO;
import br.ifmg.produto1_2026.entities.Categoria;
import br.ifmg.produto1_2026.services.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaResource {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> categorias(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                         @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
                                                         @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                                         @RequestParam(value = "sort", defaultValue = "id") String sort) {

    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable Long id) {

        return ResponseEntity.ok().body(categoriaService.findById(id));
    };

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() {

        return ResponseEntity.ok().body(categoriaService.findAll());
    };

    public ResponseEntity<CategoriaDTO> categoria(@PathVariable Long id){

        CategoriaDTO dto = categoriaService.findById(id);
        return ResponseEntity.ok().body(dto);
    }
    @PostMapping
    public ResponseEntity<CategoriaDTO> insert(@RequestBody CategoriaDTO dto){

        // inserindo o BD e pegando o objeto inserido
        CategoriaDTO retorno = categoriaService.insert(dto);
        // criando um link para acessa a categoria criada
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(retorno.getId()).toUri();
        // enviando a categoria criada
        return ResponseEntity.created(location).body(retorno);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
    public ResponseEntity<CategoriaDTO> update(@PathVariable Long id, @RequestBody CategoriaDTO dto){
        CategoriaDTO retorno = categoriaService.update(id,dto);

        return ResponseEntity.ok().body(retorno);

    }
}