package com.libreria.libreria.service;

import java.util.List;

import javax.transaction.Transactional;

import com.libreria.libreria.entity.Libro;
import com.libreria.libreria.repository.AutorRepository;
import com.libreria.libreria.repository.EditorialRepository;
import com.libreria.libreria.repository.LibroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroService {
    
    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private EditorialRepository editorialRepository;

    @Transactional
    public void create(String titulo, Integer anio, Integer ejemplares, Long isbn, String autor, String editorial) {
        Libro libro = new Libro();
        
        libro.setAlta(true);
        libro.setAnio(anio);
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresPrestados(0);
        libro.setEjemplaresRestantes(ejemplares);
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setAutor(autorRepository.findByIdAutor(autor));
        libro.setEditorial(editorialRepository.findByIdEditorial(editorial));

        libroRepository.save(libro);
    }

    @Transactional
    public List<Libro> getAll(){
        return libroRepository.findAll();
    }
}
