package com.guilhermeferreira.livrariaapi.repository;

import com.guilhermeferreira.livrariaapi.model.Autor;
import com.guilhermeferreira.livrariaapi.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class TransacoesTest {

    @Autowired
    TransacaoService transacaoService;


    /**
     * commit = confirmar as alteraçoes
     * rollback = desfazer as alterações
     */
    @Test
    void transacaoSimples() {

        transacaoService.executar();
//                salvar um livro
//                salvar o autor
//                alugar o livro
//                enviar emai pro locatário
//                notificar que o livro saiu da livraria


    }

    @Test
    void transacaoEstadoManaged() {

        transacaoService.atualizarSemAtualizar();

    }
}