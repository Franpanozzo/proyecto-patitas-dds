package Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import Asociacion.Asociacion;
import Mascota.Mascota;

public class UsuarioDuenio extends Usuario {
    DatosPersonales datosPersonales;
    List<DatoDeContacto> datoDeContactoList = new ArrayList<>();
    List<Mascota> mascotasList = new ArrayList<>();
    String codigoQR;

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

    public UsuarioDuenio(String nombreUsuario, String contrasenia, Asociacion asociacion,DatosPersonales datosPersonales, List<DatoDeContacto> datoDeContactoList) {
        super(nombreUsuario, contrasenia, asociacion);
        this.datosPersonales = Objects.requireNonNull(datosPersonales, "Los datos personales no tienen que ser null");
        this.datoDeContactoList = Objects.requireNonNull(datoDeContactoList, "El dato de contacto no tiene que ser null");
    }

    @Override
    public boolean mismoCodigoQR(String codigoQR) {
        return this.codigoQR.equals(codigoQR);
    }

    public void buscarMascota() {

    }
}





