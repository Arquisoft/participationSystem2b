package es.uniovi.asw.business;

import java.util.ArrayList;

import es.uniovi.asw.dao.Ciudadano;

public interface Formatos {
	
	public AddUsuario addUsuario = new AddUsuario();
	
	public ArrayList<Ciudadano> leerCiudadanos(ArrayList<Ciudadano> ciudadanos, String ruta);
}
