package Exceptions;

public class MailInvalidoException extends RuntimeException{

  public MailInvalidoException(String message) {
    super(message);
  }
}
