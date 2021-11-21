package com.libreria.libreria.entity;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@SQLDelete(sql = "UPDATE editorial SET alta = false WHERE id = ?")
public class Editorial {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(unique = true)
    private String nombre;
    private Boolean alta;
    
    @OneToMany(mappedBy = "editorial", fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Editorial() {
        this.libros = new ArrayList<>();
    }

}
