package Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Asociacion.Asociacion;
import Mascota.Mascota;
import Usuario.DatoDeContacto;

public class UsuarioDuenio extends Usuario {
    List<Mascota> mascotasList = new ArrayList<>();

    public void registrarMascota(Mascota mascotaNueva) {
        mascotasList.add(mascotaNueva);
    }

    public List<Mascota> getMascotasList(){
        return mascotasList;
    }

    @Override
    public UsuarioDuenio crearUsuario(String nombreUsuario,
                                      String contrasenia,
                                      String nombreApellido,
                                      LocalDate fechaNacimiento,
                                      tipoDocumento tipoDocumento,
                                      Integer numeroDocumento,
                                      List<DatoDeContacto> datoDeContactoList,
                                      Asociacion asociacion){
        this.nombreUsuario = Objects.requireNonNull(nombreUsuario, "Nombre de usuario no tiene que ser null");
        this.contrasenia =  validarContrasenia(nombreUsuario, contrasenia);
        this.nombreApellido = Objects.requireNonNull(nombreApellido, "El nombre apellido no tiene que ser null");
        this.fechaNacimiento =Objects.requireNonNull(fechaNacimiento, "La fecha nacimiento no tiene que ser null");
        this.tipoDocumento = Objects.requireNonNull(tipoDocumento, "El tipo documento, no tiene que ser null");
        this.numeroDocumento = Objects.requireNonNull(numeroDocumento, "El numero documento no tiene que ser null");
        this.datoDeContactoList = Objects.requireNonNull(datoDeContactoList, "El dato de contacto no tiene que ser null");
        asociacion.registrarUsuario(this);
        return new UsuarioDuenio();
    }
}





