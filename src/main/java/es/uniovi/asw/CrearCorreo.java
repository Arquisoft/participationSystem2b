package es.uniovi.asw;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import dao.Ciudadano;

public class CrearCorreo {

	public static void mandarCorreo(Ciudadano ciudadano) {
		File file = null;
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		
		try {
			String nombre = ciudadano.getNombre();
			file = new File("./correos/" + nombre + ".txt");

			fileWriter = new FileWriter(file);
			String cadena = "Buenos dias " + nombre + "\n";
			cadena += "Usted a sido dado de alta con exito en el sistema de particion ciudadana.\n";
			cadena += "Sus credenciales son:\n";
			cadena += "\tUsuario: " + ciudadano.getEmail() + "\n";
			cadena += "\tContraseña: " + ciudadano.getPassword() + "\n";
			cadena += "\nUn saludo y gracias por darse de alta.\n";
			cadena += "\nAtentamente el ayuntamiento.";
			fileWriter.write(cadena);
			bufferedWriter = new BufferedWriter(fileWriter);
		} catch (IOException e) {
			System.out.println("No se ha podido crear el fichero");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.close();
				bufferedWriter.close();
			} catch (IOException e) {
				System.out.println("Error cerrando el fichero");
				e.printStackTrace();
			}
			
		}

	}
	
}
