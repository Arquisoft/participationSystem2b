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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.model.Comment;
import es.uniovi.asw.model.Participant;
import es.uniovi.asw.model.Suggestion;
import es.uniovi.asw.model.util.CrearCorreo;
import es.uniovi.asw.model.util.Leer;
import es.uniovi.asw.model.util.LoadUsers;
import es.uniovi.asw.model.util.Xlsx;
import es.uniovi.asw.persistence.BBDD;
import es.uniovi.asw.service.Service;
import es.uniovi.asw.service.impl.CommentServiceImpl;
import es.uniovi.asw.service.impl.ParticipantServiceImpl;
import es.uniovi.asw.service.impl.SuggestionServiceImpl;

public class AplicationTest {

	@Before
	public void before() {
		BBDD.deleteAllParticipants();
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void jpaTest(){
		CommentServiceImpl servComment=Service.getCommentService();
		ParticipantServiceImpl servPart= Service.getParticipantService();
		SuggestionServiceImpl servSug=Service.getSuggestionService();
		// creamos un participant
		Participant c = new Participant("Pepe", "Garcia", "email@prueba", "dir", "España", "564613I",
				new Date(1995 - 1900, 2, 25));
		
		//lo metemos en la base de datos
		servPart.addParticipant(c);
		assertNotNull(Service.getParticipantService().findParticipant("564613I"));
		// creamos una sugerencia
		Suggestion sug = new Suggestion(c, "prueba", "para probar", "categoria");
		assertTrue(c.getSuggestions().contains(sug));
		servSug.addSuggestion(sug);
		
		Comment comment = new Comment(c, sug, "comentario");
		
		assertTrue(sug.getComments().contains(comment));
		assertTrue(c.getComments().contains(comment));
		servComment.addComment(comment);
		
		assertNotNull(servSug.getSuggestionByParticipant(c));
		servComment.deleteComment(comment);
		servSug.deleteSuggestion(sug);
		
		servPart.deleteParticipantByDni("564613I");
		
	}
	@SuppressWarnings("deprecation")
	@Test
	public void addCiudadanoTest() {
		
		
		List<Participant> ciudadanos = new ArrayList<Participant>();

		Participant c = new Participant("Pepe", "Garcia", "email@prueba", "dir", "España", "564613I",
				new Date(1995 - 1900, 2, 25));

		ciudadanos.add(c);
		BBDD.addParticipants(ciudadanos);

		Participant cBD = BBDD.getParticipant("564613I");
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

		BBDD.updateParticipant(c);
		cBD = BBDD.getParticipant("564613I");
		assertNotNull(cBD);
		assertEquals("Pepe", cBD.getNombre());
		assertEquals("Garcia Garcia", cBD.getApellidos());
		assertEquals("otroemail@.com", cBD.getEmail());
		assertEquals("direccion2", cBD.getDireccion());
		assertEquals("España", cBD.getNacionalidad());
		assertEquals("564613I", cBD.getDni());
		assertEquals(new Date(1995 - 1900, 2, 25), cBD.getFecha_nacimiento());

		BBDD.deleteParticipant("564613I");
		cBD = BBDD.getParticipant("564613I");
		assertNull(cBD);
	}

	@Test
	public void testCargarCSS() {
		// leemos y cargamos el fichero
		List<Participant> ciudadanos = LoadUsers.cargarFichero("./src/test/java/es/uniovi/asw/test.xlsx");

		// probamos con el primer ciudadano
		Participant c = ciudadanos.get(0);
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
		List<Participant> ciudadanos = x.leerCiudadanos(new ArrayList<Participant>(),
				"./src/test/java/es/uniovi/asw/test.xlsx");

		// probamos con el primer ciudadano
		Participant c = ciudadanos.get(0);
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

		assertNotNull(BBDD.getParticipant("90500084Y"));
		assertNotNull(BBDD.getParticipant("19160962F"));
		assertNotNull(BBDD.getParticipant("09940449X"));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testCrearCorreo() {
		Participant c = new Participant("Marcos", "Garcia", "marcos@mail.com", "C/ peru 3", "Español", "87963215P",
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
		ArrayList<Participant> xlsx = new ArrayList<Participant>();
		ArrayList<Participant> cvs = new ArrayList<Participant>();

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
		List<Participant> ciudadanos = new ArrayList<Participant>();
		
		Participant ciudadano = new Participant("Hugo", "Perez", "yo@me.com", "Calle no se que Oviedo", "español", "123456789A", new Date(18, 7, 1995));
		
		ciudadanos.add(ciudadano);
		BBDD.addParticipants(ciudadanos);
		Participant cBBDD = BBDD.getParticipant("123456789A");
		assertNotNull(cBBDD);
		
		BBDD.deleteParticipant("123456789A");
		
		cBBDD = BBDD.getParticipant("123456789A");
		assertNull(cBBDD);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testEliminarTodosCiudadanos() {
		List<Participant> ciudadanos = new ArrayList<Participant>();
		Participant ciudadano = new Participant("Hugo", "Perez", "yo@me.com", "Calle no se que Oviedo", "español", "123456789A", new Date(18, 7, 1995));
		
		ciudadanos.add(ciudadano);
		BBDD.addParticipants(ciudadanos);
		Participant cBBDD = BBDD.getParticipant("123456789A");
		assertNotNull(cBBDD);
		
		BBDD.deleteAllParticipants();
		
		cBBDD = BBDD.getParticipant("123456789A");
		assertNull(cBBDD);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testCrearPassword() {
		List<Participant> ciudadanos = new ArrayList<Participant>();
		
		Participant ciudadano = new Participant("Hugo", "Perez", "yo@me.com", "Calle no se que Oviedo", "español", "1234A", new Date(18, 7, 1995));
		
		ciudadanos.add(ciudadano);
		BBDD.addParticipants(ciudadanos);
		Participant cBBDD = BBDD.getParticipant("1234A");
		cBBDD.crearPassword();
		String password = cBBDD.getPassword();
		BBDD.saveUserPassword("1234A", password);
		cBBDD = BBDD.getParticipant("1234A");
		
		assertEquals(password, cBBDD.getPassword());
	}
	
}
