package co.org.smart.corazonvaliente.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.org.smart.corazonvaliente.ejb.FuncionarioEJB;
import co.org.smart.corazonvaliente.ejb.FuncionarioSedeEJB;
import co.org.smart.corazonvaliente.entidades.Acceso;
import co.org.smart.corazonvaliente.entidades.Funcionario;
import co.org.smart.corazonvaliente.entidades.Sede;
import co.org.smart.corazonvaliente.seguridad.Secured;
import co.org.smart.corazonvaliente.web.util.LoginRespuestaDTO;
import co.org.smart.corazonvaliente.web.util.RespuestaDTO;

@Path("/login")
public class LoginRest {

	@EJB
	private FuncionarioEJB funcionarioEJB;

	@EJB
	private FuncionarioSedeEJB funcionarioSedeEJB;

	/**
	 * Map de tokens
	 */
	public static Map<String, Object> tokens = new HashMap<>();

	/**
	 * servicio rest para verificar usuario
	 * 
	 * @param usuario
	 *            el usuario
	 * @param contrasenia
	 *            la contrasenia del usuairo
	 * @return dto con mensaje de error u objeto
	 */
	@POST
	@Path("/verificar")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO login(@FormParam(value = "usuario") String usuario,
			@FormParam(value = "contrasenia") String contrasenia) {
		Funcionario f = funcionarioEJB.buscarFuncionarioUsuario(usuario, contrasenia);
		int resp = -1;
		if (f != null) {
			resp = funcionarioSedeEJB.guardarInfo(f);
			if (resp == 1) {
				String token = UUID.randomUUID().toString();
				tokens.put(token, f.getUsuario());

				LoginRespuestaDTO login = new LoginRespuestaDTO(f.getUsuario(), token, null);
				return new RespuestaDTO(login, resp + "", "00");

			} else if (resp == 2) {
				Sede sede = funcionarioSedeEJB.buscarFuncionarioSede(f.getCedula()).getSede();
				String token = UUID.randomUUID().toString();
				tokens.put(token, f.getUsuario());

				LoginRespuestaDTO login = new LoginRespuestaDTO(f.getUsuario(), token, sede);
				return new RespuestaDTO(login, resp + "", "00");

			} else {
				return new RespuestaDTO(false, resp + "", "00");
			}
		}
		return new RespuestaDTO(resp);
	}

	@Secured
	@POST
	@Path("/accesos")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public RespuestaDTO login(@FormParam(value = "usuario") String usuario) {
		List<Acceso> accesos = funcionarioEJB.listarAccesosRol(usuario);
		if (accesos != null) {
			return new RespuestaDTO(accesos);
		} else {
			return new RespuestaDTO(null, "error al cargar accesos", "-1");
		}
	}

	/**
	 * cierra la sesion del usuario
	 * 
	 * @return un dto con una direccion a el login
	 */
	@Path("/cerrarSesion")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public RespuestaDTO logOut(@FormParam(value = "token") String token) {
		
		tokens.remove(token);
					
		return new RespuestaDTO("/corazonvaliente-web/index.html#/");
	}

}