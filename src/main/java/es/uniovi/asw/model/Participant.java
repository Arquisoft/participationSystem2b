package es.uniovi.asw.model;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="PARTICIPANT")
public class Participant implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
	private String nombre;
	private String apellidos;
	private String email;
	private String direccion;
	private String nacionalidad;
	private String dni;
	private String password;
	private String usuario;
	@Temporal(TemporalType.DATE)
	private Date fecha_nacimiento;
	
	@OneToMany(mappedBy="participant")
	private Set<Comment> comments = new HashSet<Comment>();
	@OneToMany(mappedBy="participant")
	private Set<Suggestion> suggestions = new HashSet<Suggestion>();

	Participant(){}
	public Participant(String nombre, String apellidos, String email, String direccion, String nacionalidad, String dni,
			Date fecha_nacimiento, String user) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.direccion = direccion;
		this.nacionalidad = nacionalidad;
		this.dni = dni;
		this.fecha_nacimiento = fecha_nacimiento;
		this.usuario=user;
	}
	
	
	
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	Set<Comment> _getComments() {
		return this.comments;
	}
	Set<Suggestion> _getSuggestions() {
		return this.suggestions;
	}
	
	public Set<Comment> getComments() {
		return new HashSet<Comment>(this.comments);
	}
	public Set<Suggestion> getSuggestions() {
		return new HashSet<Suggestion>(this.suggestions);
	}
	public void addComment(Comment comment){
		this.comments.add(comment);
	}
	public void addSuggestion(Suggestion suggestion){
		this.suggestions.add(suggestion);
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public Long getId(){
		return this.id;
	}

	@Override
	public String toString() {
		return "Participant [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email
				+ ", direccion=" + direccion + ", nacionalidad=" + nacionalidad + ", dni=" + dni + ", password="
				+ password + ", usuario=" + usuario + ", fecha_nacimiento=" + fecha_nacimiento + "]";
	}
	
	



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Participant other = (Participant) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}
	/**
	 * Metodo para crear la password de forma aleatoria.
	 */
	public void crearPassword() {
		password = "";
		char[] minusculas = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		char[] mayusculas = "abcdefghijklmnopqrstuvwxyz".toUpperCase().toCharArray();
		char[] numeros = "0123456789".toCharArray();
		char[] simbolos = "'¿?*+-$%".toCharArray();
		
		// Tiene una letra mayuscula
		Random random = new Random();
		int pos = random.nextInt(mayusculas.length);
		password += mayusculas[pos];
		
		// Tiene 5 letras minusculas
		for (int i = 0; i < 5; i++) {
			random = new Random();
			pos = random.nextInt(minusculas.length);
			password += minusculas[pos];
		}
		
		// Tiene un numero
		random = new Random();
		pos = random.nextInt(numeros.length);
		password += numeros[pos];
		
		// Tiene un simbolo especial 
		random = new Random();
		pos = random.nextInt(simbolos.length);
		password += simbolos[pos];
	}
	
	
}