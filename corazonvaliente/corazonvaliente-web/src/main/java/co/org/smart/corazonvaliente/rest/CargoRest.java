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

import co.org.smart.corazonvaliente.ejb.CargoEJB;
import co.org.smart.corazonvaliente.entidades.Cargo;
import co.org.smart.corazonvaliente.entidades.Sede;
import co.org.smart.corazonvaliente.seguridad.Secured;
import co.org.smart.corazonvaliente.web.util.RespuestaDTO;

@Secured
@Path("/cargo")
public class CargoRest {

	@EJB
	private CargoEJB cargoEJB;
	
	
	@Path("/crear")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaDTO crearCargo(Cargo cargo) {
		if (cargoEJB.crearCargo(cargo)) {
			return new RespuestaDTO(true, "se registro correctamente", "00");
		} else {
			return new RespuestaDTO(false, "el cargo ya existe", "-2");
		}
	}
	
	
	@Path("/buscar")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public RespuestaDTO buscar(@FormParam(value = "nombre") String nombre) {
		Cargo cargo = cargoEJB.buscar(nombre);
		if (cargo != null) {
			return new RespuestaDTO(cargo);
		} else {
			return new RespuestaDTO(null, "este cargo no existe", "-2");
		}
	}

	
	@Path("/eliminar")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaDTO eliminar(Cargo cargo) {
		if (cargoEJB.eliminarC(cargo)) {
			return new RespuestaDTO(true, "se elimino correctamente", "00");
		} else {
			return new RespuestaDTO(false, "error al eliminar", "-2");
		}
	}
	
	
	
	@Path("/editar")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaDTO editar(Cargo cargo) {
		if (cargoEJB.editarC(cargo)) {
			return new RespuestaDTO(true, "se edito correctamente", "00");
		} else {
			return new RespuestaDTO(false, "error al editar", "-2");
		}
	}
	
	
	@Path("/listarCargos")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO listarCargos() {
		List<Cargo> lista = cargoEJB.listarCargos();
		if (!lista.isEmpty()) {
			return new RespuestaDTO(lista);
		} else {
			return new RespuestaDTO(false, "No hay cargos registrados", "-2");
		}
	}
	

}
