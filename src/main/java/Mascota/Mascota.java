package Mascota;

import Asociacion.Asociacion;
import ClasesPersistencia.EntidadPersistente;
import Mascota.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.*;

@Entity
public class Mascota {

    @Id
    @GeneratedValue
    private long Id;

    @Transient
    Animal tipo;

    String nombre;

    String apodo;

    @Transient
    Integer edadAprox;

    @Transient
    Sexo sexo;

    String descripcion;

    @Transient
    String foto;

    @Transient
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

    public Mascota() {

    }

    public Map<String, String> getCaracteristicas() {
        return caracteristicas;
    }

    public void inicializarCaracteristicas(Asociacion asociacion, Map<String, String> caracteristicasNuevas) {
        List<String> listaCaracteristicas = asociacion.getCaracteristicasPosibles();
        listaCaracteristicas.forEach(caracteristica ->  caracteristicas.put(caracteristica, null));

        caracteristicasNuevas.forEach((k,v) -> {
            if(caracteristicas.containsKey(k))  {
                caracteristicas.put(k, v);
            }
        });
    }

}



