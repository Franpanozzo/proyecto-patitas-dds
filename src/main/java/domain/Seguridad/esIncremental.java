package domain.Seguridad;

public class esIncremental implements ValidacionContra {

    public boolean validar(String unaContrasenia, String usuario) {
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

            if (contador == 4) return false;
        }
        return true;
    }

    public String mensajeError(){
        return "La contrasenia no puede ser una secuencia de numeros. ";
    }
}
