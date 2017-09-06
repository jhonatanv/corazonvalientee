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
import co.org.smart.corazonvaliente.ejb.EgresoEJB;
import co.org.smart.corazonvaliente.ejb.IngresoEJB;
import co.org.smart.corazonvaliente.entidades.Cargo;
import co.org.smart.corazonvaliente.entidades.Egreso;
import co.org.smart.corazonvaliente.entidades.Ingreso;
import co.org.smart.corazonvaliente.entidades.Sede;
import co.org.smart.corazonvaliente.seguridad.Secured;
import co.org.smart.corazonvaliente.web.util.EgresoDTO;
import co.org.smart.corazonvaliente.web.util.RespuestaDTO;

@Secured
@Path("/egreso")
public class EgresoRest {

	@EJB
	private EgresoEJB egresoEJB;
	
	
	@Path("/crear")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaDTO crearIngreso(EgresoDTO dto) {
		
		if (dto != null) {
			egresoEJB.crear(dto.getFuncionario(), dto.getSede(), dto.getEgreso().getNumeroFactura(), dto.getEgreso().getFechaEgreso(), dto.getEgreso().getCuenta(),
					dto.getEgreso().getNit(), dto.getEgreso().getConcepto(), dto.getEgreso().getValor());
			return new RespuestaDTO(true, "se registro correctamente", "00");
		}
		return new RespuestaDTO(false, "error al registrar el egreso", "-1");
	}
	
	
	@Path("/buscar")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public RespuestaDTO buscar(@FormParam(value = "codigo") int codigo) {
		Egreso egreso = egresoEJB.buscar(codigo);
		if (egreso != null) {
			return new RespuestaDTO(egreso);
		} else {
			return new RespuestaDTO(null, "este egreso no existe", "-2");
		}
	}

	
	@Path("/eliminar")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public RespuestaDTO eliminar(@FormParam(value = "codigo") int codigo) {
		Egreso ing = egresoEJB.buscar(codigo);
		if(ing != null){
			egresoEJB.eliminarE(ing);
			return new RespuestaDTO(true, "se elimino correctamente", "00");

	
		} else {
			return new RespuestaDTO(false, "error al eliminar", "-2");
		}
	}
	
	
	
	@Path("/editar")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaDTO editar(EgresoDTO dto) {
		
		
		if (egresoEJB.editarE(dto.getEgreso().getCodigo(), dto.getFuncionario(), dto.getSede(), dto.getEgreso().getNumeroFactura(), dto.getEgreso().getFechaEgreso(), dto.getEgreso().getCuenta(),
				dto.getEgreso().getNit(), dto.getEgreso().getConcepto(), dto.getEgreso().getValor())) {
			return new RespuestaDTO(true, "se edito correctamente", "00");
		} else {
			return new RespuestaDTO(false, "error al editar", "-2");
		}
	}
	
	
	@Path("/listarEgresos")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public RespuestaDTO listarEgresos(@FormParam(value = "ano") int ano, @FormParam(value = "mes") int mes, @FormParam(value = "sede") String sede) {
		List<Egreso> lista = egresoEJB.listarEgresos(ano, mes, sede);
		if (!lista.isEmpty()) {
			return new RespuestaDTO(lista);
		} else {
			return new RespuestaDTO(false, "No hay egresos registrados", "-2");
		}
	}
	

	@Path("/listarTodosEgresos")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public RespuestaDTO listarTodosEgresos( @FormParam(value = "sede") String sede) {
		List<Egreso> lista = egresoEJB.listarTodosEgresos(sede);
		if (lista != null) {
			return new RespuestaDTO(lista);
		} else {
			return new RespuestaDTO(false, "No hay egresos registrados", "-2");
		}
	}
	

}
