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

import co.org.smart.corazonvaliente.ejb.IngresoEJB;
import co.org.smart.corazonvaliente.entidades.Egreso;
import co.org.smart.corazonvaliente.entidades.Ingreso;
import co.org.smart.corazonvaliente.seguridad.Secured;
import co.org.smart.corazonvaliente.web.util.IngresoDTO;
import co.org.smart.corazonvaliente.web.util.RespuestaDTO;

@Secured
@Path("/ingreso")
public class IngresoRest {

	@EJB
	private IngresoEJB ingresoEJB;
	
	
	@Path("/crear")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaDTO crearIngreso(IngresoDTO dto) {

		if (dto != null) {
			ingresoEJB.crear(dto.getFuncionario() , dto.getSede(), dto.getIngreso().getFechaIngreso(), dto.getIngreso().getValor(), dto.getIngreso().getDescripcion());
			return new RespuestaDTO(true, "se registro correctamente", "00");

		}
		return new RespuestaDTO(false, "error al registrar el ingreso", "-1");
	}
	
	
	@Path("/buscar")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public RespuestaDTO buscar(@FormParam(value = "codigo") int codigo) {
		Ingreso ingreso = ingresoEJB.buscar(codigo);
		if (ingreso != null) {
			return new RespuestaDTO(ingreso);
		} else {
			return new RespuestaDTO(null, "este ingreso no existe", "-2");
		}
	}

	
	@Path("/eliminar")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public RespuestaDTO eliminar(@FormParam(value = "codigo") int codigo) {
		Ingreso ing = ingresoEJB.buscar(codigo);
		if(ing != null){
			ingresoEJB.eliminarI(ing);
			return new RespuestaDTO(true, "se elimino correctamente", "00");

	
		} else {
			return new RespuestaDTO(false, "error al eliminar", "-2");
		}
	}
	
	
	
	@Path("/editar")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaDTO editar(IngresoDTO dto) {
		if (ingresoEJB.editarI(dto.getIngreso().getCodigo(),dto.getFuncionario(), dto.getSede(), dto.getIngreso().getFechaIngreso(), dto.getIngreso().getValor(),dto.getIngreso().getDescripcion())) {
			return new RespuestaDTO(true, "se edito correctamente", "00");
		} else {
			return new RespuestaDTO(false, "error al editar", "-2");
		}
		

	}
	
	
	@Path("/listarIngresos")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public RespuestaDTO listarIngresos(@FormParam(value = "ano") int ano, @FormParam(value = "mes") int mes,  @FormParam(value = "sede") String sede) {
		List<Ingreso> lista = ingresoEJB.listarIngresos(ano, mes, sede);
		if (!lista.isEmpty()) {
			return new RespuestaDTO(lista);
		} else {
			return new RespuestaDTO(false, "No hay ingresos registrados", "-2");
		}
	}
	
	
	@Path("/listarTodosIngresos")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public RespuestaDTO listarTodosIngresos(@FormParam(value = "sede") String sede) {
		List<Ingreso> lista = ingresoEJB.listarTodosIngresos(sede);
		if (lista != null) {
			return new RespuestaDTO(lista);
		} else {
			return new RespuestaDTO(false, "No hay ingresos registrados", "-2");
		}
	}

}
