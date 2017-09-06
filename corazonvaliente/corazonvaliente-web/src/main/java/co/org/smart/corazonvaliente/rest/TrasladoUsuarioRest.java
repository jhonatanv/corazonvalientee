package co.org.smart.corazonvaliente.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.org.smart.corazonvaliente.dto.TrasladoUsuarioDTO;
import co.org.smart.corazonvaliente.ejb.TrasladoUsuarioEJB;
import co.org.smart.corazonvaliente.seguridad.Secured;
import co.org.smart.corazonvaliente.web.util.RespuestaDTO;

@Secured
@Path("/trasladoUsuario")
public class TrasladoUsuarioRest {

	@EJB
	private TrasladoUsuarioEJB trasladoUsuarioEJB;

	/**
	 * crea un traslado para un usuario
	 * 
	 * @param traslado
	 *            el traslado con el usuario y la nueva sede
	 * @return un dto informando el resultado de la opetacion
	 */
	@POST
	@Path("/trasladar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaDTO crearTraslado(TrasladoUsuarioDTO traslado) {
		int resp = trasladoUsuarioEJB.realizarTraslado(traslado);
		if (resp == 0) {
			return new RespuestaDTO(false, "Este usuario no se encuentra registrado", "-1");
		} else {
			return new RespuestaDTO(true, "El traslado ha sido realizado", "00");
		}
	}
}
