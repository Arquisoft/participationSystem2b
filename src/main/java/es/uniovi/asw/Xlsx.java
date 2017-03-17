package es.uniovi.asw;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dao.Ciudadano;

public class Xlsx implements Formatos {

	@Override
	public ArrayList<Ciudadano> leerCiudadanos(ArrayList<Ciudadano> ciudadanos, String ruta) {
		try {
			FileInputStream file = new FileInputStream(new File(ruta));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				ArrayList<Object> aux = new ArrayList<Object>();
				for (int i = 0; i < 7; i++) {
					aux.add(row.getCell(i) != null ? row.getCell(i).toString() : null);
				}

				String nombre = row.getCell(0) != null ? row.getCell(0).toString() : null;
				if (nombre != null && nombre.equals("Nombre"))
					continue;
				String fecha = row.getCell(3) != null ? row.getCell(3).toString() : null;

				Date date = new SimpleDateFormat("dd-MMM-yyyy").parse(fecha);
				java.sql.Date nacimiento = new java.sql.Date(date.getTime());

				Ciudadano ciudadano = new Ciudadano(aux.get(0).toString(), aux.get(1).toString(), aux.get(2).toString(),
						aux.get(4).toString(), aux.get(5).toString(), aux.get(6).toString(), nacimiento);

				addUsuario.execute();
				
				ciudadanos.add(ciudadano);
			}

			file.close();
			workbook.close();
		} catch (Exception e) {
			System.err.println("Error al leer del excel");
			e.printStackTrace();
		}
		return ciudadanos;
	}

}