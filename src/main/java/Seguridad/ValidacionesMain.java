package Seguridad;

import Exceptions.ContraseniaDebilException;
import Exceptions.ReglasNistException;

import java.util.Objects;

import static Seguridad.ReglasNist.usuarioYContraDistintos;

public class ValidacionesMain {

    public static String validarContrasenia(String nombreUsuario, String contrasenia) {
        Objects.requireNonNull(contrasenia, "La contrasenia no tiene que ser null");

        if (OWASP.contraseniaDebil(contrasenia)) {
            throw new ContraseniaDebilException("La contrasenia es muy debil");
        }

        if (!ReglasNist.cumpleLargo(contrasenia)) {
            throw new ReglasNistException("La contrasenia debe tener entre 8 y 64 caracteres");
        }

        if (!ReglasNist.usuarioYContraDistintos(nombreUsuario, contrasenia)) {
            throw new ReglasNistException("La contrasenia debe ser distinta al nombre de usuario");
        }

        if (ReglasNist.mismoCaracterRepetido(contrasenia)) {
            throw new ReglasNistException("La contrasenia no debe tener caracteres repetitivos");
        }

        if (ReglasNist.esIncremental(contrasenia)) {
            throw new ReglasNistException("La contrasenia no puede ser una secuencia de numeros");
        }

        if (ReglasNist.esDecremental(contrasenia)) {
            throw new ReglasNistException("La contrasenia no puede ser una secuencia de numeros");
        }

        if (!ReglasNist.cumpleReglasSintacticas(contrasenia)) {
            throw new ReglasNistException("La contrasenia debe contar al menos con una letra mayuscula, minuscula y un numero");
        }
        return contrasenia;
    }
}
