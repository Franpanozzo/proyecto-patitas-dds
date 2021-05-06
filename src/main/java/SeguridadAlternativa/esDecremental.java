package SeguridadAlternativa;


//Se podria buscar combinar con la de incremental
public class esDecremental implements ValidacionContra {

    public boolean validar(String unaContrasenia, String usuario) {
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


}
