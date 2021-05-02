package Usuario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import Mascota.Mascota;
import Usuario.DatoDeContacto;

public class UsuarioDuenio extends Usuario {
    List<Mascota> mascotasList = new ArrayList<>();

    public void registrarMascota(Mascota mascotaNueva) {
        // new Mascota.Mascota(new ,new ,new, new, ne
        mascotasList.add(mascotaNueva);
    }

    public UsuarioDuenio(String nombreUsuario, String contraseña, String nombreApellido, LocalDate fechaNacimiento, String tipoDocumento, Integer numeroDocumento, List<DatoDeContacto> datoDeContactoList, List<Mascota> mascotasList) {
        super(nombreUsuario, contraseña, nombreApellido, fechaNacimiento, tipoDocumento, numeroDocumento, datoDeContactoList);
        this.mascotasList = mascotasList;
    }
}





