package com.guilhermeferreira.livrariaapi.service;

import com.guilhermeferreira.livrariaapi.model.Autor;
import com.guilhermeferreira.livrariaapi.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
/**
 * Esta é a classe de Serviço para a entidade Autor.
 * Sua responsabilidade é conter a lógica de negócio e servir como
 * intermediária entre o Controller e o Repository.
 */
@Service
public class AutorService {

    // Declara uma dependência do AutorRepository. É a camada que de fato se comunica com o banco de dados.
    private final AutorRepository autorRepository;

   // Ele automaticamente fornece uma instância de AutorRepository quando cria o AutorService
    public AutorService(AutorRepository autorRepository){
        // Atribui a instância do repositório (injetada pelo Spring) ao campo da classe.
        this.autorRepository = autorRepository;
    }
        //Metodo para salvar uma entidade Autor no banco de dados.
    public Autor salvar(Autor autor){
        return autorRepository.save(autor);
    }

    //Metodo para buscar um único Autor pelo seu ID.
    //Um Optional contendo o Autor se ele for encontrado, ou um Optional vazio caso contrário
    public Optional<Autor> obterPorId(UUID id){
        return autorRepository.findById(id);
    }



    public void deletar(Autor autor){
        autorRepository.delete(autor);
    }

    public List<Autor> pesquisa(String nome, String nacionalidade){
        // Verifica se ambos os filtros foram preenchidos.
    if(nome != null && nacionalidade != null) {
        // Se sim, chama o metodo do repositório que busca por ambos os campos.
        return autorRepository.findByNomeAndNacionalidade(nome, nacionalidade);
            }
        // Se o de cima for falso, verifica se apenas o nome foi preenchido.
            if (nome != null){
                // Se sim, chama o metodo do repositório que busca apenas por nome.
                return autorRepository.findByNome(nome);
            }
            if (nacionalidade != null){
                return autorRepository.findByNacionalidade(nacionalidade);
            }

        // Se nenhum 'if' acima for verdadeiro, significa que nenhum filtro foi passado.
        // Nesse caso, retorna todos os autores do banco de dados.
            return autorRepository.findAll();
        }
    }



