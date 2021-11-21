package com.libreria.libreria.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.libreria.libreria.entity.Libro;
import com.libreria.libreria.service.AutorService;
import com.libreria.libreria.service.EditorialService;
import com.libreria.libreria.service.LibroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

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
    public ModelAndView mostrarTodos(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("libros");
        Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
        if(map != null){
            mav.addObject("errorGuardar", map.get("errorGuardar"));
            mav.addObject("errorEditar", map.get("errorEditar"));
            mav.addObject("errorModificar", map.get("errorModificar"));
            mav.addObject("errorEliminar", map.get("errorEliminar"));
            mav.addObject("errorAlta", map.get("errorAlta"));
        }

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
    public RedirectView guardar(@RequestParam String titulo,@RequestParam Integer anio, @RequestParam Integer ejemplares, @RequestParam Long isbn, @RequestParam String autor, @RequestParam String editorial, RedirectAttributes attributes){
        
        try {
            libroService.create(titulo, anio, ejemplares, isbn, autor, editorial);
            
        } catch (Exception e) {
            attributes.addFlashAttribute("errorGuardar", e.getMessage());
        }
        return new RedirectView("/libros");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable String id, RedirectAttributes attributes) {
        ModelAndView mav = new ModelAndView("libro-formulario");

        try {
            mav.addObject("libro", libroService.getLibro(id));
        } catch (Exception e) {
            attributes.addFlashAttribute("errorEditar", e.getMessage());
            return new ModelAndView(new RedirectView("/libros"));
        }
        mav.addObject("autores", autorService.getAll());
        mav.addObject("editoriales", editorialService.getAll());
        mav.addObject("action", "modificar");
        mav.addObject("tittle", "Modificar Libro");
        return mav;
    }

    @PostMapping("/modificar")
    public RedirectView modificar(@RequestParam String id, @RequestParam String titulo,@RequestParam Integer anio, @RequestParam Integer ejemplares, @RequestParam Long isbn, @RequestParam String autor, @RequestParam String editorial, RedirectAttributes attributes) {
        
        try {
            libroService.modificar(id, titulo, anio, ejemplares, isbn, autor, editorial);
            
        } catch (Exception e) {
            attributes.addFlashAttribute("errorModificar", e.getMessage());
        }

        return new RedirectView("/libros");
    }

    @PostMapping("/eliminar")
    public RedirectView eliminar(@RequestParam String id, RedirectAttributes attributes) {
        try {
            libroService.delete(id);
            
        } catch (Exception e) {
            attributes.addFlashAttribute("errorEliminar", e.getMessage());
        }
        return new RedirectView("/libros");
    }

    @PostMapping("/alta")
    public RedirectView alta(@RequestParam String id, RedirectAttributes attributes) {
        try {
            libroService.alta(id);
            
        } catch (Exception e) {
            attributes.addFlashAttribute("errorAlta", e.getMessage());
        }
        return new RedirectView("/libros");
    }
}
/**
 * Agregar excepciones en guardar, modificar, eliminar y alta
 * Agregar de alta y baja, modificar en libros 
 */