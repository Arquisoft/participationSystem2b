package es.uniovi.asw.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.hsqldb.jdbc.JDBCDriver;

import es.uniovi.asw.model.Participant;

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
			String url = "jdbc:hsqldb:file:./DB/BaseTest/data/test";
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
	public static void addParticipants(List<Participant> ciudadanos) {
		Connection con = crearConexion();
		
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("insert into PARTICIPANT ");
			sb.append("(nombre, apellidos, email, direccion, nacionalidad, dni, fecha_nacimiento, password,usuario) ");
			sb.append("values (?,?,?,?,?,?,?,?,?)");
			PreparedStatement ps = con.prepareStatement(sb.toString());
			for (Participant ciu : ciudadanos) {
				Date fecha = new Date(ciu.getFecha_nacimiento().getTime());
				ps.setString(1, ciu.getNombre());
				ps.setString(2, ciu.getApellidos());
				ps.setString(3, ciu.getEmail());
				ps.setString(4, ciu.getDireccion());
				ps.setString(5, ciu.getNacionalidad());
				ps.setString(6, ciu.getDni());
				ps.setDate(7, fecha);
				ps.setString(8, ciu.getPassword());
				ps.setString(9, ciu.getUsuario());
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
	public static void deleteParticipant(String dni) {
		Connection con = crearConexion();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("delete from PARTICIPANT ");
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
	 * @param participant
	 *            a actualizar
	 */
	public static void updateParticipant(Participant participant) {
		Connection con = crearConexion();
		Date fecha = new Date(participant.getFecha_nacimiento().getTime());
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE PARTICIPANT "
					+ "set nombre= ?, apellidos= ?, email= ?, fecha_nacimiento= ?, direccion= ?, nacionalidad= ?, usuario= ? "
					+ "where dni=?");
			PreparedStatement ps = con.prepareStatement(sb.toString());
			ps.setString(1, participant.getNombre());
			ps.setString(2, participant.getApellidos());
			ps.setString(3, participant.getEmail());
			ps.setDate(4, fecha);
			ps.setString(5, participant.getDireccion());
			ps.setString(6, participant.getNacionalidad());
			ps.setString(7, participant.getUsuario());
			ps.setString(8, participant.getDni());
			ps.executeUpdate();
			ps.close();
			con.close();
		} catch (SQLException e) {
			System.err.println("no existe el ciudadano especificado");
			e.printStackTrace();
		}
	}

	public static Participant getParticipant(String dni) {
		Connection con = crearConexion();
		String consulta = "SELECT c.* FROM PARTICIPANT c WHERE c.dni = ?";
		Participant ciudadano = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(consulta);
			ps.setString(1, dni);
			rs = ps.executeQuery();
			while (rs.next()) {
				ciudadano = new Participant(rs.getString("nombre"), rs.getString("apellidos"), rs.getString("email"),
						rs.getString("direccion"), rs.getString("nacionalidad"), rs.getString("dni"),
						rs.getDate("fecha_nacimiento"),rs.getString("usuario"));
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
	
	
	public static Participant getParticipantByIDandPassword(String id, String password) {
		Connection con = crearConexion();
		String consulta = "SELECT * FROM PARTICIPANT  WHERE USUARIO = ? AND PASSWORD = ?";
		Participant ciudadano = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(consulta);
			ps.setString(1, id);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while (rs.next()) {
				Participant ciudadanoAux = new Participant(rs.getString("nombre"), rs.getString("apellidos"), rs.getString("email"),
						rs.getString("direccion"), rs.getString("nacionalidad"), rs.getString("dni"),
						rs.getDate("fecha_nacimiento"),rs.getString("usuario"));
				if(ciudadano.getUsuario().equals(id) && ciudadano.getPassword().equals(password))
					ciudadano=ciudadanoAux;
				
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
	public static void saveUserPassword(String dni, String password) {
		Connection con = crearConexion();
		String consulta = "update PARTICIPANT set password = ? where dni = ?";
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
	public static void deleteAllParticipants() {
		Connection con = crearConexion();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("delete from PARTICIPANT ");
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
