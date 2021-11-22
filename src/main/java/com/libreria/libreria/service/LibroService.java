package com.libreria.libreria.service;

import java.util.List;

import javax.transaction.Transactional;

import com.libreria.libreria.entity.Libro;
import com.libreria.libreria.repository.AutorRepository;
import com.libreria.libreria.repository.EditorialRepository;
import com.libreria.libreria.repository.LibroRepository;
import com.libreria.libreria.utils.Validaciones;

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
    public void create(String titulo, Integer anio, Integer ejemplares, Long isbn, String autor, String editorial) throws Exception {
        
        if(!editorialRepository.existsById(editorial)){
            throw new Exception("La editorial no existe");
        }

        if(!autorRepository.existsById(autor)){
            throw new Exception("El autor no existe");
        }
        
        Libro libro = new Libro();
        
        libro.setAlta(true);
        libro.setAnio(Validaciones.anio(anio));
        libro.setEjemplares(Validaciones.ejemplares(ejemplares));
        libro.setEjemplaresPrestados(0);
        libro.setEjemplaresRestantes(Validaciones.ejemplares(ejemplares));
        libro.setIsbn(Validaciones.isbn(isbn));
        libro.setTitulo(Validaciones.titulo(titulo));
        libro.setAutor(autorRepository.findByIdAutor(autor));
        libro.setEditorial(editorialRepository.findByIdEditorial(editorial));

        libroRepository.save(libro);
    }

    @Transactional
    public List<Libro> getAll(){
        return libroRepository.findAll();
    }

    @Transactional
    public Libro getLibro(String id) throws Exception {
        if(!libroRepository.existsById(id)){
            throw new Exception("El libro no existe");
        }
        return libroRepository.getById(id);
    }

    @Transactional
    public void modificar (String id, String titulo, Integer anio, Integer ejemplares, Long isbn, String autor, String editorial) throws Exception {

        if(!libroRepository.existsById(id)){
            throw new Exception("El libro no existe");
        }

        if(!editorialRepository.existsById(editorial)){
            throw new Exception("La editorial no existe");
        }

        if(!autorRepository.existsById(autor)){
            throw new Exception("El autor no existe");
        }

        Libro libro = libroRepository.getById(id);

        libro.setTitulo(Validaciones.titulo(titulo));
        libro.setAnio(Validaciones.anio(anio));
        libro.setEjemplares(Validaciones.ejemplares(ejemplares));
        libro.setIsbn(Validaciones.isbn(isbn));
        libro.setAutor(autorRepository.findByIdAutor(autor));
        libro.setEditorial(editorialRepository.findByIdEditorial(editorial));
        
        libroRepository.save(libro);

    }

    @Transactional
    public void delete(String id) throws Exception {

        if(!libroRepository.existsById(id)){
            throw new Exception("El libro no existe");
        }

        libroRepository.deleteById(id);
    }

    @Transactional
    public void alta(String id) throws Exception {

        if(!libroRepository.existsById(id)){
            throw new Exception("El libro no existe");
        }

        Libro libro = libroRepository.getById(id);
        libro.setAlta(true);
        libroRepository.save(libro);
    }
}
