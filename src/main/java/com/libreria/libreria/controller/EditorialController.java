package com.libreria.libreria.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping("/editoriales")
public class EditorialController {
    
    @Autowired
    private EditorialService editorialService;

    @GetMapping

    public ModelAndView mostrarTodos(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("editoriales");
        Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
        if( map != null){
            mav.addObject("errorEditar", map.get("errorEditar"));
            mav.addObject("errorModificar", map.get("errorModificar"));
            mav.addObject("errorEliminar", map.get("errorEliminar"));
            mav.addObject("errorAlta", map.get("errorAlta"));
            mav.addObject("errorGuardar", map.get("errorGuardar"));
        }
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
    public RedirectView guardar(@RequestParam String nombre, RedirectAttributes attribute) {
        try {
            editorialService.create(nombre);
        } catch (Exception e) {
            attribute.addFlashAttribute("errorGuardar", e.getMessage());
        }
        return new RedirectView("/editoriales");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable String id, RedirectAttributes attribute) {
        ModelAndView mav = new ModelAndView("editorial-formulario");
        try {
            mav.addObject("editorial", editorialService.getEditorial(id));
            
        } catch (Exception e) {
            attribute.addFlashAttribute("errorEditar", e.getMessage());
            return new ModelAndView(new RedirectView("/editoriales"));
        }
        mav.addObject("tittle", "Modificar editorial");
        mav.addObject("action", "modificar");
        return mav;
    }

    @PostMapping("/modificar")
    public RedirectView name(@RequestParam String nombre, @RequestParam String id, RedirectAttributes attribute) {
        
        try {
            editorialService.modificar(id, nombre);
            
        } catch (Exception e) {
            attribute.addFlashAttribute("errorModificar", e.getMessage());
        }
        return new RedirectView("/editoriales");
    }

    @PostMapping("/eliminar")
    public RedirectView name(@RequestParam String id, RedirectAttributes attribute) {
        try {
            editorialService.eliminar(id);
            
        } catch (Exception e) {
            attribute.addFlashAttribute("errorEliminar", e.getMessage());
        }
        return new RedirectView("/editoriales");
    }

    @PostMapping("/alta")
    public RedirectView alta(@RequestParam String id, RedirectAttributes attribute) {
        try {
            
            editorialService.alta(id);
        } catch (Exception e) {
            attribute.addFlashAttribute("errorAlta", e.getMessage());
        }
        return new RedirectView("/editoriales");
    }
}