package co.org.smart.corazonvaliente.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.org.smart.corazonvaliente.dto.FuncionarioDTO;
import co.org.smart.corazonvaliente.entidades.Acceso;
import co.org.smart.corazonvaliente.entidades.Funcionario;
import co.org.smart.corazonvaliente.entidades.FuncionarioSede;
import co.org.smart.corazonvaliente.entidades.Sede;
import co.org.smart.corazonvaliente.implementacion.EJBGenerico;

@LocalBean
@Stateless
public class FuncionarioEJB extends EJBGenerico<Funcionario> {

	@Override
	public Class getClase() {
		return Funcionario.class;
	}

	@EJB
	FuncionarioSedeEJB funcinarioSede;

	public int crearFuncionario(FuncionarioDTO dto) {
		Funcionario f = buscar(dto.getCedula());
		Funcionario fu = buscarFuncionarioPorUsuario(dto.getUsuario());
		
		if (f == null) {
			if(fu==null){
				if (dto.getCargo().getNombre().contains("presidente") || dto.getCargo().getNombre().contains("nacional")) {
					Funcionario funcionario = new Funcionario(dto.getCedula(), dto.getCargo(), dto.getNombre(),
							dto.getTelefono(), dto.getFechaIngreso(), dto.getTipoContrato(), dto.getEps(), dto.getSalario(),
							dto.getUsuario(), dto.getContrasenia(), true);
					dao.crear(funcionario);
					return 1;
				}else{
					Funcionario funcionario = new Funcionario(dto.getCedula(), dto.getCargo(), dto.getNombre(),
							dto.getTelefono(), dto.getFechaIngreso(), dto.getTipoContrato(), dto.getEps(), dto.getSalario(),
							dto.getUsuario(), dto.getContrasenia(), false);
					dao.crear(funcionario);
					FuncionarioSede fun = new FuncionarioSede(dto.getSede(), funcionario, true);
					funcinarioSede.crearFuncionarioSede(fun);
					return 1;
				}
				
			
			}else{
				return 2;
			}
		} else {
			return 3;
		}
	}

	public FuncionarioDTO buscarFuncionarioSede(String cedula) {
		Funcionario fun = dao.buscar(cedula);
		if (fun != null) {

			FuncionarioSede f = funcinarioSede.buscarFuncionarioSede(cedula);
			 if(f!= null){
			FuncionarioDTO dto = new FuncionarioDTO(cedula, fun.getCargo(), fun.getNombre(), fun.getTelefono(),
					fun.getFechaIngreso(), fun.getTipoContrato(), fun.getEps(), fun.getSalario(), fun.getUsuario(),
					fun.getContrasenia(), f.getSede());
			return dto;
			 } else {
			return null;
		}
		}
		return null;
	}

	public Funcionario buscar(String cedula) {
		Funcionario fun = dao.buscar(cedula);
		if (fun != null) {
			return fun;
		} else {
			return null;
		}
	}

	public int editarC(Funcionario funcionario) {
		Funcionario fu = buscar(funcionario.getCedula());
		Funcionario fui = buscarFuncionarioPorUsuario(funcionario.getUsuario());

		if (fu != null) {
			
			if(fui == null || fui.getCedula().equals(fu.getCedula())){
				dao.editar(funcionario);
				return 1;
			}else{
				return 2;
			}
			

		} else {
			return 3;
		}
	}

	public boolean eliminarC(String cedula) {
		boolean resp = funcinarioSede.desactivarFuncionarioSede(cedula);
		Funcionario u = buscar(cedula);
		if (resp) {
			return true;
		} else if(u != null) {
			if(u.isEstado()){
			u.setEstado(false);
			dao.editar(u);
			return true;
			}
		}else{
			return false;
		}
		return false;
	}

	public Funcionario buscarFuncionarioUsuario(String user, String pass) {
		List<Funcionario> lista = dao.ejecutarNamedQuery(Funcionario.BUSCAR_FUNCIONARIO_USER_PASS, user, pass);
		if (!lista.isEmpty()) {
			return lista.get(0);
		} else {
			return null;
		}
	}
	
	
	public Funcionario buscarFuncionarioPorUsuario(String user) {
		List<Funcionario> lista = dao.ejecutarNamedQuery(Funcionario.BUSCAR_FUNCIONARIO_USER, user);
		if (!lista.isEmpty()) {
			return lista.get(0);
		} else {
			return null;
		}
	}

	public List<Acceso> listarAccesosRol(String usuario) {
		Funcionario fu = buscarFuncionarioPorUsuario(usuario);
		if (fu != null) {
			List<Acceso> lista = dao.ejecutarNamedQuery(Acceso.LISTAR_ACCSESOS_POR_ROL,
					fu.getCargo());
			if (!lista.isEmpty()) {
				return lista;
			} else {
				return null;
			}
		}
		return null;
	}
	
	public List<Funcionario> listarFuncionariosEjecutivos() {
		List<Funcionario> lista = dao.ejecutarNamedQuery(Funcionario.BUSCAR_FUNCIONARIOS_EJECUTIVOS);
		if (!lista.isEmpty()) {
			return lista;
		} else {
			return null;
		}
	}

}
