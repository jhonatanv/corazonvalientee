package co.org.smart.corazonvaliente.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "FUNCIONARIOS_SEDES")
@NamedQueries({ @NamedQuery(name = FuncionarioSede.BUSCARFUNCIONARIOACTIVO, query = "select f from FuncionarioSede f where f.funcionario.cedula=?1 and f.estado=true"),
	@NamedQuery(name = FuncionarioSede.LISTARFUNCIONARIOSACTIVOS, query = "select f from FuncionarioSede f where f.sede.nombre =?1 and f.estado=true")})
public class FuncionarioSede implements Serializable {
	

	public static final String BUSCARFUNCIONARIOACTIVO="funcionarioActivoo.buscar";
	public static final String LISTARFUNCIONARIOSACTIVOS="funcionariosActivos.listar";

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_funcionarioSed")
	private int codigo;

	@ManyToOne
	@JoinColumn(name = "sede_funcionarioSed")
	private Sede sede;

	@ManyToOne
	@JoinColumn(name = "funcionario_funcionarioSed")
	private Funcionario funcionario;

	@Column(name = "estado_funcionarioSed")
	private boolean estado;

	public FuncionarioSede() {
	}

	public FuncionarioSede(Sede sede, Funcionario funcionario, boolean estado) {
		super();
		this.sede = sede;
		this.funcionario = funcionario;
		this.estado = estado;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

}
