package Mascota;

import EntidadesExternas.Rescatista;
import jdk.vm.ci.meta.Local;

import java.time.LocalDate;

public class MascotaPerdida {

    Rescatista rescatista;
    //Foto foto;
    String descripcionEstado;
    Coordenadas lugarDeEncuentro;
    LocalDate fechaEncuentro;

    public LocalDate getFechaEncuentro(){
        return fechaEncuentro;
    }

    public boolean encontradaEnFecha(LocalDate fecha){
        return fecha.isEqual(fechaEncuentro);
    }
}
