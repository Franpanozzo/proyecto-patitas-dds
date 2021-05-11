package SeguridadAlternativa;

import Exceptions.ContraseniaDebilException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Validaciones {
    private static List<ValidacionContra> validacionesRecomendadas = Arrays.asList(new cumpleLargoNist(),
            new usuarioContraDistintoNist(), new esDecremental(), new esIncremental(), new mismoCaracterRepetido(),
            new oswap(), new cumpleReglasSint());

    /*Si se quiere agregar otra nueva recomendacion para la contra solo se tiene que agregar a la lista y tiene que
      implementar el metodo validar respetando la interfazo de una ValidacionContra*/

    public static String validarContrasenia(String contrasenia, String nombreUsuario) {
        Objects.requireNonNull(contrasenia, "La contrasenia no puede ser nula. ");
        List<ValidacionContra> validacionIncumplidas = noCumpleValidaciones(contrasenia, nombreUsuario);

        if (!validacionIncumplidas.isEmpty()) {
            throw new ContraseniaDebilException(mensajeError(validacionIncumplidas));
        }
        return contrasenia;
    }

    public static String mensajeError(List<ValidacionContra> lista) {
        List<String> lista2 = lista.stream().map(ValidacionContra::mensajeError).collect(Collectors.toList());
        return lista2.toString();
    }


    public static List<ValidacionContra> noCumpleValidaciones(String contrasenia, String nombreUsuario) {
        return validacionesRecomendadas.stream().filter(validacion -> !validacion.validar(contrasenia, nombreUsuario)).collect(Collectors.toList());
    }
}