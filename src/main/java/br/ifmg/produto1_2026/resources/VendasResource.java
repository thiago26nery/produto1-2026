package br.ifmg.produto1_2026.resources;

import br.ifmg.produto1_2026.entities.Usuario;
import br.ifmg.produto1_2026.services.AtivacaoClienteService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.parser.Entity;

@RestController
@RequestMapping("/venda")
public class VendasResource {

    private AtivacaoClienteService atividacaoClienteService;

    public VendasResource(AtivacaoClienteService atividacaoClienteService) {
        this.atividacaoClienteService = atividacaoClienteService;
        System.out.println("Camada de resource executada");
    }

    @PostMapping
    public ResponseEntity<String> insert(){
        Usuario usuario = new Usuario();
        usuario.setNome("Fernando");
        usuario.setTelefone("+5555555555");
        usuario.setEmail("fernando@gmail.com");
        atividacaoClienteService.ativar(usuario,"ativado...");

        return ResponseEntity.ok().body("Venda Realizada");
    }

}
