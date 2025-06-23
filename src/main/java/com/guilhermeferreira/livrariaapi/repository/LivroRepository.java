package com.guilhermeferreira.livrariaapi.repository;

import com.guilhermeferreira.livrariaapi.model.Autor;
import com.guilhermeferreira.livrariaapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;



public interface LivroRepository extends JpaRepository<Livro, UUID> {

    //QUERY METHOD
    // select * from livro where id_autor = id
    List<Livro> findByAutor(Autor autor);

    //Pesquisar
    //select * from livro where tituo = titulo
    List<Livro> findByTitulo(String titulo);

    //select * from livro where isbn = ?
    List<Livro> findByIsbn(String isbn);

    //select * from where titulo =? and preco = ?
    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);

    //select * from where titulo =? or isbn = ?
    List<Livro> findByTituloOrIsbn(String titulo, String isbn);

    // select * from livro where data_publicacao btween ? and ?
    List<Livro> findByDataPublicacaoBetween(LocalDate inicio, LocalDate fim);

    // JPQL = REFERENCIA AS ENTIDADES E AS PROPRIEDADES
    // select l.* from livro as l order by l.titulo
    @Query("select l from Livro as l order by l.titulo, l.preco")
    List<Livro> listarTodosOrdernadoPorTituloAndPreco();


    //select a.* from livro l join autor a on a.id = l.id_autor
    @Query("select a from Livro l join l.autor a ")
    List<Autor> listarAutoresDosLivros();

    //select distinct l.* from  Livro l
    @Query("select distinct l.titulo from Livro l")
    List<String> listarNomesDiferentesLivros();


    @Query("""
            select l.genero
            from Livro l
            join l.autor a 
            where a.nacionalidade = 'Brasileira'
            order by l.genero
            """)
        List<String> listarGenerosAutoresBrasileiros();


}