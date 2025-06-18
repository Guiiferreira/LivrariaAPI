package com.guilhermeferreira.livrariaapi.repository;


import com.guilhermeferreira.livrariaapi.model.Autor;
import com.guilhermeferreira.livrariaapi.model.GeneroLivro;
import com.guilhermeferreira.livrariaapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {


    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salavarTest(){
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
}