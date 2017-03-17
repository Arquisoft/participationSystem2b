package es.uniovi.asw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hsqldb.jdbc.JDBCDriver;

import dao.Ciudadano;

public class BBDD {

	/**
	 * Metodo que establece conexión con la base de datos local
	 * 
	 * @return objeto conexion
	 */
	@SuppressWarnings("finally")
	public static Connection crearConexion() {
		Connection conexion = null;
		try {
			DriverManager.registerDriver(new JDBCDriver());
			String url = "jdbc:hsqldb:file:./DDBB/data/test";
			// String url = "jdbc:hsqldb:hsql://localhost/";
			String user = "SA";
			String pass = "";
			conexion = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			return conexion;
		}
	}

	/**
	 * Añade ciudadanos a la base de datos
	 * 
	 * @param ciudadanos,
	 *            lista de ciudadanos a insertar en la base de datos
	 */
	public static void insertarCiudadano(List<Ciudadano> ciudadanos) {
		Connection con = crearConexion();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("insert into CIUDADANO ");
			sb.append("(nombre, apellidos, email, direccion, nacionalidad, dni, fecha_nacimiento, password) ");
			sb.append("values (?,?,?,?,?,?,?,?)");
			PreparedStatement ps = con.prepareStatement(sb.toString());
			for (Ciudadano ciu : ciudadanos) {
				ps.setString(1, ciu.getNombre());
				ps.setString(2, ciu.getApellidos());
				ps.setString(3, ciu.getEmail());
				ps.setString(4, ciu.getDireccion());
				ps.setString(5, ciu.getNacionalidad());
				ps.setString(6, ciu.getDni());
				ps.setDate(7, ciu.getFecha_nacimiento());
				ps.setString(8, ciu.getPassword());
				ps.execute();
			}
			con.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Elimina 1 ciudadano cuyo dni se introduce como parametro
	 * 
	 * @param dni
	 *            del ciudadano a borrar
	 */
	public static void eliminarCiudadano(String dni) {
		Connection con = crearConexion();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("delete from CIUDADANO ");
			sb.append("where dni = ?");
			PreparedStatement ps = con.prepareStatement(sb.toString());
			ps.setString(1, dni);
			ps.execute();
			ps.close();
			con.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.print("Seguramente es porque el formato dni es incorrecto");
			e.printStackTrace();
		}

	}

	/**
	 * Se actualizan los datos de un usuario. Los nuevos datos se añaden a un
	 * objeto ciudadano que sera el que se use para actualizar los datos (se
	 * basa en el dni)
	 * 
	 * @param ciudadano
	 *            a actualizar
	 */
	public static void updateCiudadano(Ciudadano ciudadano) {
		Connection con = crearConexion();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE CIUDADANO "
					+ "set nombre= ?, apellidos= ?, email= ?, fecha_nacimiento= ?, direccion= ?, nacionalidad= ?"
					+ "where dni=?");
			PreparedStatement ps = con.prepareStatement(sb.toString());
			ps.setString(1, ciudadano.getNombre());
			ps.setString(2, ciudadano.getApellidos());
			ps.setString(3, ciudadano.getEmail());
			ps.setDate(4, ciudadano.getFecha_nacimiento());
			ps.setString(5, ciudadano.getDireccion());
			ps.setString(6, ciudadano.getNacionalidad());
			ps.setString(7, ciudadano.getDni());
			ps.executeUpdate();
			ps.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("no existe el ciudadano especificado");
			e.printStackTrace();
		}
	}

	public static Ciudadano obtenerCiudadano(String dni) {
		Connection con = crearConexion();
		String consulta = "SELECT c.* FROM ciudadano c WHERE c.dni = ?";
		Ciudadano ciudadano = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(consulta);
			ps.setString(1, dni);
			rs = ps.executeQuery();
			while (rs.next()) {
				ciudadano = new Ciudadano(rs.getString("nombre"), rs.getString("apellidos"), rs.getString("email"),
						rs.getString("direccion"), rs.getString("nacionalidad"), rs.getString("dni"),
						rs.getDate("fecha_nacimiento"));
				ciudadano.setPassword(rs.getString("password"));
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ciudadano;
	}

	/**
	 * Metodo que guarda en la base de datos la contraseña asociada al usuario
	 * que se identifica con el dni
	 */
	public static void guardaarPasswordUsuario(String dni, String password) {
		Connection con = crearConexion();
		String consulta = "update Ciudadano set password = ? where dni = ?";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(consulta);
			ps.setString(1, password);
			ps.setString(2, dni);
			ps.executeUpdate();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Elimina todos los ciudadanos
	 */
	public static void eliminarCiudadanos() {
		Connection con = crearConexion();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("delete from CIUDADANO ");
			PreparedStatement ps = con.prepareStatement(sb.toString());
			ps.execute();
			ps.close();
			con.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.print("Error al borrar todos los ciudadanos");
			e.printStackTrace();
		}
	}

}
