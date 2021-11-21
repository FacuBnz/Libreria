package com.libreria.libreria.utils;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validaciones {

    public static String titulo(String titulo) throws Exception {
        
        if(titulo.length() != 0){
            return titulo;
        }
        
        throw new Exception("El titulo no es valido");
    }

    public static Integer anio(Integer anio) throws Exception {
        
        Pattern pattern = Pattern.compile("^([1-9]+\\d*)|[0]");

        Matcher matcher = pattern.matcher(Integer.toString(anio));

        boolean matchFound = matcher.find();
        
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);

        if(!matchFound || anio > year || anio < 0) {
            throw new Exception("El año no es valido");
        }
        return anio;
    }

    public static Integer ejemplares(Integer ejemplares) throws Exception {

        Pattern pattern = Pattern.compile("^([1-9]+\\d*)|[0]");

        Matcher matcher = pattern.matcher(Integer.toString(ejemplares));

        boolean matchFound = matcher.find();

        if(!matchFound || ejemplares < 0) {
            throw new Exception("El numero de ejemplares no es valido");
        }

        return ejemplares;
    }

    public static Long isbn(Long isbn) throws Exception {

        String val = Long.toString(isbn);

        Pattern pattern = Pattern.compile("^([1-9]+\\d*)|[0]");

        Matcher matcher = pattern.matcher(val);

        boolean matchFound = matcher.find();

        if(!matchFound || val.length() != 13) {
            throw new Exception("El numero de isbn no es valido");
        }

        return isbn;
    }

    
    public static String autor(String autor) throws Exception {
        if(autor.length() != 0){
            return autor;
        }

        throw new Exception("El autor no es valido");
    }

    public static String editorial(String editorial) throws Exception {
        if(editorial.length() != 0){
            return editorial;
        }

        throw new Exception("La editorial no es valido");
    }
}
