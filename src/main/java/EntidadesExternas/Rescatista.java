package EntidadesExternas;

import Asociacion.Asociacion;
import Usuario.DatoDeContacto;
import Mascota.Coordenadas;
import Mascota.MascotaPerdida;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Rescatista {
    String nombreYApellido;
    LocalDate fechaNacimiento;
    String tipoDocumento;
    Integer nroDocumento;
    Coordenadas direccion;
    List<DatoDeContacto> contacto = new ArrayList<DatoDeContacto>();

    public Rescatista(String nombreYApellido, LocalDate fechaNacimiento, String tipoDocumento, Integer nroDocumento, Coordenadas direccion, List<DatoDeContacto> contacto) {
        this.nombreYApellido = nombreYApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
        this.direccion = direccion;
        this.contacto = contacto;
    }

    public void informarMascotaEncontrada(MascotaPerdida mascota,Asociacion asociacion) {
        asociacion.cargarMascota(mascota);
    }
}
