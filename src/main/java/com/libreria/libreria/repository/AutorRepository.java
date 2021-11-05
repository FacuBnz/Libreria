package com.libreria.libreria.repository;

import com.libreria.libreria.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, String>{
    
    @Query("SELECT a FROM Autor a WHERE a.nombre = :nombre")
    Autor findAuthor(@Param("nombre") String nombre);

    @Query("SELECT a FROM Autor a WHERE a.id = :id")
    Autor findByIdAutor(@Param("id") String id);

    @Modifying
    @Query("UPDATE Autor a SET a.nombre = :nombre WHERE a.id = :id")
    void modificar(@Param("nombre") String nombre, @Param("id") String id);
}
