package domain.Exceptions;

public class NoTodasLasPreguntasFueronRespondidas extends RuntimeException{

  public NoTodasLasPreguntasFueronRespondidas(String message) {
    super(message);
  }
}
