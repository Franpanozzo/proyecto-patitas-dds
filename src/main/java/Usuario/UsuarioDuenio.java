package Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import Asociacion.Asociacion;
import Mascota.Mascota;

public class UsuarioDuenio extends Usuario {
    String nombreApellido;
    LocalDate fechaNacimiento;
    TipoDocumento tipoDocumento;
    Integer numeroDocumento;
    List<DatoDeContacto> datoDeContactoList = new ArrayList<>();
    List<Mascota> mascotasList = new ArrayList<>();

    /*public UsuarioDuenio(String pepe12, String ahz1e38dJmnp, Asociacion.Asociacion patitas, LocalDate fechaAntigua, Usuario.tipoDocumento dni, int i, List<Usuario.DatoDeContacto> asList) {
        super();
    }*/

    public void registrarMascota(Mascota mascotaNueva, Map<String, String> caracteristicas) {
        mascotaNueva.inicializarCarcteristicas(asociacion, caracteristicas);
        mascotasList.add(mascotaNueva);
    }

    public List<Mascota> getMascotasList(){
        return mascotasList;
    }

    public UsuarioDuenio(String nombreUsuario, String contrasenia, Asociacion asociacion, String nombreApellido, LocalDate fechaNacimiento, TipoDocumento tipoDocumento, Integer numeroDocumento, List<DatoDeContacto> datoDeContactoList) {
        super(nombreUsuario, contrasenia, asociacion);
        this.nombreApellido = Objects.requireNonNull(nombreApellido, "El nombre apellido no tiene que ser null");
        this.fechaNacimiento =Objects.requireNonNull(fechaNacimiento, "La fecha nacimiento no tiene que ser null");
        this.tipoDocumento = Objects.requireNonNull(tipoDocumento, "El tipo documento, no tiene que ser null");
        this.numeroDocumento = Objects.requireNonNull(numeroDocumento, "El numero documento no tiene que ser null");
        this.datoDeContactoList = Objects.requireNonNull(datoDeContactoList, "El dato de contacto no tiene que ser null");
    }

}





