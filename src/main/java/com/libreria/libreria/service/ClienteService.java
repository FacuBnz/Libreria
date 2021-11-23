package com.libreria.libreria.service;

import java.util.Collections;
import java.util.List;

import com.libreria.libreria.entity.Cliente;
import com.libreria.libreria.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ClienteService implements UserDetailsService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    private final String MENSAJE = "El email ingresado no existe %s";

    @Transactional
    public void created(String nombre, String apellido, Long dni, String email, String clave) {
        
        Cliente cliente = new Cliente();

        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setDni(dni);
        cliente.setEmail(email);
        cliente.setClave(encoder.encode(clave));
        cliente.setAlta(true);

        clienteRepository.save(cliente);
        
    }

    @Transactional(readOnly = true)
    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Cliente getCliente(String id) throws Exception {
        
        if(!clienteRepository.existsById(id)){
            throw new Exception("No existe el cliente");
        }

        return clienteRepository.findByIdCliente(id);
    }

    @Transactional
    public void modificar(String id, String nombre, String apellido, Long dni, String email, String clave) throws Exception {
        
        if(!clienteRepository.existsById(id)){
            throw new Exception("No existe el clinete");
        }
        clienteRepository.modificar(nombre, apellido, dni, email, clave, id);
    }

    @Transactional
    public void eliminar(String id) throws Exception {
        if(!clienteRepository.existsById(id)){
            throw new Exception("No existe el clinete");
        }
        clienteRepository.deleteById(id);
    }

    public void alta(String id) throws Exception {
        if(!clienteRepository.existsById(id)){
            throw new Exception("No existe el clinete");
        }
        Cliente cliente = clienteRepository.findByIdCliente(id);
        cliente.setAlta(true);
        clienteRepository.save(cliente);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Cliente cliente = clienteRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException(String.format(MENSAJE, email)));

        return new User(cliente.getEmail(), cliente.getClave(), Collections.emptyList());

    }

}
