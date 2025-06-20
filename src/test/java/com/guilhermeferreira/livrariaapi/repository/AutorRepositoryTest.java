package com.guilhermeferreira.livrariaapi.repository;

import com.guilhermeferreira.livrariaapi.model.Autor;
import com.guilhermeferreira.livrariaapi.model.GeneroLivro;
import com.guilhermeferreira.livrariaapi.model.Livro;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.transform.sax.SAXSource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {


    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

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
            var id = UUID.fromString("930972e2-e45e-4ce1-82b1-e4f949bd6aff");
            repository.deleteById(id);
        }
         @Test
        public void deleteTest(){
        var id = UUID.fromString("c0688567-cf71-4e0a-9b5f-904df3fa2ff9");
        var maria = repository.findById(id).get();
        repository.delete(maria);
    }


        @Test
        void salvarAutorComLivrosTest() {
            Autor autor = new Autor();
            autor.setNome("erik");
            autor.setNacionalidade("Americana");
            autor.setDataNascimento(LocalDate.of(1996, 8, 5));


            Livro livro = new Livro();
            livro.setIsbn("20847-84874");
            livro.setPreco(BigDecimal.valueOf(204));
            livro.setGenero(GeneroLivro.MISTERIO);
            livro.setTitulo("O roubo da casa assombrada");
            livro.setDataPublicacao(LocalDate.of(1999, 1, 2));
            livro.setAutor(autor);

            Livro livro2 = new Livro();
            livro2.setIsbn("99999-84874");
            livro2.setPreco(BigDecimal.valueOf(650));
            livro2.setGenero(GeneroLivro.MISTERIO);
            livro2.setTitulo("novo livro ");
            livro2.setDataPublicacao(LocalDate.of(2000, 1, 2));
            livro2.setAutor(autor);

            autor.setLivros(new ArrayList<>());
            autor.getLivros().add(livro);
            autor.getLivros().add(livro2);

            repository.save(autor);

         //livroRepository.saveAll(autor.getLivros());


        }

        @Test
       // @Transactional
        void listarLivrosAutor(){
        var id = UUID.fromString("67b7cf65-9f63-46f7-863d-bfdea369952f");
        var autor = repository.findById(id).get();

        //BUSCAR OS LIVROS DO AUTOR

            List<Livro> livrosListas = livroRepository.findByAutor(autor);
            autor.setLivros(livrosListas);

            autor.getLivros().forEach(System.out::println);
        }

    
    

}
