package co.org.smart.corazonvaliente.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.org.smart.corazonvaliente.entidades.Funcionario;
import co.org.smart.corazonvaliente.entidades.FuncionarioSede;
import co.org.smart.corazonvaliente.entidades.Sede;
import co.org.smart.corazonvaliente.implementacion.EJBGenerico;

@LocalBean
@Stateless
public class FuncionarioSedeEJB extends EJBGenerico<FuncionarioSede> {

	@EJB
	private FuncionarioEJB funcionarioEJB;
	

	@Override
	public Class getClase() {
		return FuncionarioSede.class;
	}

	public void crearFuncionarioSede(FuncionarioSede funcionarioSede) {
		dao.crear(funcionarioSede);
	}

	public int guardarInfo(Funcionario funcionario) {
		if (funcionario.getCargo().getNombre().contains("presidente") || funcionario.getCargo().getNombre().contains("nacional") ) {
			if(funcionario.isEstado())	{
				return 1;	
			}
			
		} else {
			List<FuncionarioSede> funcionarioSede = dao.ejecutarNamedQuery(FuncionarioSede.BUSCARFUNCIONARIOACTIVO,
					funcionario.getCedula());
			if (!funcionarioSede.isEmpty()) {
				return 2;
			}
		}
		return 0;
	}


	public boolean desactivarFuncionarioSede(String cedula) {
		FuncionarioSede fun = buscarFuncionarioSede(cedula);

		if(fun!=null){
			fun.setEstado(false);
			dao.editar(fun);
			return true;
		}else{
			return false;
		}
	}

	/**
	 * busca un funcionario activo por cedula
	 * 
	 * @param cedula
	 *            la cedula del funcionario
	 * @return el funcionario si lo encuentra
	 */
	public FuncionarioSede buscarFuncionarioSede(String cedula) {
		List<FuncionarioSede> funcionarioSede = dao.ejecutarNamedQuery(FuncionarioSede.BUSCARFUNCIONARIOACTIVO, cedula);
		if (funcionarioSede.isEmpty()) {
			return null;
		}
		return funcionarioSede.get(0);
	}
	
	public List<FuncionarioSede> listarFuncionarios(String sede) {
		List<FuncionarioSede> funcionarioSede = dao.ejecutarNamedQuery(FuncionarioSede.LISTARFUNCIONARIOSACTIVOS, sede);
		
		if (funcionarioSede.isEmpty()) {
			return null;
		}
		return funcionarioSede;
	}

}
