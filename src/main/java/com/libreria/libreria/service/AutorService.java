package com.libreria.libreria.service;

import java.util.List;

import com.libreria.libreria.entity.Autor;
import com.libreria.libreria.repository.AutorRepository;
import com.libreria.libreria.repository.LibroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutorService {
    
    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Transactional
    public void create(String nombre){
        
        Autor autor = new Autor();

        autor.setNombre(nombre);
        autor.setAlta(true);
        autor.setLibros(libroRepository.findAllForAuthor(nombre));

        autorRepository.save(autor);
    }

    @Transactional(readOnly = true)
    public List<Autor> getAll(){
        return autorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Autor getAutor(String id){
        return autorRepository.findByIdAutor(id);
    }

    @Transactional()
    public void modificar(String nombre, String id) {
        autorRepository.modificar(nombre, id);
    }
}
