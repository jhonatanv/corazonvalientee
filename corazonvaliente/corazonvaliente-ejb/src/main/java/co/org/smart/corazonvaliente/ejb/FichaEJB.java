package co.org.smart.corazonvaliente.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.org.smart.corazonvaliente.entidades.AporteUsuario;
import co.org.smart.corazonvaliente.entidades.Ficha;
import co.org.smart.corazonvaliente.entidades.Sede;
import co.org.smart.corazonvaliente.entidades.Usuario;
import co.org.smart.corazonvaliente.implementacion.EJBGenerico;

@Stateless
@LocalBean
public class FichaEJB extends EJBGenerico<Ficha> {

	@EJB
	private AporteEJB aporteEJB;

	@Override
	public Class getClase() {
		return Ficha.class;
	}

	public void crear(Ficha ficha) {
		dao.crear(ficha);
	}

	public Ficha buscar(Object pk) {
		return dao.buscar(pk);
	}

	public boolean editarFicha(Ficha ficha, Sede sede) {
		Ficha fichaB = buscar(ficha.getCodigo());
		if (fichaB.getSede().getNombre().equals(sede.getNombre())) {
			if (fichaB.isEstadoUsuario()) {
				List<AporteUsuario> aportes = aporteEJB.listarAportes(fichaB.getUsuario().getCedula(), sede.getNombre());
				for (int i = 0; i < aportes.size(); i++) {
					aporteEJB.eliminar(aportes.get(i));
				}
			}
			fichaB.setEstadoUsuario(false);
			fichaB.setObservacion(ficha.getObservacion());
			fichaB.setFechaSalida(ficha.getFechaSalida());
			dao.editar(fichaB);
			return true;
		}
		return false;
	}

	public void editar(Ficha ficha) {
		dao.editar(ficha);
	}

	public List<Ficha> listarFichas(String cedula) {
		return dao.ejecutarNamedQuery(Ficha.LISTAR_FICHAS, cedula);
	}

	public List<Ficha> listarActivos(String cedula) {
		return dao.ejecutarNamedQuery(Ficha.LISTAR_ACTIVOS, cedula);
	}
	
	public List<Ficha> listarUsuarios(String cedula) {
		return dao.ejecutarNamedQuery(Ficha.LISTAR_USUARIOS, cedula);
	}

	public List<Usuario> listarUsuariosSede(Sede sede) {
		return dao.ejecutarNamedQuery(Ficha.LISTAR_USUARIOS_SEDE, sede);
	}

}
