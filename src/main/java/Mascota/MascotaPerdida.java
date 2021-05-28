package Mascota;

import Asociacion.Asociacion;
import EntidadesExternas.Rescatista;
import FormasDeEncuentro.FormaDeEncuentro;
//import jdk.vm.ci.meta.Local;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


public class MascotaPerdida {
    Chapita chapita;
    DatosMascotaPerdida datosMascotaPerdida;

    public MascotaPerdida(Rescatista rescatista, String foto, String descripcionEstado, Coordenadas lugarDeEncuentro, LocalDate fechaEncuentro) {
        datosMascotaPerdida = new DatosMascotaPerdida(rescatista,foto,descripcionEstado,lugarDeEncuentro,fechaEncuentro);
    }

    public MascotaPerdida(Rescatista rescatista, String foto, String descripcionEstado, Coordenadas lugarDeEncuentro, LocalDate fechaEncuentro, String codigoQR) {
        this.rescatista = rescatista;
        this.foto = foto;
        this.descripcionEstado = descripcionEstado;
        this.lugarDeEncuentro = lugarDeEncuentro;
        this.fechaEncuentro = fechaEncuentro;
        this.chapita = chapita.;
    }*/

    public boolean encontradaDespuesDe(LocalDate fechaLimite){
        return fechaEncuentro.isAfter(fechaLimite);
    }

    public double distanciaAEncuentro(Coordenadas direccion) {
        return datosMascotaPerdida.getLugarDeEncuentro().distanciaA(direccion);
    }

    public void buscarDuenioCorrespondiente() {
        chapita.identificarDuenioEnAsociacion();
    }
}
