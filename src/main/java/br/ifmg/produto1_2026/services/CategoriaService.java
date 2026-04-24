package br.ifmg.produto1_2026.services;

import br.ifmg.produto1_2026.dto.CategoriaDTO;
import br.ifmg.produto1_2026.dto.UsuarioDTO;
import br.ifmg.produto1_2026.entities.Categoria;
import br.ifmg.produto1_2026.repositories.CategoriaRepository;
import br.ifmg.produto1_2026.services.exceptions.ErroNoBancoDeDados;
import br.ifmg.produto1_2026.services.exceptions.ResourceNotFound;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public Page<CategoriaDTO> findAll(Pageable pageRequest){
        // lista com os dados do bd
        Page<Categoria> categorias = categoriaRepository.findAll(pageRequest);

        return categorias.map(CategoriaDTO::new);
    }

    @Transactional(readOnly = true)
    public CategoriaDTO findById(Long id) {
        Optional<Categoria> opt = categoriaRepository.findById(id);
        Categoria categoria = opt.orElseThrow(() -> new ResourceNotFound("Categoria não encontrada"));
        return new CategoriaDTO(categoria);
    }

    @Transactional
    public CategoriaDTO insert(CategoriaDTO dto) {

        Categoria entity = new Categoria();
        entity.setNome(dto.getNome());

        Categoria nova = categoriaRepository.save(entity);
        return new CategoriaDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        if(!categoriaRepository.existsById(id)){
            throw new ResourceNotFound("Categoria não encontrada");
        }

        try {
            categoriaRepository.deleteById(id);
        }
        catch(DataIntegrityViolationException e) {
            throw new ErroNoBancoDeDados(e.getMessage());
        }
    }

    public CategoriaDTO update(Long id, CategoriaDTO dto) {

        if (!categoriaRepository.existsById(id)){
            throw new ResourceNotFound("Categoria não encontrada");
        }

        Categoria entity = categoriaRepository.getReferenceById(id);

        entity.setNome(dto.getNome()); // sobrescrevi o nome antigo
        entity = categoriaRepository.save(entity);

        return new CategoriaDTO(entity);

    }
}