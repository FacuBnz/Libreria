package com.libreria.libreria.service;

import java.util.List;

import com.libreria.libreria.entity.Editorial;
import com.libreria.libreria.repository.EditorialRepository;
import com.libreria.libreria.repository.LibroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditorialService {
    
    @Autowired
    private EditorialRepository editorialRepository;
    
    @Autowired
    private LibroRepository libroRepository;

    @Transactional
    public void create(String nombre){

        Editorial editorial = new Editorial();

        editorial.setAlta(true);
        editorial.setNombre(nombre);
        editorial.setLibros(libroRepository.findAllForEditorial(nombre));

        editorialRepository.save(editorial);

    }

    @Transactional
    public List<Editorial> getAll() {
        return editorialRepository.findAll();
    }

    @Transactional
    public Editorial getEditorial(String id) {
        return editorialRepository.findByIdEditorial(id);
    }

    @Transactional
    public void modificar(String id, String nombre){
        Editorial editorial = editorialRepository.getById(id);
        editorial.setNombre(nombre);
        editorialRepository.save(editorial);
    }

    @Transactional
    public void eliminar(String id) {
        editorialRepository.deleteById(id);
    }

    @Transactional
    public void alta(String id) {
        Editorial editorial = editorialRepository.getById(id);
        editorial.setAlta(true);
        editorialRepository.save(editorial);
    }
}
