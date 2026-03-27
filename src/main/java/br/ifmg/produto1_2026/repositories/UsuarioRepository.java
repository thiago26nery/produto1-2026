package br.ifmg.produto1_2026.repositories;

import br.ifmg.produto1_2026.entities.Usuario;
import org.springframework.data.repository.Repository;

import java.nio.channels.FileChannel;
import java.time.Instant;
import java.util.Collection;

interface UsuarioRepository extends Repository<Usuario, Long> {
    FileChannel findById(Long id);

    Collection<Object> findAllByAtualidoEm(Instant atualidoEm);

    boolean existsById(Long id);

    void deleteById(Long id);

    Usuario getReferenceById(Long id);
}
