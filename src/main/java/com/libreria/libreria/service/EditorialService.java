package com.libreria.libreria.service;

import java.util.List;

import com.libreria.libreria.entity.Editorial;
import com.libreria.libreria.repository.EditorialRepository;
import com.libreria.libreria.repository.LibroRepository;
import com.libreria.libreria.utils.Validaciones;

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
    public void create(String nombre) throws Exception{

        Editorial editorial = new Editorial();

        editorial.setAlta(true);
        editorial.setNombre(Validaciones.editorial(nombre));
        editorial.setLibros(libroRepository.findAllForEditorial(nombre));

        editorialRepository.save(editorial);

    }

    @Transactional
    public List<Editorial> getAll() {
        return editorialRepository.findAll();
    }

    @Transactional
    public Editorial getEditorial(String id) throws Exception {
        if(!editorialRepository.existsById(id)){
            throw new Exception("No existe la editorial");
        }
        return editorialRepository.findByIdEditorial(id);
    }

    @Transactional
    public void modificar(String id, String nombre) throws Exception {
        if(!editorialRepository.existsById(id)){
            throw new Exception("No existe la editorial");
        }
        Editorial editorial = editorialRepository.getById(id);
        editorial.setNombre(Validaciones.editorial(nombre));
        editorialRepository.save(editorial);
    }

    @Transactional
    public void eliminar(String id) throws Exception {
        if(!editorialRepository.existsById(id)){
            throw new Exception("No existe la editorial");
        }
        editorialRepository.deleteById(id);
    }

    @Transactional
    public void alta(String id) throws Exception {
        if(!editorialRepository.existsById(id)){
            throw new Exception("No existe la editorial");
        }
        Editorial editorial = editorialRepository.getById(id);
        editorial.setAlta(true);
        editorialRepository.save(editorial);
    }
}
