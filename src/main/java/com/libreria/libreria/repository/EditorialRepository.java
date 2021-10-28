package com.libreria.libreria.repository;

import com.libreria.libreria.entity.Editorial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepository extends JpaRepository<Editorial, String> {
    
    @Query("SELECT e FROM Editorial e WHERE e.nombre = :nombre")
    Editorial findEditorial(@Param("nombre") String nombre);

    @Query("SELECT e FROM Editorial e WHERE e.id = :id")
    Editorial findByIdEditorial(@Param("id") String id);
}
