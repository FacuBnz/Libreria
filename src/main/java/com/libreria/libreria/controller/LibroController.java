package com.libreria.libreria.controller;

import com.libreria.libreria.entity.Libro;
import com.libreria.libreria.service.AutorService;
import com.libreria.libreria.service.EditorialService;
import com.libreria.libreria.service.LibroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @Autowired
    private AutorService autorService;

    @Autowired
    private EditorialService editorialService;
    
    @GetMapping
    public ModelAndView mostrarTodos() {
        ModelAndView mav = new ModelAndView("libros");
        mav.addObject("libros", libroService.getAll());
        return mav;
    }

    @GetMapping("/crear")
    public ModelAndView crearLibro() {
        ModelAndView mav = new ModelAndView("libro-formulario");
        mav.addObject("libro", new Libro());
        mav.addObject("tittle", "Crear libro");
        mav.addObject("autores", autorService.getAll());
        mav.addObject("editoriales", editorialService.getAll());
        mav.addObject("action", "guardar");
        return mav;
    }

    @PostMapping("/guardar")
    public ModelAndView guardar(@RequestParam String titulo,@RequestParam Integer anio, @RequestParam Integer ejemplares, @RequestParam Long isbn, @RequestParam String autor, @RequestParam String editorial){
        libroService.create(titulo, anio, ejemplares, isbn, autor, editorial);
        return new ModelAndView("/libros");
    }
}
/**
 * hacer servicios, vistas, repositorio controlador de
 * editorial
 */