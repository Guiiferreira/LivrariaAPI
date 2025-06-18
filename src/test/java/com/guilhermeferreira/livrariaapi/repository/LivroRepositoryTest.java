package com.guilhermeferreira.livrariaapi.repository;


import com.guilhermeferreira.livrariaapi.model.Autor;
import com.guilhermeferreira.livrariaapi.model.GeneroLivro;
import com.guilhermeferreira.livrariaapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {


    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salavarTest() {
        Livro livro = new Livro();
        livro.setIsbn("7778-9888");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = autorRepository.
                findById(UUID.fromString("15a7c5df-1e10-4d1b-bd42-366d40e39d8f"))
                .orElse(null);
        livro.setAutor(autor);


        livroRepository.save(livro);

    }

    @Test
    void salavarCascadeTest() {
        Livro livro = new Livro();
        livro.setIsbn("7778-9888");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Outro livro");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = new Autor();
        autor.setNome("Guilherme");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1998, 1, 31));

        livro.setAutor(autor);

        livroRepository.save(livro);

    }

    @Test
    void salavarAutorELivroTest() {
        Livro livro = new Livro();
        livro.setIsbn("7778-9888");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Novo livro");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        Autor autor = new Autor();
        autor.setNome("Marta");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1998, 1, 31));


        autorRepository.save(autor);

        livro.setAutor(autor);

        livroRepository.save(livro);

    }

    @Test
    public void listarTest() {
        List<Livro> lista = livroRepository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void deleteTest() {
        var id = UUID.fromString("");
        var maria = livroRepository.findById(id).get();
        livroRepository.delete(maria);
    }
    @Test
    public void deletePorIdTest() {
        var id = UUID.fromString("92fa3534-494a-4f90-8a0b-a99cf9f4c325");
        livroRepository.deleteById(id);
    }

}