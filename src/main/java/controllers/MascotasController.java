package controllers;

import domain.Asociacion.Asociacion;
import domain.Mascota.Animal;
import domain.Mascota.Mascota;
import domain.Mascota.Sexo;
import domain.Repositorios.RepositorioAsociaciones;
import domain.Repositorios.RepositorioUsuarios;
import domain.Usuario.Usuario;
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

  public ModelAndView guardar(Request req, Response res) {
    Map<String, Object> model = new HashMap<>();
    UsuarioDuenio usuario_logueado = (UsuarioDuenio) RepositorioUsuarios.getInstance().usuarioConNombre(req.session().attribute("usuario_logueado"));

    Mascota mascota = new Mascota(Animal.valueOf(req.queryParams("Animal")),
        req.queryParams("nombre"),
        req.queryParams("apodo"),
        Integer.parseInt(req.queryParams("edad")),
        Sexo.valueOf(req.queryParams("sexo")),
        req.queryParams("descrip"),
        req.queryParams("foto")
        );

    Map<String,String> caracteristicas = new HashMap<>();
    caracteristicas.put(req.queryParams("tipoCaract"),req.queryParams("caract"));
    if(req.queryParams("tipoCaract") != null){
      caracteristicas.put(req.queryParams("tipoCaract2"),req.queryParams("caract2"));
    }

    System.out.println("EEE " + caracteristicas.toString());

    usuario_logueado.registrarMascota(mascota, caracteristicas);
    RepositorioUsuarios.getInstance().modificarUsuario(usuario_logueado);

    res.redirect("/mascotas");
    return null;
  }

}
