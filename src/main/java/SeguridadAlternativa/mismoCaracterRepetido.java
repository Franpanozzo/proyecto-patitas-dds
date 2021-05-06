package SeguridadAlternativa;

public class mismoCaracterRepetido implements ValidacionContra {

    public boolean validar(String unaContrasenia, String usuario) {
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

}
