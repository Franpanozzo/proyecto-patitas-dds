package Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import Asociacion.Asociacion;
import Mascota.*;

public class UsuarioDuenio extends Usuario {
    List<DatoDeContacto> datoDeContactoList = new ArrayList<>();
    List<Mascota> mascotasList = new ArrayList<>();

    String codigoQR;

    public void registrarMascota(Mascota mascotaNueva, Map<String, String> caracteristicas) {
        mascotaNueva.inicializarCaracteristicas(asociacion, caracteristicas);
        mascotasList.add(mascotaNueva);
    }

    public List<Mascota> getMascotasList(){
        return mascotasList;
    }

    public UsuarioDuenio(String nombreUsuario, String contrasenia, Asociacion asociacion,DatosPersonales datosPersonales, List<DatoDeContacto> datoDeContactoList, String codigoQR) {
        super(nombreUsuario, contrasenia, asociacion, datosPersonales);
        this.datoDeContactoList = Objects.requireNonNull(datoDeContactoList, "El dato de contacto no tiene que ser null");
        this.codigoQR = Objects.requireNonNull(codigoQR, "El codigoQR no tiene que ser null");
    }

    @Override
    public boolean mismoCodigoQR(String codigoQR) {
        return this.codigoQR.equals(codigoQR);
    }

    @Override
    public String getMailContacto() {
        return datoDeContactoList.stream().findAny().get().getEmail();
    }

    @Override
    public List<DatoDeContacto> getDatoDeContactoList() {
        return datoDeContactoList;
    }
}





