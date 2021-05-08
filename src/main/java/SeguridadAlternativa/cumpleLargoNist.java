package SeguridadAlternativa;

public class cumpleLargoNist implements ValidacionContra {

    public boolean validar(String unaContrasenia, String nombreUsaurio) {
        Integer largoMin = 8;
        Integer largoMax = 64;
        Integer longitud = unaContrasenia.length();

        return (largoMin <= longitud && longitud <= largoMax);
    }

}
