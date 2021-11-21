package com.libreria.libreria.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/autores")
public class AutorController {
    
    @Autowired
    private AutorService autorService;

    @GetMapping
    /**
     * Agregar como parametro HttpServletRequest request (sirve para extrar el mensaje de error)
     * 
     * para capturar el error usamos un map
     * 
     * Map<String, ?> map = RequestContextUtils.getInputFlashMap(request)
     * 
     * si el mapa es distito de null es porque hubo excepcion
     */
    public ModelAndView mostrarTodos(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("autores");
        Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
        if( map != null){
            mav.addObject("errorModificar", map.get("error-modificar"));
            mav.addObject("errorAutor", map.get("errorAutor"));
            mav.addObject("errorAutorEliminar", map.get("errorAutorEliminar"));
            mav.addObject("errorAutorAltar", map.get("errorAutorAltar"));
            mav.addObject("errorGuardar", map.get("errorGuardar"));
        }
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
    public RedirectView guardar(@RequestParam String nombre, RedirectAttributes attributes){
        try {
            autorService.create(nombre);
            
        } catch (Exception e) {
            attributes.addFlashAttribute("errorGuardar", e.getMessage());
        }
        return new RedirectView("/autores");
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable String id, RedirectAttributes attributes){
        ModelAndView mav = new ModelAndView("autor-formulario");
        try {
            mav.addObject("autor", autorService.getAutor(id));
            
        } catch (Exception e) {
            attributes.addFlashAttribute("errorAutor", e.getMessage());
            return new ModelAndView(new RedirectView("/autores"));
            //mav.addObject("error", e.getMessage());
        }
        mav.addObject("tittle", "Modificar Autor");
        mav.addObject("action", "modificar");
        return mav;
    }

    @PostMapping("/modificar")
    public RedirectView modificar(@RequestParam String nombre, @RequestParam String id, RedirectAttributes attributes){
        /**  
         * controlar la exepcion agregando como parametro RedirectAttributes attributes 
         * en el catch poner attributes.addFlashAttribute("error-name", e.getMessage())
        */
        try {
            autorService.modificar(nombre, id);
            
        } catch (Exception e) {
            attributes.addFlashAttribute("error-modificar", e.getMessage());
        }
        return new RedirectView("/autores");
    }

    @PostMapping("/eliminar")
    public RedirectView eliminar(@RequestParam String id, RedirectAttributes attributes) {
        
        try {
            autorService.eliminar(id);
            
        } catch (Exception e) {
            attributes.addFlashAttribute("errorAutorEliminar", e.getMessage());
        }
        return new RedirectView("/autores");
    }

    @PostMapping("/alta")
    public RedirectView alta(@RequestParam String id, RedirectAttributes attributes) {

        try {
            autorService.alta(id);
            
        } catch (Exception e) {
            attributes.addFlashAttribute("errorAutorAltar", e.getMessage());
        }
        return new RedirectView("/autores");
    }
}
