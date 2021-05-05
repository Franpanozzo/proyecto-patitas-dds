/**package Mascota;

import Asociacion.Asociacion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MascotaBuilder{

    Animal tipo;
    String nombre;
    String apodo;
    Integer edadAprox;
    Sexo sexo;
    String descripcion;
    String foto;
    List<String> caracteristicas = new ArrayList<String>();

    void setTipo(Animal tipo){
        this.tipo = tipo;
    }

    void setNombre(String nombre){
        this.nombre = nombre;
    }

    void setApodo(String apodo){
        this.apodo = apodo;
    }

    void setEdadAprox(Integer edadAprox){
        this.edadAprox = edadAprox;
    }

    void setSexo(Sexo sexo){
        this.sexo = sexo;
    }

    void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    void setFoto(String foto){
        this.foto = foto;
    }

    void setCaracteristicas(List<String> caracteristicas,Asociacion asociacion){
        caracteristicas.stream().filter(asociacion::esCaractPosible).collect(Collectors.toList());
        this.caracteristicas = caracteristicas;
    }

    public Mascota crearMascota(){
        return new Mascota(tipo, nombre, apodo, edadAprox, sexo, descripcion, foto, caracteristicas);
    }
}
*/