package co.org.smart.corazonvaliente.ejb;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import co.org.smart.corazonvaliente.entidades.Ficha;
import co.org.smart.corazonvaliente.entidades.Funcionario;
import co.org.smart.corazonvaliente.entidades.Sede;
import co.org.smart.corazonvaliente.entidades.Usuario;
import co.org.smart.corazonvaliente.implementacion.EJBGenerico;

@LocalBean
@Stateless
public class UsuarioEJB extends EJBGenerico<Usuario> {

	@EJB
	private FichaEJB fichaEJB;

	@EJB
	private FuncionarioEJB funcionarioEJB;

	@Override
	public Class getClase() {
		return Usuario.class;
	}

	public void crear(Usuario usuario, String user, Sede sede) {
		Funcionario funcionario = funcionarioEJB.buscarFuncionarioPorUsuario(user);
		usuario.setFuncionario(funcionario);
		usuario.setSede(sede);
		dao.crear(usuario);
		Ficha ficha = new Ficha(usuario, usuario.getFechaIngreso(), null, "", true, usuario.getSede());
		fichaEJB.crear(ficha);
	}

	public Usuario buscar(String cedula, Sede sede) {
		Usuario usuario = dao.buscar(cedula);
		if (usuario != null) {
			if (usuario.getSede().getNombre().equals(sede.getNombre())) {
				return usuario;
			}
		}
		return null;
	}

	public Usuario buscar(Object cedula) {
		Usuario usuario = dao.buscar(cedula);
		if (usuario != null) {
				return usuario;
			}
		return null;
	}

	public Usuario buscarUsuFicha(Object cedula) {
		return dao.buscar(cedula);
	}

	public void editar(Usuario usuario, String user) {
		usuario.setSede(buscar(usuario.getCedula()).getSede());
		Funcionario funcionario = funcionarioEJB.buscarFuncionarioPorUsuario(user);
		usuario.setFuncionario(funcionario);
		dao.editar(usuario);
	}

	public void editarTraslado(Usuario usuario) {
		dao.editar(usuario);
	}

	public void eliminar(Usuario usuario) {
		dao.crear(usuario);
	}

}
