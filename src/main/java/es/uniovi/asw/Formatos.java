package es.uniovi.asw;

import java.util.ArrayList;

import dao.Ciudadano;

public interface Formatos {
	
	public AddUsuario addUsuario = new AddUsuario();
	
	public ArrayList<Ciudadano> leerCiudadanos(ArrayList<Ciudadano> ciudadanos, String ruta);
}
