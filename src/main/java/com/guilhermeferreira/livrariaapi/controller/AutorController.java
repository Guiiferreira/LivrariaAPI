package com.guilhermeferreira.livrariaapi.controller;


import com.guilhermeferreira.livrariaapi.controller.dto.AutorDTO;
import com.guilhermeferreira.livrariaapi.model.Autor;
import com.guilhermeferreira.livrariaapi.service.AutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/autores")
//http://localhost:8080/autores
public class AutorController {


    private final AutorService autorService;

    public AutorController(AutorService autorService){
        this.autorService = autorService;
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody AutorDTO autor){
        Autor autorEntidade = autor.mapearParaAutor();
        autorService.salvar(autorEntidade);

        //http://localhost:8080/autores/7383h3h5h4-6h5h-fy-yee4e-57ee
        //contrução da url e visualizar o dados do autor cadastrado
        URI location = ServletUriComponentsBuilder.
                fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(autorEntidade.getId())
                .toUri();


        return ResponseEntity.created(location).build();
    }
    /**
     * Endpoint para obter os detalhes de um autor específico pelo seu ID.
     * Será acessado via uma requisição HTTP GET.
     */
    // Define que este metodo responde a requisições GET em um URL que contém um ID (ex: /autores/algum-id)
    @GetMapping("{id}")
    public ResponseEntity<AutorDTO> obterDetalhes(@PathVariable("id") String id){
        // Converte a String 'id' recebida do URL para o formato UUID (Identificador Único Universal).
        // Isso é necessário para usar o ID na busca no banco de dados, que provavelmente espera um tipo UUID.
                var idAutor = UUID.fromString(id);

        // Chama o serviço (camada de lógica de negócio) para buscar um autor pelo ID já convertido para UUID.
        // O retorno é um "Optional", que é um objeto que pode ou não conter um Autor.
        Optional<Autor> autorOptional = autorService.obterPorId(idAutor);

        // Verifica se o Optional retornado contém um objeto Autor (ou seja, se o autor foi encontrado no banco de dados).
        if (autorOptional.isPresent()) {

            // Se o autor foi encontrado, extrai o objeto 'Autor' de dentro do 'Optional'.
            Autor autor = autorOptional.get();

            // Cria um DTO (Data Transfer Object - Objeto de Transferência de Dados).
            AutorDTO dto = new AutorDTO(
                    autor.getId(),
                    autor.getNome(),
                    autor.getDataNascimento(),
                    autor.getNacionalidade());

            // Retorna uma resposta HTTP 200 (OK), com o DTO do autor no corpo da resposta.
            return ResponseEntity.ok(dto);
        }

        // Se o Optional estava vazio (o 'if' deu falso), significa que o autor não foi encontrado.
        // Neste caso, retorna uma resposta HTTP 404 (Not Found - Não Encontrado) sem corpo.
        return ResponseEntity.notFound().build();
    }

    /**
     * Endpoint para deletar um autor específico pelo seu ID.
     * Será acionado por uma requisição HTTP DELETE.
     */
    @DeleteMapping("{id}")// Mapeia requisições HTTP do tipo DELETE para URLs com um ID (ex: DELETE /autores/algum-id).
    public ResponseEntity<Void> deletar(@PathVariable("id") String id){ // O retorno é ResponseEntity<Void>, indicando que não haverá corpo na resposta de sucesso.
        // Converte o ID que veio do URL (String) para o tipo UUID.
        var idAutor = UUID.fromString(id);

        // Usa o serviço para buscar o autor no banco de dados antes de tentar deletá-lo.
        Optional<Autor> autorOptional = autorService.obterPorId(idAutor);

        // Verifica se o Optional está vazio, ou seja, se o autor NÃO foi encontrado.
        // Usar .isEmpty() é uma forma clara e moderna de fazer essa checagem.
        if (autorOptional.isEmpty()) {

            // Se o autor não existe, não há o que deletar. Retorna um status HTTP 404 (Not Found).
            return ResponseEntity.notFound().build();

        }
        // Se o código chegou até aqui, significa que o autor foi encontrado.
        // O metodo .get() extrai o objeto Autor de dentro do Optional...
        // ...e o passa para o serviço realizar a operação de exclusão no banco de dados.
        autorService.deletar(autorOptional.get());

        // Após deletar com sucesso, retorna um status HTTP 204 (No Content).
        // Este é o status padrão para indicar que a operação foi bem-sucedida e não há conteúdo para retornar.
        return ResponseEntity.noContent().build();
        }

    /**
     * Endpoint para pesquisar autores. Suporta filtros opcionais por nome e nacionalidade.
     * Responde a requisições GET na raiz do recurso (ex: /autores).
     */

        @GetMapping
        public ResponseEntity<List<AutorDTO>> pesquisar(

                // @RequestParam pega parâmetros da query string do URL (ex: ?nome=Machado).
                // "required = false" torna o parâmetro opcional. Se não for enviado, a variável fica como 'null'.
              @RequestParam(value = "nome", required = false)  String nome,
              @RequestParam(value = "nacionalidade", required = false) String nacionalidade){

            // 1. Delega a lógica da busca para a camada de serviço, passando os filtros recebidos.
            List<Autor> resultado = autorService.pesquisa(nome,nacionalidade);

            // 2. Converte a lista de entidades 'Autor' para uma lista de 'AutorDTO'.
            List<AutorDTO> lista = resultado
                    .stream()// Converte a lista em um fluxo (stream) para processamento funcional.
                    .map(autor -> new AutorDTO( // Para cada objeto 'autor' no fluxo, mapeia (transforma) ele em um 'AutorDTO'.
                            autor.getId(),
                            autor.getNome(),
                            autor.getDataNascimento(),
                            autor.getNacionalidade()))
                    .collect(Collectors.toList());// Coleta todos os 'AutorDTO' criados e os agrupa em uma nova lista.

            // 3. Retorna a lista de DTOs no corpo da resposta com um status HTTP 200 (OK).
            return ResponseEntity.ok(lista);
        }
    }

