package SeguridadAlternativa;

import Exceptions.ContraseniaDebilException;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Validaciones {
    private List<ValidacionContra> validacionesRecomendadas = Arrays.asList(new cumpleLargoNist(),
            new usuarioContraDistintoNist(), new esDecremental(), new esIncremental(), new mismoCaracterRepetido(),
            new oswap(), new cumpleReglasSint());

    /*Si se quiere agregar otra nueva recomendacion para la contra solo se tiene que agregar a la lista y tiene que
      implementar el metodo validar respetando la interfazo de una ValidacionContra*/

    private String mensajeError = "La contrasenia debe contar al menos con una letra mayuscula, minuscula y un numero," +
            "debe tener entre 8 y 64 caracteres, " +  "debe ser distinta al nombre de usuario, " +
            "no debe tener caracteres repetitivos " + "y no puede ser una secuencia de numeros";

    public String validarContrasenia(String contrasenia, String nombreUsuario){
        Objects.requireNonNull(contrasenia, "La contrasenia no puede ser nula");
        if(!this.cumpleValidaciones(contrasenia, nombreUsuario)){
            throw new ContraseniaDebilException(mensajeError);
        }
        return contrasenia;
    }

    //concatenar mensaje de error

    public boolean cumpleValidaciones(String contrasenia, String nombreUsuario) {
        return validacionesRecomendadas.stream().allMatch(validacion -> validacion.validar(contrasenia, nombreUsuario));
    }

}
