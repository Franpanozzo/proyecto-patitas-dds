package domain.Exceptions;

public class UsuarioNoTieneDatoDeContacto extends RuntimeException {
  public UsuarioNoTieneDatoDeContacto(String message) {
    super(message);
  }
}
