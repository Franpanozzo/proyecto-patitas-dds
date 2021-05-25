package Mascota;

import Asociacion.Asociacion;
import EntidadesExternas.Rescatista;
import FormasDeEncuentro.FormaDeEncuentro;
//import jdk.vm.ci.meta.Local;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;


public class MascotaPerdida {
    Rescatista rescatista;
    String foto;
    String descripcionEstado;
    Coordenadas lugarDeEncuentro;
    LocalDate fechaEncuentro;
    String codigoQR;
    FormaDeEncuentro formaDeEncuentro;

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

    public MascotaPerdida(Rescatista rescatista, String foto, String descripcionEstado, Coordenadas lugarDeEncuentro, LocalDate fechaEncuentro, String codigoQR) {
        this.rescatista = rescatista;
        this.foto = foto;
        this.descripcionEstado = descripcionEstado;
        this.lugarDeEncuentro = lugarDeEncuentro;
        this.fechaEncuentro = fechaEncuentro;
        this.codigoQR = codigoQR;
    }

    public String getCodigoQR(){
        return codigoQR;
    }

    public boolean encontradaDespuesDe(LocalDate fechaLimite){
        return fechaEncuentro.isAfter(fechaLimite);
    }

    public void buscarAsociacionMasCercana(List<Asociacion> asociacionesPosibles) {

        formaDeEncuentro.ejecutarAccion(asociacionesPosibles,this);

        //Si no tiene QR

        //

    }

    public double distanciaAEncuentro(Coordenadas direccion) {
       return lugarDeEncuentro.distanciaA(direccion);
    }
}
