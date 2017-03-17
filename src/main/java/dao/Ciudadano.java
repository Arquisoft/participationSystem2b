package dao;

import java.sql.Date;
import java.util.Random;

public class Ciudadano {

	private String nombre, apellidos, email, direccion, nacionalidad, dni, password;
	private Date fecha_nacimiento;

	public Ciudadano(String nombre, String apellidos, String email, String direccion, String nacionalidad, String dni,
			Date fecha_nacimiento) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.direccion = direccion;
		this.nacionalidad = nacionalidad;
		this.dni = dni;
		this.fecha_nacimiento = fecha_nacimiento;
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

	@Override
	public String toString() {
		return "Ciudadano [nombre=" + nombre + ", apellidos=" + apellidos + ", email=" + email + ", direccion="
				+ direccion + ", nacionalidad=" + nacionalidad + ", dni=" + dni + ", password=" + password
				+ ", fecha_nacimiento=" + fecha_nacimiento + "]";
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((fecha_nacimiento == null) ? 0 : fecha_nacimiento.hashCode());
		result = prime * result + ((nacionalidad == null) ? 0 : nacionalidad.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		Ciudadano other = (Ciudadano) obj;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (nacionalidad == null) {
			if (other.nacionalidad != null)
				return false;
		} else if (!nacionalidad.equals(other.nacionalidad))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
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