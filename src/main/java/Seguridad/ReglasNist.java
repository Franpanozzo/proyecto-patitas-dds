package Seguridad;

import java.util.List;

public class ReglasNist {
        public boolean cumpleLargo(String unaContrasenia) {
            Integer largoMin = 8;
            Integer largoMax = 64;
            Integer longitud = unaContrasenia.length();

            return(largoMin <= longitud && longitud <= largoMax);
        }

        public boolean usuarioYContraDistintos(String unaContrasenia, String nombreUsuario){
            return !(nombreUsuario.equals(unaContrasenia));
        }

        public boolean mismoCaracterRepetido(String unaContrasenia) {
            char[] ArrayContrasenia = unaContrasenia.toCharArray();
            char primerCaracter = ArrayContrasenia[0];
            Integer i = 0, largoContrasenia = unaContrasenia.length();

            while(i < largoContrasenia && primerCaracter == ArrayContrasenia[i])
            {
                i++;
            }
            return largoContrasenia == i;
        }

        public boolean esIncremental(String unaContrasenia) {
            char[] ArrayContrasenia = unaContrasenia.toCharArray();
            char primerCaracter = ArrayContrasenia[0];
            int i = 0, largoContrasenia = unaContrasenia.length();


            while(i < largoContrasenia && (primerCaracter + i)== ArrayContrasenia[i]){
                i++;}
            return largoContrasenia == i;
        }

        public boolean esDecremental(String unaContrasenia) {
            char[] ArrayContrasenia = unaContrasenia.toCharArray();
            char primerCaracter = ArrayContrasenia[0];
            int i=0, largoContrasenia = unaContrasenia.length();

            while(i< largoContrasenia && (primerCaracter - i)== ArrayContrasenia[i]){
                i++;}
            return largoContrasenia == i;
        }

        public boolean cumpleReglasSintacticas(String unaContrasenia) {
            Character clave;
            Integer contN = 0;
            Integer contLMay =0;
            Integer contLMin = 0;

            for(Integer i=0; i< unaContrasenia.length(); i++){
                clave = unaContrasenia.charAt(i);
                String passValue = String.valueOf(clave);

                if(passValue.matches("[A-Z]")){
                    contLMay++;
                }
                else if(passValue.matches("[a-z]")){
                    contLMin++;
                }
                else if (passValue.matches("[0-9]")){
                    contN++;
                }
            }

            if((contN !=0) && (contLMay !=0) && (contLMin != 0)) {
                return false;
            }

            return true;
        }
    }

