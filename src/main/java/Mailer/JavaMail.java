package Mailer;

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
public class JavaMail {
  /**
   * main de prueba
   *
   * @param args Se ignoran.
   */
  public static void enviarMail(Mail mailAEnviar) {
    try {
      // Propiedades de la conexión
      Properties props = new Properties();
      props.setProperty("mail.smtp.host", "smtp.gmail.com");
      props.setProperty("mail.smtp.starttls.enable", "true");
      props.setProperty("mail.smtp.port", "587");
      props.setProperty("mail.smtp.user", "asociacion.patitas.dds@gmail.com");
      props.setProperty("mail.smtp.auth", "true");
      props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

      // Preparamos la sesion
      Session session = Session.getDefaultInstance(props);

      // Construimos el mensaje
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress("asociacion.patitas.dds@gmail.com"));
      message.addRecipient(
          Message.RecipientType.TO,
          new InternetAddress(mailAEnviar.getMail()));
      message.setSubject(mailAEnviar.getSubject());
      message.setText(
          mailAEnviar.mensajeAEnviar());

      // Lo enviamos.
      Transport t = session.getTransport("smtp");
      t.connect("asociacion.patitas.dds@gmail.com", "patitas123");
      t.sendMessage(message, message.getAllRecipients());

      // Cierre.
      t.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}