package Notificacion;

import Usuario.DatoDeContacto;

public interface FormaDeNotificar {
  void enviarNotificacion(DatoDeContacto dato, String asunto, String mensaje);

}
