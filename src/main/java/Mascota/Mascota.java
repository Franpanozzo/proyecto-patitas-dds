package Mascota;

import java.util.ArrayList;
import java.util.List;


public class Mascota {
    Animal tipo;
    String nombre;
    String apodo;
    Integer edadAprox;
    Sexo sexo;
    String descripcion;
    String foto;
    List<String> caracteristicas = new ArrayList<String>();


    public Mascota(Animal tipo, String nombre, String apodo, Integer edadAprox, Sexo sexo, String descripcion, String foto, List<String> caracteristicas) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.apodo = apodo;
        this.edadAprox = edadAprox;
        this.sexo = sexo;
        this.descripcion = descripcion;
        this.foto = foto;
        this.caracteristicas = caracteristicas;
    }
}


