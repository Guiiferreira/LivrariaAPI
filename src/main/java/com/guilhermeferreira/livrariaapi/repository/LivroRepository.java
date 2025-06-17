package com.guilhermeferreira.livrariaapi.repository;

import com.guilhermeferreira.livrariaapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
}
