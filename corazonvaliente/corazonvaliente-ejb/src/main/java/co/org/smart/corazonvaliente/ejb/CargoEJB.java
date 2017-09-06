package co.org.smart.corazonvaliente.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.org.smart.corazonvaliente.entidades.Cargo;
import co.org.smart.corazonvaliente.implementacion.EJBGenerico;

@LocalBean
@Stateless
public class CargoEJB extends EJBGenerico<Cargo> {

	@Override
	public Class getClase() {
		return Cargo.class;
	}

	public boolean crearCargo(Cargo cargo) {
		Cargo car = buscar(cargo.getNombre());

		if (car == null) {
			dao.crear(cargo);
			return true;

		} else {
			return false;
		}
	}

	public Cargo buscar(String nombre) {
		Cargo cargo = dao.buscar(nombre);
		if (cargo != null) {
			return cargo;
		} else {
			return null;
		}
	}
	
	public boolean editarC(Cargo cargo) {
		Cargo car = buscar(cargo.getNombre());
		if (car != null) {
			dao.editar(cargo);
			return true;

		} else {
			return false;
		}
	}
	
	
	public boolean eliminarC(Cargo cargo) {
		Cargo car = buscar(cargo.getNombre());
		if (car != null) {
			dao.eliminar(car);
			return true;

		} else {
			return false;
		}
	}
	
	
	public List<Cargo> listarCargos() {
		return dao.ejecutarNamedQuery(Cargo.LISTAR_CARGOS);
	}

}
