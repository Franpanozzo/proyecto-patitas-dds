package Seguridad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OWASP {
    static List<String> TOPcontraseniasInvalidas = new ArrayList<>(Arrays.asList("12345678", "QWERTY", "ABCDEFG"));

    public static boolean contraseniaDebil(String unaContrasenia) {
           return TOPcontraseniasInvalidas.contains(unaContrasenia);
    }
}
