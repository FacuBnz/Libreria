package com.libreria.libreria.service;

import java.util.List;

import com.libreria.libreria.entity.Autor;
import com.libreria.libreria.repository.AutorRepository;
import com.libreria.libreria.repository.LibroRepository;
import com.libreria.libreria.utils.Validaciones;

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
    public void create(String nombre) throws Exception{
        
        Autor autor = new Autor();

        autor.setNombre(Validaciones.autor(nombre));
        autor.setAlta(true);
        autor.setLibros(libroRepository.findAllForAuthor(nombre));

        autorRepository.save(autor);
    }

    @Transactional(readOnly = true)
    public List<Autor> getAll(){
        return autorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Autor getAutor(String id) throws Exception{
        /** 
         * lanzar exepciones 
        */
        if(!autorRepository.existsById(id)){
            throw new Exception("El autor no existe");
        }
        return autorRepository.findByIdAutor(id);
    }

    @Transactional()
    public void modificar(String nombre, String id) throws Exception {
        
        /** lanzar excepcion en caso de que no exista el id */
        if(!autorRepository.existsById(id)){
            throw new Exception("No existe el autor a modificar");
        }

        autorRepository.modificar(Validaciones.autor(nombre), id);
    }

    @Transactional()
    public void eliminar(String id) throws Exception{

        if(!autorRepository.existsById(id)){
            throw new Exception("No existe el autor a eliminar");
        }
        autorRepository.deleteById(id);
    }

    @Transactional()
    public void alta(String id) throws Exception {
        if(!autorRepository.existsById(id)){
            throw new Exception("No existe el autor a dar de alta");
        }
        Autor autor = autorRepository.getById(id);
        autor.setAlta(true);
        autorRepository.save(autor);
    }
}
