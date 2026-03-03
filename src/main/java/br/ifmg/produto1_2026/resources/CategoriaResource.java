package br.ifmg.produto1_2026.resources;

import br.ifmg.produto1_2026.entities.Categoria;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaResource {
    @GetMapping
    public ResponseEntity<List<Categoria>> categoria(){

        Categoria categoria = new Categoria(1L,"notebook");
        Categoria categoria2 = new Categoria(2L,"celular");
        Categoria categoria3 = new Categoria(3L,"livros");

        List<Categoria> categorias = new ArrayList<Categoria>();
        categorias.add(categoria);
        categorias.add(categoria2);
        categorias.add(categoria3);

        return ResponseEntity.ok().body(categorias);
    }
}
