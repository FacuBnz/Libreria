package com.libreria.libreria.entity;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Autor {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String nombre;
    private Boolean alta;

    @OneToMany(mappedBy = "autor", fetch = FetchType.EAGER)
    private List<Libro> libros;
    
    public Autor() { 
        this.libros = new ArrayList<>();
    }

    public Autor(String id, String nombre, Boolean alta, List<Libro> libros) {
        
        this.id = id;
        this.nombre = nombre;
        this.alta = alta;
        this.libros = libros;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
    
    public List<Libro> getLibros() {
        return libros;
    }

}
