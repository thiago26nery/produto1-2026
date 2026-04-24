package br.ifmg.produto1_2026.resources;

import br.ifmg.produto1_2026.dto.UsuarioDTO;
import br.ifmg.produto1_2026.services.UsuarioService;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.awt.print.Pageable;
import java.net.URI;

@RestController
@RequestMapping("/usuario")
public class UsuarioResource {
    @Autowired
    private UsuarioService usuarioService;


    @GetMapping
    public ResponseEntity<Page<UsuarioDTO>> usuarios(Pageable pageable){

        Page<UsuarioDTO> usuarios =
                usuarioService.findAll(pageable);
        return ResponseEntity.ok().body(usuarios);
    };

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> usuario(@PathVariable Long id){
        UsuarioDTO dto= usuarioService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> insert(
            @RequestBody UsuarioDTO dto){
        //inserindo no BD e pegando o objeto inserido.
        UsuarioDTO retorno
                = usuarioService.insert(dto);
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
    public ResponseEntity<Void> delete(@PathVariable Long id){

        usuarioService.delete(id);


        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(
            @PathVariable Long id,
            @RequestBody UsuarioDTO dto){

        UsuarioDTO retorno =  usuarioService.update(id,dto);

        return  ResponseEntity.ok().body(retorno);
    }

}
