package Mascota;

import Asociacion.Asociacion;
import ClasesPersistencia.EntidadPersistente;
import EntidadesExternas.Rescatista;
import FormasDeEncuentro.FormaDeEncuentro;
//import jdk.vm.ci.meta.Local;
import Mascota.*;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Entity
public class MascotaPerdida extends EntidadPersistente {
    @Embedded
    Chapita chapita;
    @Embedded
    DatosMascotaPerdida datosMascotaPerdida;

    public MascotaPerdida(Rescatista rescatista, String foto, List<String> descripcionEstado, Coordenadas lugarDeEncuentro, LocalDate fechaEncuentro, Animal animal, Tamanio tamanio) {
        datosMascotaPerdida = new DatosMascotaPerdida(rescatista,foto,descripcionEstado,lugarDeEncuentro,fechaEncuentro, animal, tamanio);
    }

    public MascotaPerdida(){

    }

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
