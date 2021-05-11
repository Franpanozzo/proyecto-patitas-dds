package SeguridadAlternativa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class oswap implements ValidacionContra {
    static List<String> TOPcontraseniasInvalidas = new ArrayList<>(Arrays.asList("12345678", "QWERTY", "ABCDEFG"));

    public boolean validar(String unaContrasenia, String nombreUsuario) {
        return !TOPcontraseniasInvalidas.contains(unaContrasenia);
    }

    public String mensajeError(){
        return  "La contrasenia es muy debil. ";
    }
}
