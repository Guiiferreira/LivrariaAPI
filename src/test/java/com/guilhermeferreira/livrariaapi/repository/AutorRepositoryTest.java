package com.guilhermeferreira.livrariaapi.repository;

import com.guilhermeferreira.livrariaapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {


    @Autowired
    AutorRepository repository;

    @Test
    public void salvarTeste() {
        Autor autor = new Autor();
        autor.setNome("Maria");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1951, 1, 31));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor Salvo: " + autorSalvo);
    }

    @Test
    public void atualizarTeste() {
        var id = UUID.fromString("15a7c5df-1e10-4d1b-bd42-366d40e39d8f");
        Optional<Autor> possivelAutor = repository.findById(id);

        if (possivelAutor.isPresent()) {
            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do Autor:");
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(1960, 1, 30));

            repository.save(autorEncontrado);

            }

        }
        @Test
        public void listarTest(){
            List<Autor> lista = repository.findAll();
            lista.forEach(System.out::println);

        }

        @Test
        public void countTest(){
            System.out.println("Contagem de autores: " + repository.count() );
        }

        @Test
        public void deletePorIdTest(){
            var id = UUID.fromString("771a1c14-95b9-4955-9300-ea05019921d8");
            repository.deleteById(id);
        }
         @Test
        public void deleteTest(){
        var id = UUID.fromString("c0688567-cf71-4e0a-9b5f-904df3fa2ff9");
        var maria = repository.findById(id).get();
        repository.delete(maria);
    }

    }
