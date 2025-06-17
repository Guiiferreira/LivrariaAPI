package com.guilhermeferreira.livrariaapi;

import com.guilhermeferreira.livrariaapi.model.Autor;
import com.guilhermeferreira.livrariaapi.repository.AutorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class LivrariaapiApplication {

	public static void main(String[] args) {

		var context = SpringApplication.run(LivrariaapiApplication.class, args);
		AutorRepository repository = context.getBean(AutorRepository.class);

			exemploSalvarRegistro(repository);
	}

	public static void exemploSalvarRegistro(AutorRepository autorRepository){
		Autor autor = new Autor();
		autor.setNome("Jose");
		autor.setNacionalidade("Brasileira");
		autor.setDataNascimento(LocalDate.of(1950,1,31));

		var autorSalvo = autorRepository.save(autor);
		System.out.println("Autor Salvo: "+ autorSalvo);
	}
}


