package co.org.smart.corazonvaliente.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.org.smart.corazonvaliente.entidades.Cargo;
import co.org.smart.corazonvaliente.entidades.Egreso;
import co.org.smart.corazonvaliente.entidades.Funcionario;
import co.org.smart.corazonvaliente.entidades.Ingreso;
import co.org.smart.corazonvaliente.entidades.Sede;
import co.org.smart.corazonvaliente.implementacion.EJBGenerico;

@LocalBean
@Stateless
public class IngresoEJB extends EJBGenerico<Ingreso> {

	@Override
	public Class getClase() {
		return Ingreso.class;
	}

	@EJB
	private FuncionarioEJB funcionarioEJB;

	public void crear(String funcionario, Sede sede, Date fechaIngreso, double valor, String descripcion) {

		Funcionario f = funcionarioEJB.buscarFuncionarioPorUsuario(funcionario);
		Ingreso in = new Ingreso(f, sede, fechaIngreso, valor, descripcion);
		dao.crear(in);
	}

	public Ingreso buscar(int codigo) {
		return dao.buscar(codigo);
	}

	public boolean editarI(int codigo, String funcionario, Sede sede, Date fechaIngreso, double valor, String descripcion) {
		Funcionario f =	funcionarioEJB.buscarFuncionarioPorUsuario(funcionario);

		
		Ingreso in = buscar(codigo);
		if (in != null) {
			Ingreso ingreso = new Ingreso(codigo, f, sede, fechaIngreso, valor, descripcion);
			dao.editar(ingreso);
			return true;

		} else {
			return false;
		}
	}

	public void eliminarI(Ingreso ingreso) {
		dao.eliminar(ingreso);

	}

	public List<Ingreso> listarIngresos(int ano, int mes, String sede) {
		String cadena = "";
		if (mes < 10) {
			cadena = ano + "-0" + mes;
		} else {
			cadena = ano + "-" + mes;
		}
		List<Ingreso> e = dao.ejecutarNamedQuery(Ingreso.LISTAR_INGRESOS, sede, cadena);
		return e;
	}
	
	
	public List<Ingreso> listarTodosIngresos(String sede) {
		List<Ingreso> e = dao.ejecutarNamedQuery(Ingreso.LISTAR_TODOS_INGRESOS, sede);
		if(e.isEmpty()){
			return null;
		}
		return e;
	}


}
