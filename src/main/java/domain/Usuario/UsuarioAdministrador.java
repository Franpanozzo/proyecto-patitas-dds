package domain.Usuario;

import domain.Asociacion.Asociacion;
import domain.Exceptions.*;
import domain.Utils.Pregunta;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;
import java.util.Locale;

@Entity
@DiscriminatorValue("A")
public class UsuarioAdministrador extends Usuario {

    public UsuarioAdministrador(String nombreUsuario, String contrasenia, Asociacion asociacion, DatosPersonales datosPersonales) {
        super(nombreUsuario, contrasenia, asociacion, datosPersonales);
    }

    @Override
    public boolean mismoCodigoQR(String codigoQR) {
        return false;
    }

    @Override
    public String getMailContacto() {
        throw new UsuarioNoPerdioMascotaException("Los usuarios administradores no pierden mascotas, por lo tanto no se los notifica");
    }

    @Override
    public List<DatoDeContacto> getDatoDeContactoList() {
        throw new UsuarioNoTieneDatoDeContacto("Los usuarios administradores no tienen dato de contacto");
    }

    public void agregarCaracteristica(String nuevaCaract) {
        asociacion.agregarCarateristica(nuevaCaract.toUpperCase());
    }

    public void quitarCaracteristica(String caract) {
        asociacion.quitarCaracteristica(caract.toUpperCase());
    }

    public void quitarPreguntaParaAdopcion(Pregunta pregunta){
        asociacion.quitarPregunta(pregunta);
    }

    public void agregarPreguntaParaAdopcion(Pregunta preguntaNueva) {
        asociacion.agregarPregunta(preguntaNueva);
    }

    public UsuarioAdministrador() {

    }

}
