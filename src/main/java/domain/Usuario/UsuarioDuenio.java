package domain.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import domain.Asociacion.Asociacion;
import domain.Mascota.*;

import javax.persistence.*;

@Entity
@DiscriminatorValue("D")
public class UsuarioDuenio extends Usuario {

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "Id")
    List<DatoDeContacto> datoDeContactoList = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "duenio_id", referencedColumnName = "Id")
    List<Mascota> mascotasList = new ArrayList<>();

    String codigoQR;

    public void registrarMascota(Mascota mascotaNueva, Map<String, String> caracteristicas) {
        mascotaNueva.inicializarCaracteristicas(asociacion, caracteristicas);
        mascotasList.add(mascotaNueva);
    }

    public List<Mascota> getMascotasList(){
        return mascotasList;
    }

    public List<Mascota> filtrarPorNombre(String filtro) {
        return mascotasList.stream().filter(mascota -> mascota.sePareceNombreA(filtro)).collect(Collectors.toList());
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

    public String getCodigoQR() {
        return codigoQR;
    }

    @Override
    public List<DatoDeContacto> getDatoDeContactoList() {
        return datoDeContactoList;
    }

    public UsuarioDuenio() {

    }
}





