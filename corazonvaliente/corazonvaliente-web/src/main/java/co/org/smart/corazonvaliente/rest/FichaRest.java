package co.org.smart.corazonvaliente.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.org.smart.corazonvaliente.ejb.FichaEJB;
import co.org.smart.corazonvaliente.entidades.Ciudad;
import co.org.smart.corazonvaliente.entidades.Ficha;
import co.org.smart.corazonvaliente.entidades.Sede;
import co.org.smart.corazonvaliente.entidades.Usuario;
import co.org.smart.corazonvaliente.seguridad.Secured;
import co.org.smart.corazonvaliente.web.util.FichaSedeDTO;
import co.org.smart.corazonvaliente.web.util.RespuestaDTO;

@Secured
@Path("/ficha")
public class FichaRest {

	@EJB
	private FichaEJB fichaEJB;

	/**
	 * lista las fichas de un usuario por cedula
	 * 
	 * @param cedula
	 *            la cedula del usuario
	 * @return una lista con las fichas de un usuario
	 */
	@POST
	@Path("/listarFichas")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public RespuestaDTO buscarFuncionario(@FormParam(value = "cedula") String cedula) {
		List<Ficha> lista = fichaEJB.listarFichas(cedula);
		return new RespuestaDTO(lista);
	}

	/**
	 * edita las fichas de un usuario
	 * 
	 * @param ficha
	 *            la ficha con las observaciones y fecha de salida
	 * @return un dto informando si se pudo editar o no
	 */
	@POST
	@Path("/editarFicha")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaDTO editarUsuario(FichaSedeDTO ficha) {
		Ficha fichaB = fichaEJB.buscar(ficha.getFicha().getCodigo());
		if (fichaB != null) {
			if (fichaEJB.editarFicha(ficha.getFicha(), ficha.getSede())) {
				return new RespuestaDTO(true, "La ficha ha sido registrada", "00");
			} else {
				return new RespuestaDTO(false, "Este usuario no pertenece a esta sede", "-3");
			}
		}
		return new RespuestaDTO(false, "No se encontro una ficha con el codigo " + ficha.getFicha().getCodigo(), "-2");
	}

	/**
	 * lista los usuarios de una sede
	 * 
	 * @return un dto con los usuarios de la sede
	 */
	@POST
	@Path("/listarUsuariosSede")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaDTO listarUsuario(Sede sede) {
		List<Usuario> lista = fichaEJB.listarUsuariosSede(sede);
		if (!lista.isEmpty()) {
			return new RespuestaDTO(lista);
		} else {
			return new RespuestaDTO(false, "No hay usuarios en esta sede", "-2");
		}
	}

}
