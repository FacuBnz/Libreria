package com.libreria.libreria.controller;

import java.security.Principal;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;

import com.libreria.libreria.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class PrincipalController {

    @Autowired
    private ClienteService clienteService;
    
    @GetMapping("/home")
    public ModelAndView inicio(){
        return new ModelAndView("index");
    }

    @GetMapping("/login")
    public ModelAndView login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, Principal principal){
        ModelAndView mav = new ModelAndView("login");

        if (error != null) {
            mav.addObject("error", "Correo o contraseña inválida");
        }

        if (logout != null) {
            mav.addObject("logout", "Ha salido correctamente de la plataforma");
        }

        if (principal != null) {
            mav.setViewName("redirect:/home");
        }

        return mav;
    }

    @GetMapping("/signup")
    public ModelAndView signup(HttpServletRequest request, Principal principal) {
        ModelAndView mav = new ModelAndView("signup");
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);

        if (principal != null) {
            mav.setViewName("redirect:/home");
        }

        if (flashMap != null) {
            mav.addObject("exito", flashMap.get("exito"));
            mav.addObject("error", flashMap.get("error"));
            mav.addObject("nombre", flashMap.get("nombre"));
            mav.addObject("apellido", flashMap.get("apellido"));
            mav.addObject("email", flashMap.get("email"));
            mav.addObject("clave", flashMap.get("clave"));
            mav.addObject("dni", flashMap.get("dni"));
            
        }

        return mav;
    }

    @PostMapping("/registro")
    public RedirectView signup(@RequestParam String nombre, @RequestParam String apellido, @RequestParam Long dni, @RequestParam String email, @RequestParam String clave, RedirectAttributes attributes) {
        RedirectView redirectView = new RedirectView("/login");

        try {
            clienteService.created(nombre, apellido, dni, email, clave);
            attributes.addFlashAttribute("exito", "El registro ha sido realizado con exito");
        } catch (Exception e) {
            attributes.addFlashAttribute("error", e.getMessage());
            attributes.addFlashAttribute("nombre", nombre);
            attributes.addFlashAttribute("apellido", apellido);
            attributes.addFlashAttribute("dni", dni);
            attributes.addFlashAttribute("email", email);
            attributes.addFlashAttribute("clave", clave);

            redirectView.setUrl("/signup");

        }

        return redirectView;
    }
}
