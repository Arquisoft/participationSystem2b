package es.uniovi.asw;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import dao.Ciudadano;

public class Leer {
	
	public static ArrayList<Ciudadano> Ciudadanos(ArrayList<Ciudadano> ciudadanos, String ruta){
		String[] subcadenas = ruta.split("\\.");
		String extension = subcadenas[subcadenas.length-1];
		extension = extension.toLowerCase();
		extension = Character.toUpperCase(extension.charAt(0)) + extension.substring(1);
		try {
			Class<?> cl = Class.forName("es.uniovi.asw."+extension);
			Constructor<?> con = cl.getConstructor();
			Formatos xyz = (Formatos) con.newInstance();
			ciudadanos = xyz.leerCiudadanos(ciudadanos, ruta);
		} catch (Exception e) {
			System.out.println("Extensión no soportada");
		} catch (Error e) {
			System.out.println("Extensión no soportada");
		} 
		return ciudadanos;
	}
}
