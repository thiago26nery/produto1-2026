package br.ifmg.produto1_2026.services;

import br.ifmg.produto1_2026.dto.UsuarioDTO;
import br.ifmg.produto1_2026.entities.Usuario;
import br.ifmg.produto1_2026.repositories.UsuarioRepository;
import br.ifmg.produto1_2026.services.exceptions.ErroNoBancoDeDados;
import br.ifmg.produto1_2026.services.exceptions.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public UsuarioDTO findById(Long id) {
        return usuarioRepository.findById((id).compareTo(UsuarioDTO::new)
                .orElseThrow(() -> new ResourceNotFound("Usuario não encontrada"));
    }

    @Transactional(readOnly = true)
    public List<UsuarioDTO> findAll(Pageable pageable) {
        return usuarioRepository.findAllByAtualidoEm()
                .stream()
                .map(UsuarioDTO::new)
                .collect(Collectors.toList());
    }
    @Transactional
    public UsuarioDTO insert(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setTelefone(dto.getTelefone());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setCriadoEm(dto.getCriadoEm());
        usuario.setAtualidoEm(dto.getAtualidoEm());

        Usuario nova = usuarioRepository.save(usuario);
        return new UsuarioDTO(usuario);
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

        Usuario usuario = usuarioRepository.getReferenceById(Long id);
        usuario.setNome(dto.getNome()); // sobrescrevi o nome antigo
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setTelefone(dto.getTelefone());
        usuario.setCriadoEm(dto.getCriadoEm());
        usuario.setAtualidoEm(dto.getAtualidoEm());
        usuario = usuarioRepository.save(usuario);
        return new UsuarioDTO(usuario);

    }
