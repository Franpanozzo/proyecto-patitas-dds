package EntidadesExternas;

import FormasDeEncuentro.FormaDeEncuentro;
import Servicios.Hogares.Hogar;
import Servicios.Hogares.ListaDeHogares;
import Usuario.DatoDeContacto;
import Mascota.Coordenadas;
import Mascota.MascotaPerdida;
import Usuario.DatosPersonales;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Rescatista {
    DatosPersonales datosPersonales;
    Coordenadas direccion;
    List<DatoDeContacto> contacto = new ArrayList<DatoDeContacto>();
    MascotaPerdida mascotaPerdida;

    public Rescatista(DatosPersonales datosPersonales, Coordenadas direccion, List<DatoDeContacto> contacto) {
        this.datosPersonales = datosPersonales;
        this.direccion = direccion;
        this.contacto = contacto;
    }

    //Middle man??
    public void informarMascotaEncontrada(MascotaPerdida mascota, FormaDeEncuentro formaDeEncuentro) {
        System.out.println(mascota.getChapita());
       formaDeEncuentro.ejecutarAccion(mascota);
       mascotaPerdida = mascota;
    }

    public List<Hogar> buscarHogares(double radio) throws IOException {
        return ListaDeHogares.hogaresQueCumplan(radio,mascotaPerdida.getDatosMascotaPerdida());
    }
}








