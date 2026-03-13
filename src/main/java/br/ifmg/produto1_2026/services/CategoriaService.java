package br.ifmg.produto1_2026.services;

import br.ifmg.produto1_2026.dto.CategoriaDTO;
import br.ifmg.produto1_2026.entities.Categoria;
import br.ifmg.produto1_2026.repositories.CategoriaRepository;
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
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public CategoriaDTO findById(Long id) {
        return categoriaRepository.findById(id).map(CategoriaDTO::new)
                .orElseThrow(() -> new ResourceNotFound("Categoria não encontrada"));
    }

    @Transactional(readOnly = true)
    public List<CategoriaDTO> findAll() {
         return categoriaRepository.findAll()
             .stream()
             .map(CategoriaDTO::new)
             .collect(Collectors.toList());
    }
    @Transactional
    public static CategoriaDTO insert(CategoriaDTO dto) {
        Categoria entity = new Categoria();
        entity.setNome(dto.getNome());

        Categoria nova = CategoriaRepository.save(entity);
        return new CategoriaDTO(entity);
    }

    public CategoriaDTO delete(Long id) {
        if(!CategoriaRepository.existsById(id)){
            throw new RegistroNaoEncontrado("Categoria não encontrada");
        }

        try {
            categoriaRepository.deleteById(id);
        }
        catch(DataIntegrityViolationException e) {
            throw new ErroNoBancoDeDados(e.getMessage());
        }
    }
}