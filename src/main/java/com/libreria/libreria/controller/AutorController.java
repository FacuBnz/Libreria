package com.libreria.libreria.controller;

import com.libreria.libreria.entity.Autor;
import com.libreria.libreria.service.AutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/autores")
public class AutorController {
    
    @Autowired
    private AutorService autorService;

    @GetMapping
    public ModelAndView mostrarTodos(){
        ModelAndView mav = new ModelAndView("autores");
        mav.addObject("autores", autorService.getAll());
        return mav;
    }

    @GetMapping("/crear")
    public ModelAndView crearAutor(){
        ModelAndView mav = new ModelAndView("autor-formulario");
        mav.addObject("autor", new Autor());
        mav.addObject("tittle", "Crear Autor");
        mav.addObject("action", "guardar");
        return mav;
    }

    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam String nombre){
        autorService.create(nombre);
        return new RedirectView("/autores");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable String id){
        ModelAndView mav = new ModelAndView("autor-formulario");
        mav.addObject("autor", autorService.getAutor(id));
        mav.addObject("tittle", "Modificar Autor");
        mav.addObject("action", "modificar");
        return mav;
    }

    @PostMapping("/modificar")
    public RedirectView modificar(@RequestParam String nombre, @RequestParam String id){
        autorService.modificar(nombre, id);
        return new RedirectView("/autores");
    }

    @PostMapping("/eliminar")
    public RedirectView eliminar(@RequestParam String id) {
        autorService.eliminar(id);
        return new RedirectView("/autores");
    }

    @PostMapping("/alta")
    public RedirectView alta(@RequestParam String id) {
        autorService.alta(id);
        return new RedirectView("/autores");
    }
}
