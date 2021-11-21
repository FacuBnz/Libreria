package com.libreria.libreria.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@SQLDelete(sql = "UPDATE libro SET alta = false WHERE id = ?")
public class Libro {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(unique = true)
    private Long isbn;

    @Column(unique = true)
    private String titulo;
    private Integer anio;
    private Integer ejemplares;
    private Integer ejemplaresPrestados;
    private Integer ejemplaresRestantes;
    private Boolean alta;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Autor autor;
    
    @JoinColumn(nullable = false)
    @ManyToOne
    private Editorial editorial;

}
