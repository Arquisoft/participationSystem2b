package es.uniovi.asw;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dao.Ciudadano;

/**
 * Main application
 * 
 * @author Labra
 *
 */
public class LoadUsers {

//	@SuppressWarnings("deprecation")
	/**
	 * Metodo que carga el fichero y devuelve una lista de ciudadanos.
	 * 
	 * @param ruta
	 * @return
	 */
	public static List<Ciudadano> cargarFichero(String ruta) {
		List<Ciudadano> participants = new ArrayList<Ciudadano>();
		try {
			FileInputStream file = new FileInputStream(new File(ruta));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();
//			rowIterator.next();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				ArrayList<Object> aux = new ArrayList<Object>();
				for (int i = 0; i < 7; i++) {
					aux.add(row.getCell(i) != null ? row.getCell(i).toString() : null);
				}
//				for (int i = 4; i < 7; i++) {
//					aux.add(row.getCell(i) != null ? row.getCell(i).toString() : null);
//				}

				String nombre = row.getCell(0) != null ? row.getCell(0).toString() : null;
				if(nombre != null && nombre.equals("Nombre"))
					continue;
				String fecha = row.getCell(3) != null ? row.getCell(3).toString() : null;
				
//				String fecha = String.valueOf(aux.get(3));
//				String mesS = fecha.split("-")[1];
//				
//				int mes = sacarMes(mesS);
//				int dia = Integer.parseInt(fecha.split("-")[0]);
//				
//				
//				int year = Integer.parseInt(fecha.split("-")[2]);
//				Date nacimiento = new Date(year-1900, mes-1, dia) ;
				
				Date date = new SimpleDateFormat("dd-MMM-yyyy").parse(fecha);
			    java.sql.Date nacimiento = new java.sql.Date(date.getTime());

				Ciudadano ciudadano = new Ciudadano(aux.get(0).toString(), aux.get(1).toString(), aux.get(2).toString(),
						aux.get(4).toString(), aux.get(5).toString(), aux.get(6).toString(), nacimiento);

				participants.add(ciudadano);
			}

			file.close();
			workbook.close();
		} catch (Exception e) {
			System.err.println("Error al leer del excel");
			e.printStackTrace();
		}
		return participants;
	}

}
