package EntidadesExternas;

import Asociacion.Asociacion;
import Usuario.DatoDeContacto;
import Mascota.Coordenadas;
import Mascota.MascotaPerdida;
import Usuario.DatosPersonales;

import java.time.LocalDate;
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

    public void informarMascotaEncontrada(MascotaPerdida mascota,Asociacion asociacion) {
        asociacion.cargarMascota(mascota);
    }
}
