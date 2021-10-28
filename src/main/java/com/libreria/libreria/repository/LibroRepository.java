package com.libreria.libreria.repository;

import java.util.List;
import com.libreria.libreria.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, String>{
    

    @Query("SELECT l FROM Libro l WHERE l.autor.nombre = :nombre")
    List<Libro> findAllForAuthor(@Param("nombre") String nombre);

    @Query("SELECT l FROM Libro l WHERE l.editorial.nombre = :nombre")
    List<Libro> findAllForEditorial(@Param("nombre") String nombre);
}
