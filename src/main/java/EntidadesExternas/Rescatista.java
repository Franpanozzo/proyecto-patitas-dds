package EntidadesExternas;

import Asociacion.Asociacion;
import FormasDeEncuentro.FormaDeEncuentro;
import Usuario.DatoDeContacto;
import Mascota.Coordenadas;
import Mascota.MascotaPerdida;
import Usuario.DatosPersonales;

import java.util.ArrayList;
import java.util.List;

public class Rescatista {
    DatosPersonales datosPersonales;
    Coordenadas direccion;
    List<DatoDeContacto> contacto = new ArrayList<DatoDeContacto>();

    public Rescatista(DatosPersonales datosPersonales, Coordenadas direccion, List<DatoDeContacto> contacto) {
        this.datosPersonales = datosPersonales;
        this.direccion = direccion;
        this.contacto = contacto;
    }

    //Middle man??
    public void informarMascotaEncontrada(MascotaPerdida mascota, FormaDeEncuentro formaDeEncuentro) {
       formaDeEncuentro.ejecutarAccion(mascota);
    }
}








