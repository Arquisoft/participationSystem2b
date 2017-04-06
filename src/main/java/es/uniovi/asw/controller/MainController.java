package es.uniovi.asw.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.uniovi.asw.controller.producers.KafkaProducer;
import es.uniovi.asw.hello.Message;
import es.uniovi.asw.model.Participant;
import es.uniovi.asw.model.Suggestion;
import es.uniovi.asw.service.Service;

@Controller
public class MainController {

	@Autowired
	private KafkaProducer kafkaProducer;

	@RequestMapping("/")
	public String landing(Model model) {
		model.addAttribute("message", new Message());
		return "login";
	}

	@RequestMapping("/send")
	public String send(Model model, @ModelAttribute Message message) {
		kafkaProducer.send("exampleTopic", message.getMessage());
		return "redirect:/";
	}

	@RequestMapping(value = "/loguearse", method = RequestMethod.POST)
	public String loguearse(HttpSession sesion, Model model, @RequestParam String usuario,
			@RequestParam String password) {
		Participant user = Service.getParticipantService().findLogableUser(usuario, password);
		String resultado = "login";
		if (user != null) {
			if (user.getUsuario().equals("Admin1"))
				resultado = "principalAdmin";
			else
				resultado = "principalUsuario";

			sesion.setAttribute("user", user);
		}
		return resultado;
	}

	@RequestMapping(value = "/registrar", method = RequestMethod.POST)
	public String registrar(HttpSession sesion, Model model, @RequestParam String archivo) {
		System.out.println(archivo);
		return "principalUsuario";
	}

	@RequestMapping("/mostrarPropuestas")
	public String mostrarPropuestas(HttpSession sesion, Model model) {
		// es de prueba, habria que llamr al servicefindAllSuggestions
		// model.addAttribute("suggestions",((Participant)sesion.getAttribute("user")).getSuggestions());
		return "principalUsuario";
	}

	@RequestMapping("/crearPropuesta")
	public String crearPropuesta(HttpSession sesion, Model model) {
		System.out.println(sesion.getAttribute("user"));
		return "addSuggestion";
	}

	@RequestMapping("/anadirPropuesta")
	public String anadirPropuesta(HttpSession sesion, Model model, @RequestParam String titulo,
			@RequestParam String categoria, @RequestParam String propuesta) {
		if (!titulo.equals("") && !propuesta.equals("")) {
			Suggestion sug = new Suggestion((Participant) sesion.getAttribute("user"), titulo, propuesta, categoria);
			Service.getSuggestionService().addSuggestion(sug);
			getSuggestions();
			return "principalUsuario";
		}
		return "addSuggestion";
	}

	@RequestMapping("/verPropuesta/{id}")
	public String verPropuesta(HttpSession sesion, Model model, @PathVariable("id") Long id,
			@ModelAttribute Message message) {
		// es de prueba, habria que llamr al servicefindAllSuggestions
		Suggestion suggestion = (Suggestion) Service.getSuggestionService().findSugById(id);
		model.addAttribute("suggestion", suggestion);
		sesion.setAttribute("suggestion", suggestion);
		return "showSuggestion";
	}

	@ModelAttribute("suggestions")
	public List<Suggestion> getSuggestions() {
		return Service.getSuggestionService().getAllSuggestions();
	}

	@ModelAttribute("user")
	public Participant getUser(HttpSession sesion) {
		return (Participant) sesion.getAttribute("user");
	}

	@RequestMapping("/cerrarSesion")
	public String cerrarSesion(HttpSession session) {
		session.setAttribute("user", null);
		return "login";
	}

	@RequestMapping(value = "/votarPositivo")
	public String votarPositivo(HttpSession session) {
		System.out.println("Pulsado si1");
		Suggestion suggestion = (Suggestion) session.getAttribute("suggestion");
		System.out.println("Pulsado si2");
		suggestion.increasePositiveVotes();
		System.out.println("Pulsado si3");
		Service.getSuggestionService().updateSuggestion(suggestion);
		System.out.println("Pulsado si4");
		int numvotos = suggestion.getPositiveVotes();
		System.out.println(numvotos);
		getSuggestions();
		return "principalUsuario";
	}

	@RequestMapping(value = "/votarNegativo")
	public String votarNegativo(HttpSession session) {
		System.out.println("Pulsado no1");
		Suggestion suggestion = (Suggestion) session.getAttribute("suggestion");
		System.out.println("Pulsado no2");
		suggestion.increaseNegativeVotes();
		System.out.println("Pulsado no3");
		Service.getSuggestionService().updateSuggestion(suggestion);
		System.out.println("Pulsado no4");
		int numvotos = suggestion.getNegativeVotes();
		System.out.println(numvotos);
		getSuggestions();
		return "principalUsuario";
	}
}
