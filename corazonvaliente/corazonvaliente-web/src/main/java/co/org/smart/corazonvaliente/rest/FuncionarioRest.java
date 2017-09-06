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

import co.org.smart.corazonvaliente.dto.FuncionarioDTO;
import co.org.smart.corazonvaliente.ejb.CargoEJB;
import co.org.smart.corazonvaliente.ejb.FuncionarioEJB;
import co.org.smart.corazonvaliente.entidades.Cargo;
import co.org.smart.corazonvaliente.entidades.Funcionario;
import co.org.smart.corazonvaliente.seguridad.Secured;
import co.org.smart.corazonvaliente.web.util.RespuestaDTO;

@Secured
@Path("/funcionario")
public class FuncionarioRest {

	@EJB
	private FuncionarioEJB funcionarioEJB;
	
	
	@Path("/crear")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaDTO crearCargo(FuncionarioDTO dto) {
		int resp = funcionarioEJB.crearFuncionario(dto);
		if (resp == 1) {
			return new RespuestaDTO(true, "se registro correctamente", "00");
		} else if (resp == 2)  {
			return new RespuestaDTO(false, "Ya existe un funcionario con este usuario", "-2");
		}else{
			return new RespuestaDTO(false, "Este funcionario ya se encuentra registrado", "-3");

		}
	}
	
	
	@Path("/buscar")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public RespuestaDTO buscar(@FormParam(value = "nombre") String cedula) {
		FuncionarioDTO funcionario = funcionarioEJB.buscarFuncionarioSede(cedula);
		if (funcionario != null) {
			return new RespuestaDTO(funcionario);
		} else {
			return new RespuestaDTO(null, "este funcionario no existe", "-2");
		}
	}
	
	@Path("/buscarF")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public RespuestaDTO buscarF(@FormParam(value = "nombre") String cedula) {
		Funcionario funcionario = funcionarioEJB.buscar(cedula);
		FuncionarioDTO funcionario2 = funcionarioEJB.buscarFuncionarioSede(cedula);
		
		if (funcionario != null) {
			if(funcionario2 != null){
				return new RespuestaDTO(funcionario2,"0","00");
			}else{
				return new RespuestaDTO(funcionario,"1","00");

			}
		} else {
			return new RespuestaDTO(null, "este funcionario no existe", "-2");
		}
	}

	
	@Path("/eliminar")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public RespuestaDTO eliminar(@FormParam(value = "cedula") String cedula) {
		if (funcionarioEJB.eliminarC(cedula)) {
			return new RespuestaDTO(true, "se elimino correctamente", "00");
		} else {
			return new RespuestaDTO(false, "error al eliminar, este funcionario no esta activo o registrado", "-2");
		}
	}
	
	
	
	@Path("/editar")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaDTO editar(Funcionario funcionario) {
		
		int resp = funcionarioEJB.editarC(funcionario);
		if (resp==1) {
			return new RespuestaDTO(true, "se edito correctamente", "00");
		} else if(resp==2){
			return new RespuestaDTO(false, "error al editar, este usuario ya existe", "-2");
		}else{
			return new RespuestaDTO(false, "error al editar, no existe este funcionario con esta cedula", "-3");

		}
	}
	
	
	@Path("/listarFuncionariosEjecutivos")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO listarFuncionariosEjecutivos() {
		List<Funcionario> lista = funcionarioEJB.listarFuncionariosEjecutivos();
		if (lista != null) {
			return new RespuestaDTO(lista);
		} else {
			return new RespuestaDTO(false, "No hay funcionarios ejecutivos registrados", "-2");
		}
	}
	

}
