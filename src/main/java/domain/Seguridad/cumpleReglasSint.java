package domain.Seguridad;

public class cumpleReglasSint implements ValidacionContra {

    public boolean validar(String unaContrasenia, String nombreUsuario) {
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

    public String mensajeError(){
        return "La contrasenia debe contar al menos con una letra mayuscula, minuscula y un numero. ";
    }
}

