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
import co.org.smart.corazonvaliente.ejb.FuncionarioEJB;
import co.org.smart.corazonvaliente.ejb.FuncionarioSedeEJB;
import co.org.smart.corazonvaliente.ejb.SedeEJB;
import co.org.smart.corazonvaliente.entidades.Funcionario;
import co.org.smart.corazonvaliente.entidades.FuncionarioSede;
import co.org.smart.corazonvaliente.entidades.Sede;
import co.org.smart.corazonvaliente.seguridad.Secured;
import co.org.smart.corazonvaliente.web.util.RespuestaDTO;

@Secured
@Path("/funcionariosede")
public class FuncionarioSedeRest {

	@EJB
	private FuncionarioSedeEJB funconarioSedeEJB;
	
	@EJB
	private FuncionarioEJB funconarioEJB;
	
	
	

	
	
	/**
	 * busca un funcionario
	 * 
	 * @param cedula
	 *            la cedula del funcionario
	 * @return un dto con el funcionario
	 */
	@POST
	@Path("/buscarfuncionario")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public RespuestaDTO buscarFuncionario(@FormParam(value = "cedula") String cedula) {
		FuncionarioSede funcionario = funconarioSedeEJB.buscarFuncionarioSede(cedula);
		if (funcionario != null) {
			return new RespuestaDTO(funcionario);
		} else {
			return new RespuestaDTO(null, "No se encontro un funcionario con la cedula " + cedula,
					"-2");
		}
	}
	
	
	@POST
	@Path("/trasladarFuncionario")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaDTO trasladarFuncionario(FuncionarioDTO dto) {
		Funcionario a = funconarioEJB.buscar(dto.getCedula());
		if(funconarioSedeEJB.desactivarFuncionarioSede(dto.getCedula())){
						
			Funcionario f = new Funcionario(dto.getCedula(), dto.getCargo(), dto.getNombre(), dto.getTelefono(), dto.getFechaIngreso(), dto.getTipoContrato(), dto.getEps(), dto.getSalario(), dto.getUsuario(), dto.getContrasenia());
			FuncionarioSede fu = new FuncionarioSede(dto.getSede(), f, true);
			funconarioSedeEJB.crearFuncionarioSede(fu);
			return new RespuestaDTO(true, "se traslado correctamente", "00");
		} else if(a !=null){
			FuncionarioSede fa = new FuncionarioSede(dto.getSede(), a, true);
			funconarioSedeEJB.crearFuncionarioSede(fa);
			return new RespuestaDTO(true, "Funcionario activado, se traslado correctamente ",
					"00");
		}else{
			return new RespuestaDTO(false, "Este funcionario no existe",
					"-2");
		}
	}
	
	
	@POST
	@Path("/listarFuncionarios")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaDTO listarFuncionarios(Sede sede) {
		List<FuncionarioSede> funcionarios = funconarioSedeEJB.listarFuncionarios(sede.getNombre());
		
		if (funcionarios != null) {
			return new RespuestaDTO(funcionarios);
		} else {
			return new RespuestaDTO(null, "No hay funcionarios registrados en esta sede","-2");
		}
	}
}
