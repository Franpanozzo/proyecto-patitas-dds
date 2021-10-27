package domain.Mascota;

import domain.Asociacion.Asociacion;
import domain.ClasesPersistencia.EntidadPersistente;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.*;

@Entity
public class Mascota extends EntidadPersistente{

    @Enumerated(EnumType.STRING)
    Animal tipo;

    String nombre;

    String apodo;

    Integer edadAprox;

    @Enumerated(EnumType.STRING)
    Sexo sexo;

    String descripcion;

    String foto;

    @ElementCollection
    @MapKeyColumn(name = "mascota_caracteristica")
    @Column(name = "caracteristica")
    Map<String, String> caracteristicas = new HashMap<String, String>();
    @Transient
    List<Caracteristica> caracteristicasAbstraido = new ArrayList<>();

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

    public boolean sePareceNombreA(String nombre) {
        return StringUtils.startsWithIgnoreCase(this.apodo, nombre);
    }

    public List<Caracteristica> getCaracteristicas() {
        if(caracteristicasAbstraido.size() ==0) {
            caracteristicas.forEach((k, v) -> caracteristicasAbstraido.add(new Caracteristica(k, v)));
            return caracteristicasAbstraido;
        }
        return null;
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

    public Animal getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public Integer getEdadAprox() {
        return edadAprox;
    }

    public String getSexo() {
        return sexo.toString();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFoto() {
        return foto;
    }
}



