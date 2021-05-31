package Mascota;

import Asociacion.Asociacion;
import EntidadesExternas.Rescatista;
import FormasDeEncuentro.FormaDeEncuentro;
//import jdk.vm.ci.meta.Local;
import Mascota.*;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


public class MascotaPerdida {
    Chapita chapita;
    DatosMascotaPerdida datosMascotaPerdida;

    public MascotaPerdida(Rescatista rescatista, String foto, String descripcionEstado, Coordenadas lugarDeEncuentro, LocalDate fechaEncuentro, Animal animal, Tamanio tamanio) {
        datosMascotaPerdida = new DatosMascotaPerdida(rescatista,foto,descripcionEstado,lugarDeEncuentro,fechaEncuentro, animal, tamanio);
    }

    /*public MascotaPerdida(Rescatista rescatista, String foto, String descripcionEstado, Coordenadas lugarDeEncuentro, LocalDate fechaEncuentro, Optional<Chapita> chapita) {
        this.rescatista = rescatista;
        this.foto = foto;
        this.descripcionEstado = descripcionEstado;
        this.lugarDeEncuentro = lugarDeEncuentro;
        this.fechaEncuentro = fechaEncuentro;
        this.chapita = chapita.;
    }*/

    public Chapita getChapita(){
        return chapita;
    }

    public void setChapita(Chapita chapita) {
        this.chapita = chapita;
    }

    public double distanciaAEncuentro(Coordenadas direccion) {
        return datosMascotaPerdida.getLugarDeEncuentro().distanciaA(direccion);
    }


    public void buscarDuenioCorrespondiente() {
        chapita.identificarDuenioEnAsociacion();
    }

    public DatosMascotaPerdida getDatosMascotaPerdida() {
        return datosMascotaPerdida;
    }
}
