package com.libreria.libreria.repository;

import com.libreria.libreria.entity.Cliente;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends JpaRepository<Cliente, String>{
    
    @Query("SELECT c FROM Cliente c WHERE c.id = :id")
    Cliente findByIdCliente(@Param("id") String id);

    @Modifying
    @Query("UPDATE Cliente c SET c.nombre = :nombre, c.apellido = :apellido, c.dni = :dni, c.email = :email, c.clave = :clave WHERE c.id = :id")
    void modificar(@Param("nombre") String nombre, @Param("apellido") String apellido, @Param("dni") Long dni, @Param("email") String email, @Param("clave") String clave, @Param("id") String id);

    @Query("SELECT c FROM Cliente c WHERE c.email = :email")
    Cliente BuscarClientePorEmail(@Param("email") String email);

    Optional<Cliente> findByEmail(String email);

}
