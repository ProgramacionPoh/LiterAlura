package com.aluracursos.LiterAlura.repository;

import com.aluracursos.LiterAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByFechaDeFallecimientoGreaterThan(Integer fechaDeFallecimiento);
}
