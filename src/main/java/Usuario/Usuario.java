package Usuario;

import Asociacion.Asociacion;
import Exceptions.ContraseniaDebilException;
import Exceptions.ReglasNistException;
import Seguridad.ReglasNist;

import java.time.LocalDate;
import java.util.*;

public abstract class Usuario {
    String nombreUsuario;
    String contraseña;
    String nombreApellido;
    LocalDate fechaNacimiento;
    tipoDocumento tipoDocumento;
    Integer numeroDocumento;
    List<DatoDeContacto> datoDeContactoList = new ArrayList<>();
    List<String> TOPcontraseniasInvalidas = new ArrayList<>(Arrays.asList("12345678", "QWERTY", "ABCDEFG"));

    public abstract Usuario crearUsuario(String nombreUsuario,
                                         String contrasenia,
                                         String nombreApellido,
                                         LocalDate fechaNacimiento,
                                         tipoDocumento tipoDocumento,
                                         Integer numeroDocumento,
                                         List<DatoDeContacto> datoDeContactoList,
                                         Asociacion asociacionDondeTrabaja);

    public String validarContrasenia(String nombreUsuario, String contrasenia) {
        Objects.requireNonNull(contrasenia, "La contrasenia no tiene que ser null");
        ReglasNist reglasNist = new ReglasNist();

        if (TOPcontraseniasInvalidas.contains(contrasenia)){
            throw new ContraseniaDebilException("La contrasenia es muy debil");
        }

        if (!reglasNist.cumpleLargo(contrasenia)){
            throw new ReglasNistException("La contrasenia debe tener entre 8 y 64 caracteres");
        }

        if (!reglasNist.usuarioYContraDistintos(nombreUsuario, contrasenia)){
            throw new ReglasNistException("La contrasenia debe ser distinta al nombre de usuario");
        }

        if (!reglasNist.mismoCaracterRepetido(contrasenia)){
            throw new ReglasNistException("La contrasenia no debe tener caracteres repetitivos");
        }
        if (!reglasNist.esIncremental(contrasenia)){
            throw new ReglasNistException("La contrasenia no puede ser una secuencia de numeros");
        }
        if (!reglasNist.esDecremental(contrasenia)){
            throw new ReglasNistException("La contrasenia no puede ser una secuencia de numeros");
        }
        if (!reglasNist.cumpleReglasSintacticas(contrasenia)){
            throw new ReglasNistException("La contrasenia debe contar al menos con una letra mayuscula, minuscula y un numero");
        }
        return contrasenia;
    }

}




