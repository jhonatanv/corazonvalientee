package co.org.smart.corazonvaliente.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.org.smart.corazonvaliente.ejb.AporteEJB;
import co.org.smart.corazonvaliente.entidades.AporteUsuario;
import co.org.smart.corazonvaliente.seguridad.Secured;
import co.org.smart.corazonvaliente.web.util.AporteSedeDTO;
import co.org.smart.corazonvaliente.web.util.RespuestaDTO;
import co.org.smart.corazonvaliente.web.util.UsuarioBuscarDTO;

@Secured
@Path("/aporte")
public class AporteRest {

	@EJB
	private AporteEJB aporteEJB;

	/**
	 * crea un aporte
	 * 
	 * @param aporte
	 *            el aporte a crear
	 * @return un dto informando si se registro el aporte o no.
	 */
	@Path("/crear")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaDTO crearAporteUsuario(AporteSedeDTO aporteSede) {
		int resp = aporteEJB.validarAporteCrear(aporteSede.getAporte(), aporteSede.getSede());
		if (resp == -3) {
			return new RespuestaDTO(true, "No se encontro un usuario con la cedula ingresada", "-3");
		} else if (resp == 0) {
			return new RespuestaDTO(true, "El aporte es mayor a lo que se debe", "-1");
		} else if (resp == 1) {
			return new RespuestaDTO(true, "El aporte ha sido registrado", "00");
		} else {
			return new RespuestaDTO(false, "El usuario se encuentra inactivo", "-2");
		}
	}

	/**
	 * lista los aportes de un usuario por cedula
	 * 
	 * @param cedula
	 *            la cedula de usuario
	 * @return un dto con los aportes del usuario
	 */
	@Path("/listarAportes")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaDTO listarAportesUsuario(UsuarioBuscarDTO usuarioSede) {
		List<AporteUsuario> aportes = aporteEJB.listarAportes(usuarioSede.getCedula(), usuarioSede.getSede().getNombre());
		if (aportes.isEmpty()) {
			return new RespuestaDTO(null, "Este usuario no tiene aportes registrados", "-2");
		} else {
			return new RespuestaDTO(aportes);
		}
	}

}
