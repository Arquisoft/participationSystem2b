package es.uniovi.asw;

import java.util.ArrayList;
import java.util.Scanner;

import dao.Ciudadano;

//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.List;
//
//import dao.Ciudadano;

public class MainPruebas {

	// @SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// Ciudadano a = new Ciudadano("Nacho", "Martin Franco",
		// "miemail@gmail.com", "12312312A",
		// "Calle Uria Oviedo", "Senegal", new Date(6, 2, 1995));
		// Ciudadano b = new Ciudadano("Pepe", "pepin pepon",
		// "pepesemail@gmail.com", "12312312B",
		// "Calle Uria Gijon", "Mozambique", new Date(6, 3, 1992));
		//
		// List<Ciudadano> cius = new ArrayList<Ciudadano>();
		// cius.add(a);
		// cius.add(b);
		// //BBDD bbdd = new BBDD();
		// BBDD.insertarCiudadano(cius);

		// AddUsuario cargarUsuarios = new AddUsuario();
		// cargarUsuarios.execute();

		System.out.print("Inserte ruta:");
		Scanner scanner = new Scanner(System.in);
		String ruta = scanner.nextLine();
		ruta = ruta.replace("\"", "");
		scanner.close();
		ArrayList<Ciudadano> ciudadanos = new ArrayList<Ciudadano>();
		Leer.Ciudadanos(ciudadanos, ruta);
		for (Ciudadano ciudadano : ciudadanos) {
			System.out.println("imprimiendo desde el main " + ciudadano);
		}
		
		BBDD.eliminarCiudadanos();
		BBDD.insertarCiudadano(ciudadanos);
		Ciudadano otro = BBDD.obtenerCiudadano(ciudadanos.get(0).getDni());
		System.out.println(otro);
	}

}
