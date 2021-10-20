package domain.Mascota;

import domain.ClasesPersistencia.EntidadPersistente;
import domain.EntidadesExternas.Rescatista;
//import jdk.vm.ci.meta.Local;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.List;

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
