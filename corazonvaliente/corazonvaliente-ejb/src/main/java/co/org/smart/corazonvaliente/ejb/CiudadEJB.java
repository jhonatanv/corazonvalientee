package co.org.smart.corazonvaliente.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.org.smart.corazonvaliente.entidades.Ciudad;
import co.org.smart.corazonvaliente.entidades.Usuario;
import co.org.smart.corazonvaliente.implementacion.EJBGenerico;

@LocalBean
@Stateless
public class CiudadEJB extends EJBGenerico<Ciudad>{

	@Override
	public Class getClase() {
		return Ciudad.class;
	}

	public List<Ciudad> listarCiudades() {
		return dao.ejecutarNamedQuery(Ciudad.LISTAR_CIUDADES);
	}

}
