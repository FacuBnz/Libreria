package com.libreria.libreria.controller;

import org.springframework.web.servlet.ModelAndView;

public class PrincipalController {
    
    public ModelAndView inicio(){
        return new ModelAndView("index");
    }
}
