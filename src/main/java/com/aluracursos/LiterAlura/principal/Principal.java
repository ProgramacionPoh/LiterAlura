package com.aluracursos.LiterAlura.principal;


import com.aluracursos.LiterAlura.model.Autor;
import com.aluracursos.LiterAlura.model.DatosLibros;

import com.aluracursos.LiterAlura.model.Libros;
import com.aluracursos.LiterAlura.model.ResultadosLibros;
import com.aluracursos.LiterAlura.repository.AutorRepository;
import com.aluracursos.LiterAlura.repository.LibroRepository;
import com.aluracursos.LiterAlura.service.ConsumoAPI;
import com.aluracursos.LiterAlura.service.ConvierteDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Principal {
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();

    private LibroRepository libroRepository;
    private AutorRepository autorRepository;
    private Scanner libroteclado = new Scanner(System.in);

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public DatosLibros getLibro(String nombreLibro) {
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + nombreLibro.toLowerCase().replace(" ", "+"));
        List<DatosLibros> libros = conversor.obtenerDatos(json, ResultadosLibros.class).resultados();
        if (libros == null || libros.isEmpty()) {
            System.out.println("El libro no ha sido encontrado");
            return null;
        }
        Optional<DatosLibros> librox = libros.stream()
                .filter(l -> l.titulo().toLowerCase().contains(nombreLibro.toLowerCase()))
                .findFirst();
        if (librox.isPresent()) {
            return librox.get();
        } else {
            System.out.println("El libro no ha sido encontrado");
            return null;
        }
    }

    private void readLibro(Libros libro) {
        Optional<Libros> libroExistente = libroRepository.findByTitulo(libro.getTitulo());
        if (libroExistente.isPresent()) {
            System.out.println("----- LIBRO -----");
            System.out.println("Titulo: " + libro.getTitulo());
            System.out.println("Autor: " + libro.getAutor().getNombre());
            System.out.println("Idioma: " + libro.getIdioma());
            System.out.println("Numero de descargas: " + libro.getNumeroDeDescargas());
            System.out.println("----------\n");
            return;
        }

        System.out.println("----- LIBRO -----");
        System.out.println("Titulo: " + libro.getTitulo());
        System.out.println("Autor: " + libro.getAutor().getNombre());
        System.out.println("Idioma: " + libro.getIdioma());
        System.out.println("Numero de descargas: " + libro.getNumeroDeDescargas());
        System.out.println("----------\n");

        libroRepository.save(libro);
    }

    private void readAutor(Autor autor) {
        if (autor != null) {
            System.out.println("Autor: " + autor.getNombre());
            System.out.println("Fecha de nacimiento: " + autor.getFechaDeNacimiento());
            System.out.println("Fecha de fallecimiento: " + autor.getFechaDeFallecimiento());
            List<String> libros = autor.getLibros().stream()
                    .map(l -> l.getTitulo())
                    .collect(Collectors.toList());
            System.out.println("Libros: " + libros + "\n");
        } else {
            System.out.println("El Autor no se ha encontrado");
        }
    }

    public void menu() {
        var opcion = -1;
        while (opcion != 0) {
            System.out.println("""
                    Elija la opcion a traves de su numero:
                    1- buscar libro por titulo
                    2- listar libros registrados
                    3- listar autores registrados
                    4- listar autores vivos en un determinado año
                    5- listar libros por idioma
                    0- salir
                    """);

            if (libroteclado.hasNextInt()) {
                opcion = libroteclado.nextInt();
                if (opcion == 1) {
                    System.out.println("Ingrese el nombre del libro que desea buscar:");
                    String nombreLibro = libroteclado.next();
                    Libros libro = new Libros(getLibro(nombreLibro));
                    readLibro(libro);
                } else if (opcion == 2) {
                    List<Libros> libros = libroRepository.findAll();
                    libros.forEach(this::readLibro);
                } else if (opcion == 3) {
                    List<Autor> autores = autorRepository.findAll();
                    autores.forEach(this::readAutor);
                } else if (opcion == 4) {
                    System.out.println("Ingresa el año vivo de autor(es) que desea buscar");
                    Integer fechaDeFallecimiento = libroteclado.nextInt();
                    List<Autor> autoresVivos = autorRepository.findByFechaDeFallecimientoGreaterThan(fechaDeFallecimiento);
                    autoresVivos.forEach(this::readAutor);
                } else if (opcion == 5) {
                    System.out.println("Ingrese el idioma para buscar los libros:");
                    System.out.println("es - español");
                    System.out.println("en - ingles");
                    System.out.println("fr - frances");
                    System.out.println("pt - portugues");
                    String idioma = libroteclado.next();
                    List<Libros> librosPorIdioma = libroRepository.findByIdioma(idioma);
                    librosPorIdioma.forEach(this::readLibro);
                } else if (opcion == 0) {
                    break;
                } else {
                    System.out.println("");
                }
            } else {
                // Limpiar el buffer del scanner si la entrada no es un número entero.
                // Arregla un bug con ciertos libros como Harry Potter y otros que no existe el libro.
                libroteclado.next();
            }
        }
    }


}



