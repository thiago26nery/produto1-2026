package br.ifmg.produto1_2026.services;

import br.ifmg.produto1_2026.dto.CategoriaDTO;
import br.ifmg.produto1_2026.dto.ProdutoDTO;
import br.ifmg.produto1_2026.entities.Categoria;
import br.ifmg.produto1_2026.entities.Produto;
import br.ifmg.produto1_2026.repositories.CategoriaRepository;
import br.ifmg.produto1_2026.repositories.ProdutoRepository;
import br.ifmg.produto1_2026.services.exceptions.ErroNoBancoDeDados;
import br.ifmg.produto1_2026.services.exceptions.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public ProdutoDTO findById(Long id) {
        return produtoRepository.findById(id).map(ProdutoDTO::new)
                .orElseThrow(() -> new ResourceNotFound("Produto não encontrada"));
    }

    @Transactional(readOnly = true)
    public List<ProdutoDTO> findAll() {
        return produtoRepository.findAll()
                .stream()
                .map(ProdutoDTO::new)
                .collect(Collectors.toList());
    }
    @Transactional
    public ProdutoDTO insert(ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto.setDescricao(dto.getDescricao());
        produto.setImgUrl(dto.getImgUrl());

        for (CategoriaDTO catDto : dto.getCategorias()) {
            Categoria cat = categoriaRepository.getReferenceById(catDto.getId());
            produto.getCategorias().add(cat);
        }
        
        Produto nova = produtoRepository.save(produto);
        return new ProdutoDTO(produto);
    }

    @Transactional
    public void delete(Long id) {
        if(!produtoRepository.existsById(id)){
            throw new ResourceNotFound("Produto não encontrado");
        }

        try {
            produtoRepository.deleteById(id);
        }
        catch(DataIntegrityViolationException e) {
            throw new ErroNoBancoDeDados(e.getMessage());
        }
    }

    public ProdutoDTO update(Long id, ProdutoDTO dto) {

        if (!produtoRepository.existsById(id)){
            throw new ResourceNotFound("Produto não encontrada");
        }

        Produto produto = produtoRepository.getReferenceById(id);
        produto.setNome(dto.getNome()); // sobrescrevi o nome antigo
        produto.setDescricao(dto.getDescricao());
        produto.setImgUrl(dto.getImgUrl());
        produto.setPreco(dto.getPreco());
        produto = produtoRepository.save(produto);
        return new ProdutoDTO(produto);


    }
}