package co.org.smart.corazonvaliente.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.org.smart.corazonvaliente.ejb.CiudadEJB;
import co.org.smart.corazonvaliente.entidades.Ciudad;
import co.org.smart.corazonvaliente.seguridad.Secured;
import co.org.smart.corazonvaliente.web.util.RespuestaDTO;

@Secured
@Path("/ciudad")
public class CiudadRest {

	@EJB
	private CiudadEJB ciudadEJB;
	
	@Path("/listarCiudades")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO listarCiudades() {
		List<Ciudad> lista = ciudadEJB.listarCiudades();
		if (!lista.isEmpty()) {
			return new RespuestaDTO(lista);
		} else {
			return new RespuestaDTO(false, "error al cargar ciudades", "-2");
		}
	}
}
