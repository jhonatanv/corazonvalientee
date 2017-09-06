package co.org.smart.corazonvaliente.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.org.smart.corazonvaliente.entidades.Egreso;
import co.org.smart.corazonvaliente.entidades.Funcionario;
import co.org.smart.corazonvaliente.entidades.Ingreso;
import co.org.smart.corazonvaliente.entidades.Sede;
import co.org.smart.corazonvaliente.implementacion.EJBGenerico;

@LocalBean
@Stateless
public class EgresoEJB extends EJBGenerico<Egreso> {

	@Override
	public Class getClase() {
		return  Egreso.class;
	}

	@EJB
	FuncionarioEJB funcionarioEJB;
	
	public void crear(String funcionario, Sede sede, String numeroFactura, Date fechaEgreso, String cuenta,
			String nit, String concepto, double valor) {
		
	Funcionario f =	funcionarioEJB.buscarFuncionarioPorUsuario(funcionario);
		
		Egreso e = new Egreso(f, sede, numeroFactura, fechaEgreso, cuenta, nit, concepto, valor);
			dao.crear(e);
	}

	public Egreso buscar(int codigo) {
			return  dao.buscar(codigo);
	}
	
	
	public boolean editarE(int codigo, String funcionario, Sede sede, String numeroFactura, Date fechaEgreso, String cuenta,
			String nit, String concepto, double valor) {
		
		Funcionario f =	funcionarioEJB.buscarFuncionarioPorUsuario(funcionario);
		
		Egreso in = buscar(codigo);
		if (in != null) {
			Egreso egreso = new Egreso(codigo, f, sede, numeroFactura, fechaEgreso, cuenta, nit, concepto, valor);
			dao.editar(egreso);
			return true;

		} else {
			return false;
		}
	}
	
	
	public void eliminarE(Egreso egreso) {
			dao.eliminar(egreso);
			
	}
	
	
	public List<Egreso> listarEgresos(int ano, int mes, String sede) {
		String cadena = "";
		if(mes<10){
			cadena = ano+"-0"+mes;
		}else{
			cadena = ano+"-"+mes;
		}
		List<Egreso> e = dao.ejecutarNamedQuery(Egreso.LISTAR_EGRESOS,sede,cadena );
		 return e;
	}
	
	public List<Egreso> listarTodosEgresos(String sede) {
		List<Egreso> e = dao.ejecutarNamedQuery(Egreso.LISTAR_TODOS_EGRESOS, sede);
		if(e.isEmpty()){
			return null;
		}
		return e;
	}

}
