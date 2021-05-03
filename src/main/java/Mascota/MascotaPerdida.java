package Mascota;

import EntidadesExternas.Rescatista;
//import jdk.vm.ci.meta.Local;

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

    public MascotaPerdida(Rescatista rescatista, String foto, String descripcionEstado, Coordenadas lugarDeEncuentro, LocalDate fechaEncuentro) {
        this.rescatista = rescatista;
        this.foto = foto;
        this.descripcionEstado = descripcionEstado;
        this.lugarDeEncuentro = lugarDeEncuentro;
        this.fechaEncuentro = fechaEncuentro;
    }

    public boolean encontradaEnFecha(LocalDate fecha){
        return fecha.isEqual(fechaEncuentro);
    }
}
