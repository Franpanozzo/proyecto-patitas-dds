package Usuario;

import Exceptions.ContraseniaInvalidaException;
import Exceptions.MailInvalidoException;
import ClasesPersistencia.EntidadPersistente;

import javax.persistence.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
public class DatoDeContacto extends EntidadPersistente {
    String nombreApellido;
    Integer telefono;
    String email;

   public DatoDeContacto(String nombreApellido, Integer telefono, String email){
        this.nombreApellido = nombreApellido;
        //TODO validacion formato del telefono
        this.telefono = telefono;
        this.email = this.ValidarMail(email);
    }

  public static String ValidarMail(String email) {
      // Patron para validar el email
      Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

      Matcher mather = pattern.matcher(email);
      if(!mather.find()){
        throw new MailInvalidoException("Ingrese un mail valido");
      }
      return email;
    }

  public String getEmail() {
    return email;
  }

  public DatoDeContacto() {

  }
}

