package br.ifmg.produto1_2026.services;


import br.ifmg.produto1_2026.dto.PerfilDTO;
import br.ifmg.produto1_2026.dto.UsuarioDTO;
import br.ifmg.produto1_2026.dto.UsuarioInsertDTO;
import br.ifmg.produto1_2026.entities.Perfil;
import br.ifmg.produto1_2026.entities.Usuario;
import br.ifmg.produto1_2026.projections.UserDetailsProjection;
import br.ifmg.produto1_2026.repositories.PerfilRepository;
import br.ifmg.produto1_2026.repositories.UsuarioRepository;
import br.ifmg.produto1_2026.services.exceptions.ErroNoBancoDeDados;
import br.ifmg.produto1_2026.services.exceptions.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Transactional(readOnly = true)
    public Page<UsuarioDTO> findAll(Pageable pageRequest) {

        //Lista com os dados do BD.
        Page<Usuario> usuarios =
                repository.findAll(pageRequest);


        return usuarios.map(UsuarioDTO::new);
    }


    @Transactional(readOnly = true)
    public UsuarioDTO findById(Long id) {
        //buscamos no BD o usuario. O resultado é
        //um objeto do tipo Optional.
        Optional<Usuario> opt
                = repository.findById(id);
        //buscamos o usuario dentro do objeto Optional
        Usuario usuario = opt.orElseThrow(
                () -> new ResourceNotFound("Usuario não encontrado."));
        //Convertemos a entidade em DTO
        return new UsuarioDTO(usuario);
    }

    @Transactional
    public UsuarioDTO insert(UsuarioInsertDTO dto) {

        Usuario entity = new Usuario();
        copyDtoToEntity(dto, entity);
        entity.setSenha(
                encoder.encode(dto.getSenha())
        );

        Usuario novo = repository.save(entity);
        return new UsuarioDTO(novo);
    }

    @Transactional
    public void delete(Long id) {

        if (!repository.existsById(id)) {
            throw new ResourceNotFound("Usuario não encontrado, ao ser excluído.");
        }

        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new ErroNoBancoDeDados(e.getMessage());
        }
    }


    public UsuarioDTO update(Long id, UsuarioDTO dto) {

        if (!repository.existsById(id)) {
            throw new ResourceNotFound("Usuario não encontrado, para ser alterado.");
        }

        Usuario entity =
                repository.getReferenceById(id);

        copyDtoToEntity(dto, entity);

        entity = repository.save(entity);
        return new UsuarioDTO(entity);
    }


    private void copyDtoToEntity(UsuarioDTO dto, Usuario entity) {
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setTelefone(dto.getTelefone());

        entity.getPerfis().clear();
        for (PerfilDTO perfilDto: dto.getPerfis()){

            Perfil perfil =
                    perfilRepository
                            .getReferenceById(perfilDto.getId());
            entity.getPerfis().add(perfil);
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<UserDetailsProjection> dados =
                repository.loadUserByUsername(username);

        if (dados.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        Usuario usuario = new Usuario();
        usuario.setSenha(dados.getFirst().getPassword());
        usuario.setEmail(dados.getFirst().getUsername());

        for (UserDetailsProjection dado: dados) {
            usuario.addRole(
                    new Perfil(dado.getRoleId(),dado.getAuthority())
            );
        }

        return usuario;
    }
}