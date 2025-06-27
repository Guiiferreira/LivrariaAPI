package com.guilhermeferreira.livrariaapi.controller;


import com.guilhermeferreira.livrariaapi.controller.dto.AutorDTO;
import com.guilhermeferreira.livrariaapi.model.Autor;
import com.guilhermeferreira.livrariaapi.service.AutorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
                .path("{/id}")
                .buildAndExpand(autorEntidade.getId())
                .toUri();


        return ResponseEntity.created(location).build();
    }
}
