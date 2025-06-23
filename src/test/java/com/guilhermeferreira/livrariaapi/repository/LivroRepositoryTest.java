package com.guilhermeferreira.livrariaapi.repository;


import com.guilhermeferreira.livrariaapi.model.Autor;
import com.guilhermeferreira.livrariaapi.model.GeneroLivro;
import com.guilhermeferreira.livrariaapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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

//    @Test
//    public void deleteTest() {
//        var id = UUID.fromString("");
//        var maria = livroRepository.findById(id).get();
//        livroRepository.delete(maria);
//    }
//
//    @Test
//    public void deletePorIdTest() {
//        var id = UUID.fromString("92fa3534-494a-4f90-8a0b-a99cf9f4c325");
//        livroRepository.deleteById(id);
  //  }


    @Test
    void autualizarAutorDoLivro() {
        UUID id = UUID.fromString("fec95948-89be-47b0-b4bb-26c68e1857d0");
        var livroParaAtualizar = livroRepository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("15a7c5df-1e10-4d1b-bd42-366d40e39d8f");
        Autor maria = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(maria);

        livroRepository.save(livroParaAtualizar);
    }


    @Test
    void deletar() {
        var id = UUID.fromString("7c884a6e-b0de-4207-b00b-11a825bf4979");
        livroRepository.deleteById(id);
    }
    @Test
    void deletarCascade() {
        var id = UUID.fromString("fec95948-89be-47b0-b4bb-26c68e1857d0");
        livroRepository.deleteById(id);
    }


    @Test
    @Transactional
    void buscarLivroTest(){
        UUID id = UUID.fromString("382c10ae-7288-4460-bd2b-140e6df6a419");
        Livro livro = livroRepository.findById(id).orElse(null);
        System.out.println("Nome do livro:");
        System.out.println(livro.getTitulo());
        System.out.println("Nome do autor:");
        System.out.println(livro.getAutor().getNome());
    }


    @Test
    void pesquisarPorTituloTest(){
        List<Livro> lista = livroRepository.findByTitulo("O roubo da casa assombrada");
        lista.forEach(System.out::println);
    }
      @Test
    void pesquisarPorISBNTest(){
        List<Livro> lista = livroRepository.findByIsbn("20847-84874");
        lista.forEach(System.out::println);
    }
    @Test
    void pesquisarPorTituloEPrecoTest(){
        var preco = BigDecimal.valueOf(204.00);
        var  tituloPesquisa = "O roubo da casa assombrada";

        List<Livro> lista = livroRepository.findByTituloAndPreco(tituloPesquisa, preco);
        lista.forEach(System.out::println);
    }

    @Test
    void listarLivrosComQueryJPQL(){
    var resultado = livroRepository.listarTodosOrdernadoPorTituloAndPreco();
    resultado.forEach(System.out::println);
    }

    @Test
    void listarAutoresDosLivros(){
    var resultado = livroRepository.listarAutoresDosLivros();
    resultado.forEach(System.out::println);
    }

    @Test
    void listarTitulosNaoRepetidosDosLivros(){
    var resultado = livroRepository.listarNomesDiferentesLivros();
    resultado.forEach(System.out::println);
    }

    @Test
    void listarGenerosDeAutoresBrasileiros(){
    var resultado = livroRepository.listarGenerosAutoresBrasileiros();
    resultado.forEach(System.out::println);
    }





}