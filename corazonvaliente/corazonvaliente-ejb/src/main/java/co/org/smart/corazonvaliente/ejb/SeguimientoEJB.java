package co.org.smart.corazonvaliente.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.org.smart.corazonvaliente.entidades.Ficha;
import co.org.smart.corazonvaliente.entidades.Funcionario;
import co.org.smart.corazonvaliente.entidades.Sede;
import co.org.smart.corazonvaliente.entidades.Seguimiento;
import co.org.smart.corazonvaliente.entidades.Usuario;
import co.org.smart.corazonvaliente.implementacion.EJBGenerico;

@Stateless
@LocalBean
public class SeguimientoEJB extends EJBGenerico<Seguimiento>{

	@Override
	public Class getClase() {
		return Seguimiento.class;
	}
	
	@EJB
	FuncionarioEJB funcionarioEJB;

	
	public void crear(String use, Sede sede, Usuario usuario, Date fecha, String observacion){
		Funcionario funcionario = funcionarioEJB.buscarFuncionarioPorUsuario(use);
		Seguimiento seguimiento = new Seguimiento(funcionario, sede, usuario, fecha, observacion);
		dao.crear(seguimiento);
	}
	
	public List<Seguimiento> listarSeguimientos(String cedula){
		return dao.ejecutarNamedQuery(Seguimiento.LISTAR_SEGUIMIENTOS, cedula);
	}
	
}
