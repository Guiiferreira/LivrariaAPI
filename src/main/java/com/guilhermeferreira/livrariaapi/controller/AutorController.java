package com.guilhermeferreira.livrariaapi.controller;


import com.guilhermeferreira.livrariaapi.controller.dto.AutorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autores")
//http://localhost:8080/autores
public class AutorController {


    @PostMapping
    public ResponseEntity salvar(@RequestBody AutorDTO autor){
        return new ResponseEntity("Autor Salvo com sucesso! " + autor, HttpStatus.CREATED);
    }
}
