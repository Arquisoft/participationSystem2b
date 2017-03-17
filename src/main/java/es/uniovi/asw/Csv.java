package es.uniovi.asw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import dao.Ciudadano;

public class Csv implements Formatos {

	@Override
	public ArrayList<Ciudadano> leerCiudadanos(ArrayList<Ciudadano> ciudadanos, String ruta) {
		try {

			FileInputStream is = new FileInputStream(ruta);
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader buffReader = new BufferedReader(isr);

			String str = "";

			while ((str = buffReader.readLine()) != null) {
				String[] trozos = str.split(";");
				if (trozos[0].equals("Nombre"))
					continue;
				Date date = new SimpleDateFormat("dd/mm/yyyy").parse(trozos[3]);
				java.sql.Date nacimiento = new java.sql.Date(date.getTime());
				Ciudadano ciu = new Ciudadano(trozos[0], trozos[1], trozos[2], trozos[4], trozos[5], trozos[6],
						nacimiento);
				ciudadanos.add(ciu);
			}

			buffReader.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error leyendo el fichero");
		}
		return ciudadanos;
	}

}
