package br.ifmg.produto1_2026.repositories;

import br.ifmg.produto1_2026.entities.Produto;
import br.ifmg.produto1_2026.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
