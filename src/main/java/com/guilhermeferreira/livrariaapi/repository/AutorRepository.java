package com.guilhermeferreira.livrariaapi.repository;

import com.guilhermeferreira.livrariaapi.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {


    /**
     * Procura autores pelo nome.
     * O Spring gera: "SELECT a FROM Autor a WHERE a.nome = ?1"
     */
    List<Autor> findByNome(String nome);

    /**
     * Procura autores pela nacionalidade.
     * O Spring gera: "SELECT a FROM Autor a WHERE a.nacionalidade = ?1"
     */
    List<Autor> findByNacionalidade(String nacionalidade);

    /**
     * Procura autores pelo nome E pela nacionalidade.
     * O Spring gera: "SELECT a FROM Autor a WHERE a.nome = ?1 AND a.nacionalidade = ?2"
     */
    List<Autor> findByNomeAndNacionalidade(String nome, String nacionalidade);
}
