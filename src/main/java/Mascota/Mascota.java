package Mascota;

import Asociacion.Asociacion;
import Mascota.*;
import java.util.*;

public class Mascota {
    Animal tipo;
    String nombre;
    String apodo;
    Integer edadAprox;
    Sexo sexo;
    String descripcion;
    String foto;
    Map<String, String> caracteristicas = new HashMap<String, String>();

    public Mascota(Animal tipo, String nombre, String apodo, Integer edadAprox, Sexo sexo, String descripcion, String foto) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.apodo = apodo;
        this.edadAprox = edadAprox;
        this.sexo = sexo;
        this.descripcion = descripcion;
        this.foto = foto;
    }

    public Map<String, String> getCaracteristicas() {
        return caracteristicas;
    }

    public void inicializarCarcteristicas(Asociacion asociacion, Map<String, String> caracteristicasNuevas) {
        List<String> listaCaracteristicas = asociacion.getCaracteristicasPosibles();
        for (String caracteristica : listaCaracteristicas) {
            caracteristicas.put(caracteristica, null);
        }
        Set<String> keyscaracteristicasUsuario = caracteristicasNuevas.keySet();
        for ( String key : keyscaracteristicasUsuario) {
            if (caracteristicas.containsKey(key)){
                caracteristicas.put(key, caracteristicasNuevas.get(key));
            }
        };



    }
}


