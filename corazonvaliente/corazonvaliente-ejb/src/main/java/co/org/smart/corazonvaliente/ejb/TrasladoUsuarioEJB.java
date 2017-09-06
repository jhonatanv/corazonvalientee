package co.org.smart.corazonvaliente.ejb;

import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.org.smart.corazonvaliente.dto.TrasladoUsuarioDTO;
import co.org.smart.corazonvaliente.entidades.Ficha;

@Stateless
@LocalBean
public class TrasladoUsuarioEJB {

	@EJB
	private FichaEJB fichaEJB;

	@EJB
	private UsuarioEJB usuarioEJB;

	/**
	 * realiza el traslado de un usuario
	 * 
	 * @param trasladoUsuarioDTO
	 *            el usuario y la nueva sede del usuario
	 * @return 1 si se puede realizar el traslado, de lo contrario 0
	 */
	public int realizarTraslado(TrasladoUsuarioDTO trasladoUsuarioDTO) {
		List<Ficha> fichas = fichaEJB.listarActivos(trasladoUsuarioDTO.getUsuario().getCedula());
		List<Ficha> fichasTodas = fichaEJB.listarUsuarios(trasladoUsuarioDTO.getUsuario().getCedula());

		if(!fichasTodas.isEmpty()){
		if (!fichas.isEmpty()) {
			Ficha ficha = new Ficha(trasladoUsuarioDTO.getUsuario(), Calendar.getInstance().getTime(), null, "", true,
					trasladoUsuarioDTO.getSede());
			fichaEJB.crear(ficha);
			trasladoUsuarioDTO.getUsuario().setSede(trasladoUsuarioDTO.getSede());
			usuarioEJB.editarTraslado(trasladoUsuarioDTO.getUsuario());

			fichas.get(0).setObservacion("Traslado de sede");
			fichas.get(0).setFechaSalida(Calendar.getInstance().getTime());
			fichas.get(0).setEstadoUsuario(false);
			fichaEJB.editar(fichas.get(0));

			return 1;
		}else{
			Ficha ficha = new Ficha(trasladoUsuarioDTO.getUsuario(), Calendar.getInstance().getTime(), null, "", true,
					trasladoUsuarioDTO.getSede());
			fichaEJB.crear(ficha);
			trasladoUsuarioDTO.getUsuario().setSede(trasladoUsuarioDTO.getSede());
			usuarioEJB.editarTraslado(trasladoUsuarioDTO.getUsuario());
			return 1;
		}
		}
		return 0;
	}

}
