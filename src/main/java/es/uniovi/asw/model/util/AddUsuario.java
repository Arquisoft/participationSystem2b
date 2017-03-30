package es.uniovi.asw.model.util;

import java.util.ArrayList;
import java.util.List;

import es.uniovi.asw.model.Participant;
import es.uniovi.asw.persistence.BBDD;

public class AddUsuario {

	public void execute() {
		List<Participant> ciudadanosBaseDatos = new ArrayList<Participant>();
		List<Participant> ciudadanos = LoadUsers.cargarFichero("./src/test/java/es/uniovi/asw/test.xlsx");
		for (int i = 0; i < ciudadanos.size(); i++) {
			Participant ciudadano =ciudadanos.get(i);
			Participant c = BBDD.getParticipant(ciudadano.getDni());
			if(c == null){
				ciudadanosBaseDatos.add(ciudadano);
				ciudadano.crearPassword();
				CrearCorreo.mandarCorreo(ciudadano);
			}
		}
		BBDD.addParticipants(ciudadanosBaseDatos);
	}
	
}
