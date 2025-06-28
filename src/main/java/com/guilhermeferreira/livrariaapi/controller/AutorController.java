package com.guilhermeferreira.livrariaapi.controller;


import com.guilhermeferreira.livrariaapi.controller.dto.AutorDTO;
import com.guilhermeferreira.livrariaapi.model.Autor;
import com.guilhermeferreira.livrariaapi.service.AutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

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

}
