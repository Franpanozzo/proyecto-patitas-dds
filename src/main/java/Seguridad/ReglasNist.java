package Seguridad;

import java.util.List;

public class ReglasNist {
    public static boolean cumpleLargo(String unaContrasenia) {
        Integer largoMin = 8;
        Integer largoMax = 64;
        Integer longitud = unaContrasenia.length();

        return largoMin <= longitud && longitud <= largoMax;
    }

    public static boolean usuarioYContraDistintos(String unaContrasenia, String nombreUsuario) {
        return !(nombreUsuario.equals(unaContrasenia));
    }

    public static boolean mismoCaracterRepetido(String unaContrasenia) {
        char[] ArrayContrasenia = unaContrasenia.toCharArray();
        Integer i;
        Integer j;

        for (i = 0; i < ArrayContrasenia.length; i++) {
            for (j = i + 1; j < ArrayContrasenia.length - i; j++) {
                if (ArrayContrasenia[j] == ArrayContrasenia[i]) {
                    return true;
                }
            }
        }

        return false;
    }


    public static boolean esIncremental(String unaContrasenia) {
        char[] ArrayContrasenia = unaContrasenia.toCharArray();
        Integer i;
        Integer contador = 0;

        for (i = 0; i < ArrayContrasenia.length - 1; i++) {
            char actual = ArrayContrasenia[i];
            char next = ArrayContrasenia[i + 1];

            if (Math.abs(actual - next) == 1) {
                contador++;
            } else {
                contador = 0;
            }

            if (contador == 4) return true;
        }
        return false;
    }

    public static boolean esDecremental(String unaContrasenia) {
        char[] ArrayContrasenia = unaContrasenia.toCharArray();
        Integer i;
        Integer contador = 0;

        for (i = 0; i < ArrayContrasenia.length - 1; i++) {
            char actual = ArrayContrasenia[i];
            char next = ArrayContrasenia[i + 1];

            if (Math.abs(next - actual) == 1) {
                contador++;
            } else {
                contador = 0;
            }

            if (contador == 4) return true;
        }
        return false;
    }


    public static boolean cumpleReglasSintacticas(String unaContrasenia) {
        Character clave;
        Integer contN = 0;
        Integer contLMay = 0;
        Integer contLMin = 0;

        for (Integer i = 0; i < unaContrasenia.length(); i++) {
            clave = unaContrasenia.charAt(i);
            String passValue = String.valueOf(clave);

            if (passValue.matches("[A-Z]")) {
                contLMay++;
            } else if (passValue.matches("[a-z]")) {
                contLMin++;
            } else if (passValue.matches("[0-9]")) {
                contN++;
            }
        }

        return ((contN > 0) && (contLMay > 0) && (contLMin > 0));
    }
}

