package es.uniovi.asw.model.util;

import java.util.ArrayList;

import es.uniovi.asw.model.Participant;

public interface Formatos {
	
	public AddUsuario addUsuario = new AddUsuario();
	
	public ArrayList<Participant> leerCiudadanos(ArrayList<Participant> ciudadanos, String ruta);
}
