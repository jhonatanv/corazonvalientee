package co.org.smart.corazonvaliente.ejb;

import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.org.smart.corazonvaliente.entidades.AporteUsuario;
import co.org.smart.corazonvaliente.entidades.Ficha;
import co.org.smart.corazonvaliente.entidades.FuncionarioSede;
import co.org.smart.corazonvaliente.entidades.Ingreso;
import co.org.smart.corazonvaliente.entidades.Sede;
import co.org.smart.corazonvaliente.entidades.Usuario;
import co.org.smart.corazonvaliente.implementacion.EJBGenerico;

@Stateless
@LocalBean
public class AporteEJB extends EJBGenerico<AporteUsuario> {

	@EJB
	private FichaEJB fichaEJB;

	@EJB
	private IngresoEJB ingresoEJB;

	@EJB
	private UsuarioEJB usuarioEJB;

	@Override
	public Class getClase() {
		// TODO Auto-generated method stub
		return AporteUsuario.class;
	}

	public void crear(AporteUsuario aporteUsuario) {
		dao.crear(aporteUsuario);
	}

	public void eliminar(AporteUsuario aporteUsuario) {
		dao.eliminar(aporteUsuario);
	}

	public List<AporteUsuario> listarAportes(String cedula, String sede) {
		return dao.ejecutarNamedQuery(AporteUsuario.LISTAR_APORTES, cedula, sede);
		
	}

	/**
	 * valida el nuevo aporte de un usuario
	 * 
	 * @param aporteUsuario
	 *            el nuevo aporte de un usuario
	 * @return 1 si se pudo hacer el aporte, 0 si el aporte el mayor a lo que se
	 *         debe, -1 si el usuario no esta activo
	 */
	public int validarAporteCrear(AporteUsuario aporteUsuario, Sede sede) {
		if (usuarioEJB.buscar(aporteUsuario.getUsuario().getCedula(), sede) == null) {
			return -3;
		} else {
			List<Ficha> fichas = fichaEJB.listarActivos(aporteUsuario.getUsuario().getCedula());
			if (!fichas.isEmpty()) {

				double total = 0;
				List<AporteUsuario> aportes = listarAportes(aporteUsuario.getUsuario().getCedula(), sede.getNombre());

				for (int i = 0; i < aportes.size(); i++) {
					total += aportes.get(i).getValorCancelado();
				}
				if ((total + aporteUsuario.getValorCancelado()) <= aporteUsuario.getUsuario().getValor()) {
					aporteUsuario.setSede(sede);
					aporteUsuario.setFechaAporte(Calendar.getInstance().getTime());
					crear(aporteUsuario);
					Ingreso ingreso = new Ingreso();
					ingreso.setDescripcion("Aporte del usuario con cedula " + aporteUsuario.getUsuario().getCedula());
					ingreso.setFechaIngreso(aporteUsuario.getFechaAporte());
					ingreso.setValor(aporteUsuario.getValorCancelado());
					ingresoEJB.crear(ingreso);
					return 1;
				} else {
					return 0;
				}
			}
		}
		return -1;
	}

}
