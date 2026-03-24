package br.ifmg.produto1_2026.resources;

import br.ifmg.produto1_2026.services.ProdutoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produto")
public class ProdutoResource {

    private ProdutoService produtoService;
}
