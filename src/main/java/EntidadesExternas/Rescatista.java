package EntidadesExternas;

import Asociacion.Asociacion;
import Usuario.DatoDeContacto;
import Mascota.Coordenadas;
import Mascota.MascotaPerdida;

import java.time.LocalDate;

public class Rescatista {
    String nombreYApellido;
    LocalDate fechaNacimiento;
    String tipoDocumento;
    Integer nroDocumento;
    Coordenadas direccion;
    DatoDeContacto contacto;

    public Rescatista(String nombreYApellido, LocalDate fechaNacimiento, String tipoDocumento, Integer nroDocumento, Coordenadas direccion, DatoDeContacto contacto) {
        this.nombreYApellido = nombreYApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
        this.direccion = direccion;
        this.contacto = contacto;
    }

    public void informarMascotaEncontrada(MascotaPerdida mascota) {
        Asociacion.getInstance().cargarMascota(mascota);
    }
}
