package es.uniovi.asw;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import dao.Ciudadano;

public class AplicationTest {

	@Before
	public void before() {
		BBDD.eliminarCiudadanos();
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void addCiudadanoTest() {
		List<Ciudadano> ciudadanos = new ArrayList<Ciudadano>();

		Ciudadano c = new Ciudadano("Pepe", "Garcia", "email@prueba", "dir", "España", "564613I",
				new Date(1995 - 1900, 2, 25));
		ciudadanos.add(c);
		BBDD.insertarCiudadano(ciudadanos);

		Ciudadano cBD = BBDD.obtenerCiudadano("564613I");
		assertNotNull(cBD);
		assertEquals("Pepe", cBD.getNombre());
		assertEquals("Garcia", cBD.getApellidos());
		assertEquals("email@prueba", cBD.getEmail());
		assertEquals("dir", cBD.getDireccion());
		assertEquals("España", cBD.getNacionalidad());
		assertEquals("564613I", cBD.getDni());
		assertEquals(new Date(1995 - 1900, 2, 25), cBD.getFecha_nacimiento());

		c.setDireccion("direccion2");
		c.setEmail("otroemail@.com");
		c.setApellidos("Garcia Garcia");

		BBDD.updateCiudadano(c);
		cBD = BBDD.obtenerCiudadano("564613I");
		assertNotNull(cBD);
		assertEquals("Pepe", cBD.getNombre());
		assertEquals("Garcia Garcia", cBD.getApellidos());
		assertEquals("otroemail@.com", cBD.getEmail());
		assertEquals("direccion2", cBD.getDireccion());
		assertEquals("España", cBD.getNacionalidad());
		assertEquals("564613I", cBD.getDni());
		assertEquals(new Date(1995 - 1900, 2, 25), cBD.getFecha_nacimiento());

		BBDD.eliminarCiudadano("564613I");
		cBD = BBDD.obtenerCiudadano("564613I");
		assertNull(cBD);
	}

	@Test
	public void testCargarCSS() {
		// leemos y cargamos el fichero
		List<Ciudadano> ciudadanos = LoadUsers.cargarFichero("./src/test/java/es/uniovi/asw/test.xlsx");

		// probamos con el primer ciudadano
		Ciudadano c = ciudadanos.get(0);
		assertEquals("Juan", c.getNombre());
		assertEquals("Torres Pardo", c.getApellidos());
		assertEquals("juan@example.com", c.getEmail());
		// assertEquals("1985-10-10", String.valueOf(c.getFecha_nacimiento()));
		assertEquals("C/ Federico García Lorca 2", c.getDireccion());
		assertEquals("Español", c.getNacionalidad());
		assertEquals("90500084Y", c.getDni());

		// probamos con el segundo ciudadano
		c = ciudadanos.get(1);
		assertEquals("Luis", c.getNombre());
		assertEquals("López Fernando", c.getApellidos());
		assertEquals("luis@example.com", c.getEmail());
		// assertEquals("1970-03-02", String.valueOf(c.getFecha_nacimiento()));
		assertEquals("C/ Real Oviedo 2", c.getDireccion());
		assertEquals("Español", c.getNacionalidad());
		assertEquals("19160962F", c.getDni());

		// probamos con el tercer ciudadano
		c = ciudadanos.get(2);
		assertEquals("Ana", c.getNombre());
		assertEquals("Torres Pardo", c.getApellidos());
		assertEquals("ana@example.com", c.getEmail());
		// assertEquals("1960-01-01", String.valueOf(c.getFecha_nacimiento()));
		assertEquals("Av. De la Constitución 8", c.getDireccion());
		assertEquals("Español", c.getNacionalidad());
		assertEquals("09940449X", c.getDni());

	}

	@SuppressWarnings("deprecation")
	@Test
	public void testCargarCiudadanos() {
		// leemos y cargamos el fichero
		Xlsx x = new Xlsx();
		List<Ciudadano> ciudadanos = x.leerCiudadanos(new ArrayList<Ciudadano>(),
				"./src/test/java/es/uniovi/asw/test.xlsx");

		// probamos con el primer ciudadano
		Ciudadano c = ciudadanos.get(0);
		assertEquals("Juan", c.getNombre());
		assertEquals("Torres Pardo", c.getApellidos());
		assertEquals("juan@example.com", c.getEmail());
		assertEquals(new Date(1985 - 1900, 10 - 1, 10), c.getFecha_nacimiento());
		assertEquals("C/ Federico García Lorca 2", c.getDireccion());
		assertEquals("Español", c.getNacionalidad());
		assertEquals("90500084Y", c.getDni());

		// probamos con el segundo ciudadano
		c = ciudadanos.get(1);
		assertEquals("Luis", c.getNombre());
		assertEquals("López Fernando", c.getApellidos());
		assertEquals("luis@example.com", c.getEmail());
		assertEquals(new Date(1970 - 1900, 3 - 1, 2), c.getFecha_nacimiento());
		assertEquals("C/ Real Oviedo 2", c.getDireccion());
		assertEquals("Español", c.getNacionalidad());
		assertEquals("19160962F", c.getDni());

		// probamos con el tercer ciudadano
		c = ciudadanos.get(2);
		assertEquals("Ana", c.getNombre());
		assertEquals("Torres Pardo", c.getApellidos());
		assertEquals("ana@example.com", c.getEmail());
		assertEquals(new Date(1960 - 1900, 1 - 1, 1), c.getFecha_nacimiento());
		assertEquals("Av. De la Constitución 8", c.getDireccion());
		assertEquals("Español", c.getNacionalidad());
		assertEquals("09940449X", c.getDni());

		assertNotNull(BBDD.obtenerCiudadano("90500084Y"));
		assertNotNull(BBDD.obtenerCiudadano("19160962F"));
		assertNotNull(BBDD.obtenerCiudadano("09940449X"));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testCrearCorreo() {
		Ciudadano c = new Ciudadano("Marcos", "Garcia", "marcos@mail.com", "C/ peru 3", "Español", "87963215P",
				new Date(1990 - 1900, 2, 10));
		c.crearPassword();
		assertNotNull(c.getPassword());

		String rutaFichero = "./correos/" + c.getNombre() + ".txt";
		File fichero = new File(rutaFichero);
		assertFalse(fichero.exists());

		CrearCorreo.mandarCorreo(c);
		String[] correo = new String[9];

		correo[0] = "Buenos dias " + c.getNombre();
		correo[1] = "Usted a sido dado de alta con exito en el sistema de particion ciudadana.";
		correo[2] = "Sus credenciales son:";
		correo[3] = "\tUsuario: " + c.getEmail();
		correo[4] = "\tContraseña: " + c.getPassword();
		correo[5] = "";
		correo[6] = "Un saludo y gracias por darse de alta.";
		correo[7] = "";
		correo[8] = "Atentamente el ayuntamiento.";
		fichero = new File(rutaFichero);
		assertTrue(fichero.exists());

		try {
			BufferedReader reader = new BufferedReader(new FileReader(fichero));

			int i = 0;
			while (reader.ready()) {
				assertEquals(correo[i], reader.readLine());
				i++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		fichero.delete();
	}

	@Test
	public void testEqualsXlsxCsv() {
		ArrayList<Ciudadano> xlsx = new ArrayList<Ciudadano>();
		ArrayList<Ciudadano> cvs = new ArrayList<Ciudadano>();

		String rutaXLSX = "./src/test/java/es/uniovi/asw/test.xlsx";
		String rutaCVS = "./src/test/java/es/uniovi/asw/test.csv";

		Leer.Ciudadanos(xlsx, rutaXLSX);
		Leer.Ciudadanos(cvs, rutaCVS);

		for (int i = 0; i < cvs.size(); i++) {
			assertTrue((xlsx.get(i)).equals(cvs.get(i)));
		}
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testEliminarCiudadano() {
		List<Ciudadano> ciudadanos = new ArrayList<Ciudadano>();
		
		Ciudadano ciudadano = new Ciudadano("Hugo", "Perez", "yo@me.com", "Calle no se que Oviedo", "español", "123456789A", new Date(18, 7, 1995));
		
		ciudadanos.add(ciudadano);
		BBDD.insertarCiudadano(ciudadanos);
		Ciudadano cBBDD = BBDD.obtenerCiudadano("123456789A");
		assertNotNull(cBBDD);
		
		BBDD.eliminarCiudadano("123456789A");
		
		cBBDD = BBDD.obtenerCiudadano("123456789A");
		assertNull(cBBDD);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testEliminarTodosCiudadanos() {
		List<Ciudadano> ciudadanos = new ArrayList<Ciudadano>();
		Ciudadano ciudadano = new Ciudadano("Hugo", "Perez", "yo@me.com", "Calle no se que Oviedo", "español", "123456789A", new Date(18, 7, 1995));
		
		ciudadanos.add(ciudadano);
		BBDD.insertarCiudadano(ciudadanos);
		Ciudadano cBBDD = BBDD.obtenerCiudadano("123456789A");
		assertNotNull(cBBDD);
		
		BBDD.eliminarCiudadanos();
		
		cBBDD = BBDD.obtenerCiudadano("123456789A");
		assertNull(cBBDD);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCrearPassword() {
		List<Ciudadano> ciudadanos = new ArrayList<Ciudadano>();
		
		Ciudadano ciudadano = new Ciudadano("Hugo", "Perez", "yo@me.com", "Calle no se que Oviedo", "español", "1234A", new Date(18, 7, 1995));
		
		ciudadanos.add(ciudadano);
		BBDD.insertarCiudadano(ciudadanos);
		Ciudadano cBBDD = BBDD.obtenerCiudadano("1234A");
		cBBDD.crearPassword();
		String password = cBBDD.getPassword();
		BBDD.guardaarPasswordUsuario("1234A", password);
		cBBDD = BBDD.obtenerCiudadano("1234A");
		
		assertEquals(password, cBBDD.getPassword());
	}
	
}
