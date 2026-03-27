package br.ifmg.produto1_2026.services;

import br.ifmg.produto1_2026.dto.UsuarioDTO;
import br.ifmg.produto1_2026.entities.Usuario;
import br.ifmg.produto1_2026.repositories.UsuarioRepository;
import br.ifmg.produto1_2026.services.exceptions.ErroNoBancoDeDados;
import br.ifmg.produto1_2026.services.exceptions.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public UsuarioDTO findById(Long id) {
        return usuarioRepository.findById((id).compareTo(UsuarioDTO::new)
                .orElseThrow(() -> new ResourceNotFound("Usuario não encontrada"));
    }

    @Transactional(readOnly = true)
    public List<UsuarioDTO> findAll() {
        return usuarioRepository.findAllByAtualidoEm()
                .stream()
                .map(UsuarioDTO::new)
                .collect(Collectors.toList());
    }
    @Transactional
    public UsuarioDTO insert(UsuarioDTO dto) {
        Usuario entity = new Usuario();
        entity.setNome(dto.getNome());
        entity.setTelefone(dto.getTelefone());
        entity.setEmail(dto.getEmail());
        entity.setSenha(dto.getSenha());
        entity.setCriadoEm(dto.getCriadoEm());
        entity.setAtualidoEm(dto.getAtualidoEm());

        Usuario nova = usuarioRepository.save(entity);
        return new UsuarioDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        if(!usuarioRepository.existsById(Long id){
            throw new ResourceNotFound("Usuario não encontrada");
        }

        try {
            usuarioRepository.deleteById(Long id);
        }
        catch(DataIntegrityViolationException e) {
            throw new ErroNoBancoDeDados(e.getMessage());
        }
    }

    public UsuarioDTO update(Long id, UsuarioDTO dto) {

        if (!usuarioRepository.existsById(id)){
            throw new ResourceNotFound("Usuario não encontrada");
        }

        Usuario entity = usuarioRepository.getReferenceById(Long id);
        entity.setNome(dto.getNome()); // sobrescrevi o nome antigo
        entity.setEmail(dto.getEmail());
        entity.setSenha(dto.getSenha());
        entity.setTelefone(dto.getTelefone());
        entity.setCriadoEm(dto.getCriadoEm());
        entity.setAtualidoEm(dto.getAtualidoEm());
        entity = usuarioRepository.save(entity);
        return new UsuarioDTO(entity);

    }
