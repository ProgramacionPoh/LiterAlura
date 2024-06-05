package com.aluracursos.LiterAlura.repository;

import com.aluracursos.LiterAlura.model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libros, Long> {
    List<Libros> findByIdioma(String idioma);

    Optional<Libros> findByTitulo(String titulo);
}
