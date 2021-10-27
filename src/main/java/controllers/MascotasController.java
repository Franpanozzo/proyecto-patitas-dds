package controllers;

import domain.Mascota.Mascota;
import domain.Repositorios.RepositorioUsuarios;
import domain.Usuario.UsuarioDuenio;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MascotasController {
  public ModelAndView mascotas(Request request, Response response) {
    Map<String, Object> model = new HashMap<>();
    UsuarioDuenio usuario = (UsuarioDuenio) RepositorioUsuarios.getInstance().usuarioConNombre(request.session().attribute("usuario_logueado"));
    String filtroParametro = request.queryParams("likeNombre");

    List<Mascota> mascotas = Optional.ofNullable(filtroParametro)
        .map(usuario::filtrarPorNombre)
        .orElseGet(usuario::getMascotasList);

    model.put("likeNombre",filtroParametro);
    model.put("usuario",usuario);
    model.put("mascotas", mascotas);

    model.put("botonLogOut","botonLogOut");
    return new ModelAndView(model, "mascotas.hbs");

  }
}
