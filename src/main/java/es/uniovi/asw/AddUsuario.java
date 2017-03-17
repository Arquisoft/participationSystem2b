package es.uniovi.asw;

import java.util.ArrayList;
import java.util.List;

import dao.Ciudadano;

public class AddUsuario {

	public void execute() {
		List<Ciudadano> ciudadanosBaseDatos = new ArrayList<Ciudadano>();
		List<Ciudadano> ciudadanos = LoadUsers.cargarFichero("./src/test/java/es/uniovi/asw/test.xlsx");
		for (int i = 0; i < ciudadanos.size(); i++) {
			Ciudadano ciudadano =ciudadanos.get(i);
			Ciudadano c = BBDD.obtenerCiudadano(ciudadano.getDni());
			if(c == null){
				ciudadanosBaseDatos.add(ciudadano);
				ciudadano.crearPassword();
				CrearCorreo.mandarCorreo(ciudadano);
			}
		}
		BBDD.insertarCiudadano(ciudadanosBaseDatos);
	}
	
}
