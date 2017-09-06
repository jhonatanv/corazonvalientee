package co.org.smart.corazonvaliente.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "USUARIOS")
public class Usuario implements Serializable {
	
	public static final String LISTAR_USUARIOS = "Ficha.listarUsuarios";

	@Id
	@Column(name = "cedula_usuario", length=70)
	private String cedula;

	@ManyToOne
	@JoinColumn(name = "funcionario_usuario")
	private Funcionario funcionario;

	@ManyToOne
	@JoinColumn(name = "ciudadNac_usuario")
	private Ciudad ciudadNacimiento;

	@ManyToOne
	@JoinColumn(name = "sede_usuario")
	private Sede sede;
	
	@Lob
	@Column(name = "imagen_usuario")
	private byte[] imagen;

	@Column(name = "nombre_usuario", length=70)
	private String nombre;

	@Column(name = "alias_usuario", length=70)
	private String alias;

	@Column(name = "genero_usuario", length=50)
	private String genero;

	@Temporal(TemporalType.DATE)
	@Column(name = "fechaNac_usuario")
	private Date fechaNacimiento;

	@Temporal(TemporalType.DATE)
	@Column(name = "fechaIngreso_usuario")
	private Date fechaIngreso;

	@Column(name = "adiccion_usuario")
	private String adiccion;

	@Column(name = "tiempoAdic_usuario")
	private String tiempoAdiccion;

	@Column(name = "accesoAAdiccion_usuario")
	private String accesoAAdiccion;

	@Column(name = "antecedente_usuario")
	private String antecedentes;

	@Column(name = "problemasJud_usuario")
	private String problemaJudicial;

	@Column(name = "profecion_usuario")
	private String profesion;

	@Column(name = "estadoCivil_usuario")
	private String estadoCivil;

	@Column(name = "numeroHijos_usuario")
	private int numeroHijos;

	@Column(name = "estudios_usuario")
	private String estudios;

	@Column(name = "instituciones_usuario")
	private String instituciones;

	@Column(name = "tipoIngreso_usuario")
	private String tipoIngreso;

	@Column(name = "motivosRetiro_usuario")
	private String motivoRetiro;

	@Column(name = "descripcionIng_usuario")
	private String descripcionIngreso;

	@Column(name = "descripcionGen_usuario")
	private String descripcionGeneral;

	@Column(name = "modalidad_usuario")
	private String modalidad;

	@Column(name = "valor_usuario")
	private double valor;

	@Column(name = "etapa_usuario")
	private String etapa;

	@Column(name = "acudiente_usuario")
	private String acudiente;

	@Column(name = "celularAcudiente_usuario")
	private String celularAcudiente;

	
	public Usuario() {
	}

	public Usuario(String cedula, Funcionario funcionario, byte[] imagen, String nombre, String alias,
			String genero, Ciudad ciudadNacimiento, Date fechaNacimiento, Date fechaIngreso, String adiccion,
			String tiempoAdiccion, String accesoAAdiccion, String antecedentes, String problemaJudicial,
			String profesion, String estadoCivil, int numeroHijos, String estudios, String instituciones,
			String tipoIngreso, String motivoRetiro, String descripcionIngreso, String descripcionGeneral,
			String modalidad, double valor, String etapa, String acudiente, String celularAcudiente, Sede sede) {
		super();
		this.cedula = cedula;
		this.funcionario = funcionario;
		this.imagen = imagen;
		this.nombre = nombre;
		this.alias = alias;
		this.genero = genero;
		this.ciudadNacimiento = ciudadNacimiento;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaIngreso = fechaIngreso;
		this.adiccion = adiccion;
		this.tiempoAdiccion = tiempoAdiccion;
		this.accesoAAdiccion = accesoAAdiccion;
		this.antecedentes = antecedentes;
		this.problemaJudicial = problemaJudicial;
		this.profesion = profesion;
		this.estadoCivil = estadoCivil;
		this.numeroHijos = numeroHijos;
		this.estudios = estudios;
		this.instituciones = instituciones;
		this.tipoIngreso = tipoIngreso;
		this.motivoRetiro = motivoRetiro;
		this.descripcionIngreso = descripcionIngreso;
		this.descripcionGeneral = descripcionGeneral;
		this.modalidad = modalidad;
		this.valor = valor;
		this.etapa = etapa;
		this.acudiente = acudiente;
		this.celularAcudiente = celularAcudiente;
		this.sede=sede;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Ciudad getCiudadNacimiento() {
		return ciudadNacimiento;
	}

	public void setCiudadNacimiento(Ciudad ciudadNacimiento) {
		this.ciudadNacimiento = ciudadNacimiento;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getAdiccion() {
		return adiccion;
	}

	public void setAdiccion(String adiccion) {
		this.adiccion = adiccion;
	}

	public String getTiempoAdiccion() {
		return tiempoAdiccion;
	}

	public void setTiempoAdiccion(String tiempoAdiccion) {
		this.tiempoAdiccion = tiempoAdiccion;
	}

	public String getAccesoAAdiccion() {
		return accesoAAdiccion;
	}

	public void setAccesoAAdiccion(String accesoAAdiccion) {
		this.accesoAAdiccion = accesoAAdiccion;
	}

	public String getAntecedentes() {
		return antecedentes;
	}

	public void setAntecedentes(String antecedentes) {
		this.antecedentes = antecedentes;
	}

	public String getProblemaJudicial() {
		return problemaJudicial;
	}

	public void setProblemaJudicial(String problemaJudicial) {
		this.problemaJudicial = problemaJudicial;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public int getNumeroHijos() {
		return numeroHijos;
	}

	public void setNumeroHijos(int numeroHijos) {
		this.numeroHijos = numeroHijos;
	}

	public String getEstudios() {
		return estudios;
	}

	public void setEstudios(String estudios) {
		this.estudios = estudios;
	}

	public String getInstituciones() {
		return instituciones;
	}

	public void setInstituciones(String instituciones) {
		this.instituciones = instituciones;
	}

	public String getTipoIngreso() {
		return tipoIngreso;
	}

	public void setTipoIngreso(String tipoIngreso) {
		this.tipoIngreso = tipoIngreso;
	}

	public String getMotivoRetiro() {
		return motivoRetiro;
	}

	public void setMotivoRetiro(String motivoRetiro) {
		this.motivoRetiro = motivoRetiro;
	}

	public String getDescripcionIngreso() {
		return descripcionIngreso;
	}

	public void setDescripcionIngreso(String descripcionIngreso) {
		this.descripcionIngreso = descripcionIngreso;
	}

	public String getDescripcionGeneral() {
		return descripcionGeneral;
	}

	public void setDescripcionGeneral(String descripcionGeneral) {
		this.descripcionGeneral = descripcionGeneral;
	}

	public String getModalidad() {
		return modalidad;
	}

	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getEtapa() {
		return etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}

	public String getAcudiente() {
		return acudiente;
	}

	public void setAcudiente(String acudiente) {
		this.acudiente = acudiente;
	}

	public String getCelularAcudiente() {
		return celularAcudiente;
	}

	public void setCelularAcudiente(String celularAcudiente) {
		this.celularAcudiente = celularAcudiente;
	}

	public Sede getSede() {
		return sede;
	}
	
	public void setSede(Sede sede) {
		this.sede = sede;
	}
}
