package br.ifmg.produto1_2026.dto;

import br.ifmg.produto1_2026.entities.Categoria;

import java.time.Instant;

public class CategoriaDTO {

    private long id;
    private String nome;

    public CategoriaDTO() {

    }

    public CategoriaDTO(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public CategoriaDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "CategoriaDTO{" + "id=" + id + ", nome=" + nome + '}';
    }
}