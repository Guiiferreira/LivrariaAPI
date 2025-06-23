package com.guilhermeferreira.livrariaapi.service;

import com.guilhermeferreira.livrariaapi.model.Autor;
import com.guilhermeferreira.livrariaapi.model.GeneroLivro;
import com.guilhermeferreira.livrariaapi.model.Livro;
import com.guilhermeferreira.livrariaapi.repository.AutorRepository;
import com.guilhermeferreira.livrariaapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class TransacaoService {


    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public void executar(){

        //Salva o autor

        Autor autor = new Autor();
        autor.setNome("Guilherme Pereira");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1998, 1, 31));

        autorRepository.save(autor);

        // Salva o livro

        Livro livro = new Livro();
        livro.setIsbn("7778-9888");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTitulo("Livro do Guilherme");
        livro.setDataPublicacao(LocalDate.of(2000, 1, 2));


        livro.setAutor(autor);

        livroRepository.save(livro);

        if(autor.getNome().equals("jose")){
            throw  new RuntimeException("Rollback!");
        }

    }
}

