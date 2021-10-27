package controllers;

import domain.Repositorios.RepositorioUsuarios;
import domain.Usuario.*;
import org.apache.commons.lang3.RandomStringUtils;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class UsuariosController{

  public ModelAndView loggear(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    String usuario = request.queryParams("nombre");
    String password = request.queryParams("password");
    Usuario usuarioEncontrado = RepositorioUsuarios.getInstance().usuarioConNombre(usuario);

    if (usuarioEncontrado == null || !usuarioEncontrado.getContrasenia().equals(password)) {
      //Aca podemos imprimir un mensaje en el coso que esta mal el nombre de usuario o contraseña
      model.put("failLog","failLog");
      model.put("botonSignUp","botonSignUp");
      return new ModelAndView(model, "login.hbs");
    }

    request.session().attribute("usuario_logueado", usuario);
    response.redirect("/mascotas");
    return null;
  }


  public ModelAndView guardar(Request req, Response resp) {
    Map<String, Object> model = new HashMap<>();
    String contrasenia = req.queryParams("password");
    String coonfirmContra = req.queryParams("passwordConfirm");

    if(!contrasenia.equals(coonfirmContra)) {
      model.put("failContra","fallo");
      return new ModelAndView(model,"signup.hbs");
    }

    String codigoQR = RandomStringUtils.randomAlphabetic(10);
    String nombreApellido = req.queryParams("nombre") + " " + req.queryParams("apellido");

    DatosPersonales datosPersonales = new DatosPersonales(nombreApellido,
        LocalDate.parse(req.queryParams("fechaNac")),
        TipoDocumento.valueOf(req.queryParams("tipoDoc")),
        Integer.parseInt(req.queryParams("numeroDoc"))
        );

    DatoDeContacto datoDeContacto = new DatoDeContacto(nombreApellido, Integer.parseInt(req.queryParams("telefono")), req.queryParams("email"));

    UsuarioDuenio usuarioDuenio = new UsuarioDuenio(
        req.queryParams("nombreUsuario"),
        contrasenia,
        null,
        datosPersonales,
        Collections.singletonList(datoDeContacto),
        codigoQR
    );

    RepositorioUsuarios.getInstance().cargarNuevoUsuario(usuarioDuenio);

    req.session().attribute("usuario_logueado", req.queryParams("nombreUsuario"));
    resp.redirect("/");
    return null;
  }
}

