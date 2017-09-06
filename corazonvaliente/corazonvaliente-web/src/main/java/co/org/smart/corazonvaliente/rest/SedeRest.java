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

import co.org.smart.corazonvaliente.ejb.CiudadEJB;
import co.org.smart.corazonvaliente.ejb.SedeEJB;
import co.org.smart.corazonvaliente.entidades.Ciudad;
import co.org.smart.corazonvaliente.entidades.Sede;
import co.org.smart.corazonvaliente.seguridad.Secured;
import co.org.smart.corazonvaliente.web.util.RespuestaDTO;

@Secured
@Path("/sede")
public class SedeRest {

	@EJB
	private SedeEJB sedeEJB;

	@EJB
	private CiudadEJB ciudadEJB;
	
	
	@Path("/crear")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaDTO crearCargo(Sede sede) {
		if (sedeEJB.crearSede(sede)) {
			return new RespuestaDTO(true, "se registro correctamente", "00");
		} else {
			return new RespuestaDTO(false, "la sede ya existe", "-2");
		}
	}
	
	
	@Path("/buscar")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public RespuestaDTO buscar(@FormParam(value = "nombre") String nombre) {
		Sede sede = sedeEJB.buscar(nombre);
		if (sede != null) {
			return new RespuestaDTO(sede);
		} else {
			return new RespuestaDTO(null, "esta sede no existe", "-2");
		}
	}

	
	@Path("/eliminar")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaDTO eliminar(Sede sede) {
		if (sedeEJB.eliminarS(sede)) {
			return new RespuestaDTO(true, "se elimino correctamente", "00");
		} else {
			return new RespuestaDTO(false, "error al eliminar", "-2");
		}
	}
	
	
	
	@Path("/editar")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaDTO editar(Sede sede) {
		if (sedeEJB.editarS(sede)) {
			return new RespuestaDTO(true, "se edito correctamente", "00");
		} else {
			return new RespuestaDTO(false, "error al editar", "-2");
		}
	}
	
	
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
	
	@Path("/listarSedes")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO listarSedes() {
		List<Sede> lista = sedeEJB.listarSedes();
		if (!lista.isEmpty()) {
			return new RespuestaDTO(lista);
		} else {
			return new RespuestaDTO(false, "No hay sedes registradas", "-2");
		}
	}
}
