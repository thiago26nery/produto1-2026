package br.ifmg.produto1_2026.dto;

import br.ifmg.produto1_2026.entities.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UsuarioDTO {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Campo nome obrigatório")
    private String nome;
    private String telefone;
    private String email;
    @NotBlank(message = "Email obrigatório")
    @Email(message = "email inválido")
    private String senha;

    private List<PerfilDTO> perfis = new ArrayList<>();

    public UsuarioDTO() {

    }

    //usuario.getPerfiis().for
    public UsuarioDTO(Long id, String nome, String telefone, String email, String senha ) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
    }

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.telefone = usuario.getTelefone();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();

        usuario.getPerfis().forEach(role-> this.perfis.add(new PerfilDTO(role)));
    }
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant criadoEm;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant atualidoEm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

   public List<PerfilDTO> getPerfis() {
        return perfis;
   }

   public void setPerfis(List<PerfilDTO> perfis) {
        this.perfis = perfis;
   }

   public String toString(){
        return "Usuario{" + "id=" + id + ", nome=" + nome + ", telefone=" + telefone + ", email=" + email + ", senha=" + senha + '}';
   }
}
