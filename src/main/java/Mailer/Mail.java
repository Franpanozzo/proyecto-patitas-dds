package Mailer;

public class Mail {
  String destinatario;
  String asunto;
  String mensaje;

  public Mail(String destinatario, String asunto, String mensaje) {
    this.destinatario = destinatario;
    this.asunto = asunto;
    this.mensaje = mensaje;
  }

  public String getSubject() {
    return asunto;
  }

  public String mensajeAEnviar() {
    return mensaje;
  }

  public String getMail() {
    return destinatario;
  }
}
