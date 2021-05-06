package Mascota;

import Asociacion.Asociacion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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


    public void chequearCaracteristicasSegun(Asociacion asociacion) {
        this.caracteristicas = caracteristicas.stream().filter(asociacion::esCaractPosible).collect(Collectors.toList());
        ;
    }
}


