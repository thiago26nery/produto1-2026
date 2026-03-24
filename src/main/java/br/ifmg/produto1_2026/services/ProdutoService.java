package br.ifmg.produto1_2026.services;

import br.ifmg.produto1_2026.dto.ProdutoDTO;
import br.ifmg.produto1_2026.entities.Produto;
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
        Produto entity = new Produto();
        entity.setNome(dto.getNome());
        entity.setPreco(dto.getPreco());
        entity.setDescricao(dto.getDescricao());
        entity.setImgUrl(dto.getImgUrl());

        Produto nova = produtoRepository.save(entity);
        return new ProdutoDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        if(!produtoRepository.existsById(id)){
            throw new ResourceNotFound("Produto não encontrada");
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

        Produto entity = produtoRepository.getReferenceById(id);
        entity.setNome(dto.getNome()); // sobrescrevi o nome antigo
        entity.setDescricao(dto.getDescricao());
        entity.setImgUrl(dto.getImgUrl());
        entity.setPreco(dto.getPreco());
        entity = produtoRepository.save(entity);
        return new ProdutoDTO(entity);


    }
}