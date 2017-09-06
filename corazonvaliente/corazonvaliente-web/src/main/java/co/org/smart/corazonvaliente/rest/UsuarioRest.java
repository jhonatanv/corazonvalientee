package co.org.smart.corazonvaliente.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.org.smart.corazonvaliente.ejb.UsuarioEJB;
import co.org.smart.corazonvaliente.entidades.Sede;
import co.org.smart.corazonvaliente.entidades.Usuario;
import co.org.smart.corazonvaliente.seguridad.Secured;
import co.org.smart.corazonvaliente.web.util.RespuestaDTO;
import co.org.smart.corazonvaliente.web.util.UsuarioBuscarDTO;
import co.org.smart.corazonvaliente.web.util.UsuarioDTO;

@Secured
@Path("/usuario")
public class UsuarioRest {

	/**
	 * ejb de la clase usuario
	 */
	@EJB
	private UsuarioEJB usuarioEJB;

	/**
	 * crea un usuario
	 * 
	 * @param usuario
	 *            el usuario a crear
	 * @return dto informando si se pudo crear o no
	 */
	@POST
	@Path("/crearUsuario")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaDTO crearUsuario(UsuarioDTO usuarioDTO) {
		Usuario usuarioB = usuarioEJB.buscar(usuarioDTO.getUsuario().getCedula());
		if (usuarioB == null) {
			usuarioEJB.crear(usuarioDTO.getUsuario(), usuarioDTO.getUser(), usuarioDTO.getSede());
			return new RespuestaDTO(true, "La entrevista ha sido registrada", "00");
		}
		return new RespuestaDTO(false, "Este usuario ya tiene una entrevista", "-2");
	}

	/**
	 * busca un usuario
	 * 
	 * @param cedula
	 *            la cedula del usuario
	 * @return un dto con el usuario
	 */
	@POST
	@Path("/buscarUsuario")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaDTO buscarUsuario(UsuarioBuscarDTO usuarioBuscarDTO) {
		Usuario usuario = usuarioEJB.buscar(usuarioBuscarDTO.getCedula(), usuarioBuscarDTO.getSede());
		if (usuario != null) {
			return new RespuestaDTO(usuario);
		} else {
			return new RespuestaDTO(null, "No se encontro una entrevista para el usuario con la cedula " + usuarioBuscarDTO.getCedula(),
					"-1");
		}
	}
	
	
	@POST
	@Path("/buscarUsuarios")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaDTO buscarUsuarios(UsuarioBuscarDTO usuarioBuscarDTO) {
		Usuario usuario = usuarioEJB.buscar(usuarioBuscarDTO.getCedula());
		if (usuario != null) {
			return new RespuestaDTO(usuario);
		} else {
			return new RespuestaDTO(null, "No se encontro una entrevista para el usuario con la cedula " + usuarioBuscarDTO.getCedula(),
					"-1");
		}
	}

	/**
	 * busca un usuario
	 * 
	 * @param cedula
	 *            la cedula del usuario
	 * @return un dto con el usuario
	 */
	@POST
	@Path("/buscarUsuarioFicha")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public RespuestaDTO buscarUsuFicha(@FormParam(value = "cedula") String cedula) {
		Usuario usuario = usuarioEJB.buscarUsuFicha(cedula);
		if (usuario != null) {
			return new RespuestaDTO(usuario);
		} else {
			return new RespuestaDTO(null, "No se encontro una entrevista para el usuario con la cedula " + cedula,
					"-1");
		}
	}

	/**
	 * edita un usuario
	 * 
	 * @param usuario
	 *            el usuario a editar
	 * @return dto informando si se pudo editar o no
	 */
	@POST
	@Path("/editarUsuario")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaDTO editarUsuario(UsuarioDTO usuarioDTO) {
		Usuario usuarioB = usuarioEJB.buscar(usuarioDTO.getUsuario().getCedula(),usuarioDTO.getSede());
		if (usuarioB != null) {
			usuarioEJB.editar(usuarioDTO.getUsuario(),usuarioDTO.getUser());
			return new RespuestaDTO(true, "La entrevista ha sido editada", "00");
		}
		return new RespuestaDTO(false,
				"No se encontro una entrevista para el usuario con la cedula " + usuarioDTO.getUsuario().getCedula(), "-2");
	}

	

}
