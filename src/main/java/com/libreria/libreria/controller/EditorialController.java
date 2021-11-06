package com.libreria.libreria.controller;

import com.libreria.libreria.entity.Editorial;
import com.libreria.libreria.service.EditorialService;

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
@RequestMapping("/editoriales")
public class EditorialController {
    
    @Autowired
    private EditorialService editorialService;

    @GetMapping

    public ModelAndView mostrarTodos() {
        ModelAndView mav = new ModelAndView("editoriales");
        mav.addObject("editoriales", editorialService.getAll());
        return mav;
    }

    @GetMapping("/crear")
    public ModelAndView crearEditorial() {
        ModelAndView mav = new ModelAndView("editorial-formulario");
        mav.addObject("editorial", new Editorial());
        mav.addObject("tittle", "Crear editorial");
        mav.addObject("action", "guardar");

        return mav;
    }

    @PostMapping("/guardar")
    public RedirectView guardar(@RequestParam String nombre) {
        editorialService.create(nombre);
        return new RedirectView("/editoriales");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable String id) {
        ModelAndView mav = new ModelAndView("editorial-formulario");
        mav.addObject("editorial", editorialService.getEditorial(id));
        mav.addObject("tittle", "Modificar editorial");
        mav.addObject("action", "modificar");
        return mav;
    }

    @PostMapping("/modificar")
    public RedirectView name(@RequestParam String nombre, @RequestParam String id) {
        editorialService.modificar(id, nombre);
        return new RedirectView("/editoriales");
    }

    @PostMapping("/eliminar")
    public RedirectView name(@RequestParam String id) {
        editorialService.eliminar(id);
        return new RedirectView("/editoriales");
    }

    @PostMapping("/alta")
    public RedirectView alta(@RequestParam String id) {
        editorialService.alta(id);
        return new RedirectView("/editoriales");
    }
}