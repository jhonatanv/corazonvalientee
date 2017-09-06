package co.org.smart.corazonvaliente.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.org.smart.corazonvaliente.ejb.SeguimientoEJB;
import co.org.smart.corazonvaliente.entidades.Seguimiento;
import co.org.smart.corazonvaliente.seguridad.Secured;
import co.org.smart.corazonvaliente.web.util.RespuestaDTO;
import co.org.smart.corazonvaliente.web.util.SeguimientoDTO;

@Secured
@Path("/seguimiento")
public class SeguimientoRest {

	/**
	 * ejb de la clase seguimiento
	 */
	@EJB
	private SeguimientoEJB seguimientoEJB;

	/**
	 * crea un seguimiento
	 * 
	 * @param seguimiento
	 *            el seguimiento a crear
	 * @return dto informando si se pudo crear o no
	 */
	@POST
	@Path("/crear")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaDTO crearSeguimineto(SeguimientoDTO seguimientoDTO) {
		
		if (seguimientoDTO != null) {
			
			seguimientoEJB.crear(seguimientoDTO.getUser(), seguimientoDTO.getSeguimiento().getSede(),  seguimientoDTO.getSeguimiento().getUsuario(),  seguimientoDTO.getSeguimiento().getFecha(),  seguimientoDTO.getSeguimiento().getObservacion());
			return new RespuestaDTO(true, "El seguimiento ha sido registrado correctamente", "00");
		} else {
			return new RespuestaDTO(false, "Error al crear el seguimiento, campos vacios", "-2");
		}
	}

	/**
	 * busca los seguimientos de un usuario
	 * 
	 * @param cedula
	 *            la cedula del usuario
	 * @return una lista con seguimientos de un usuario
	 */
	@POST
	@Path("/buscarSeguimientos")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public RespuestaDTO buscarSeguimientos(@FormParam(value = "cedula") String cedula) {
		List<Seguimiento> lista = seguimientoEJB.listarSeguimientos(cedula);
		if (!lista.isEmpty()) {
			return new RespuestaDTO(lista);
		} else {
			return new RespuestaDTO(null, "No se encontraron seguimientos para el usuario con la cedula " + cedula,
					"-1");
		}
	}

}
