package domain.Notificacion;
import domain.Mailer.Mail;
import domain.Usuario.DatoDeContacto;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * Ejemplo de envio de correo simple con JavaMail
 *
 * @author Chuidiang
 */
public class NotificarPorJavaMail implements FormaDeNotificar{
  /**
   * main de prueba
   *
   * @param args Se ignoran.
   */

  public void enviarNotificacion(DatoDeContacto mail, String asunto, String mensaje){
    enviarMail(new Mail(mail.getEmail(), asunto, mensaje));
  }
  public void enviarMail(Mail mailAEnviar) {
    try {
      // Propiedades de la conexión
      Properties props = new Properties();
      props.setProperty("mail.smtp.host", "smtp.gmail.com");
      props.setProperty("mail.smtp.starttls.enable", "true");
      props.setProperty("mail.smtp.port", "587");
      props.setProperty("mail.smtp.user", "proyecto.patitas.dds@gmail.com");
      props.setProperty("mail.smtp.auth", "true");
      props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

      // Preparamos la sesion
      Session session = Session.getDefaultInstance(props);

      // Construimos el mensaje
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress("proyecto.patitas.dds@gmail.com"));
      message.addRecipient(
          Message.RecipientType.TO,
          new InternetAddress(mailAEnviar.getMail()));
      message.setSubject(mailAEnviar.getSubject());
      message.setText(
          mailAEnviar.mensajeAEnviar());

      // Lo enviamos.
      Transport t = session.getTransport("smtp");
      t.connect("proyecto.patitas.dds@gmail.com", "patitas1234");
      t.sendMessage(message, message.getAllRecipients());

      // Cierre.
      t.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
