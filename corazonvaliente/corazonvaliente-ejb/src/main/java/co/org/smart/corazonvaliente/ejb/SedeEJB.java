package co.org.smart.corazonvaliente.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.org.smart.corazonvaliente.entidades.Sede;
import co.org.smart.corazonvaliente.implementacion.EJBGenerico;

@LocalBean
@Stateless
public class SedeEJB extends EJBGenerico<Sede>{

	@Override
	public Class getClase() {
		return Sede.class;
	}

	public boolean crearSede(Sede sede) {
		Sede se = buscar(sede.getNombre());

		if (se == null) {
			dao.crear(sede);
			return true;

		} else {
			return false;
		}
	}

	public Sede buscar(String nombre) {
		Sede sede = dao.buscar(nombre);
		if (sede != null) {
			return sede;
		} else {
			return null;
		}
	}
	
	public boolean editarS(Sede sede) {
		Sede sed = buscar(sede.getNombre());
		if (sed != null) {
			dao.editar(sede);
			return true;

		} else {
			return false;
		}
	}
	
	
	public boolean eliminarS(Sede sede) {
		Sede sed = buscar(sede.getNombre());
		if (sed != null) {
			dao.eliminar(sed);
			return true;

		} else {
			return false;
		}
	}
	
	
	public List<Sede> listarSedes() {
		return dao.ejecutarNamedQuery(Sede.LISTAR_SEDES);
	}
}
