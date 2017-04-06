package es.uniovi.asw.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.uniovi.asw.controller.producers.KafkaProducer;
import es.uniovi.asw.hello.Message;
import es.uniovi.asw.model.Comment;
import es.uniovi.asw.model.Participant;
import es.uniovi.asw.model.Suggestion;
import es.uniovi.asw.service.Service;

@Controller
@Scope("session")
public class MainController {

	/**
	 * Atributos en sesion: user -> contiene el Particpant logueado suggestion
	 * -> la sugerencia que se selecciona para ver
	 */
	@Autowired
	private KafkaProducer kafkaProducer;

	@RequestMapping("/")
	public String landing(Model model) {
		model.addAttribute("message", new Message());
		model.addAttribute("comment", "");
		return "login";
	}

	// @RequestMapping("/send")
	// public String send(Model model, @ModelAttribute Message message) {
	// kafkaProducer.send("exampleTopic", message.getMessage());
	// return "redirect:/";
	// }

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
	public String verPropuesta(HttpSession sesion, Model model, @PathVariable("id") Long id) {
		model.addAttribute("comments", Service.getCommentService().findAllCommentsBySuggestionId(id));

		Suggestion suggestion = (Suggestion) Service.getSuggestionService().findSugById(id);
		model.addAttribute("suggestion", suggestion);
		sesion.setAttribute("suggestion", suggestion);

		return "showSuggestion";
	}

	@RequestMapping("/comentar")
	public String comentar(@RequestParam String comment, HttpSession sesion, Model model) {
		Suggestion s = (Suggestion) sesion.getAttribute("suggestion");
		if (!comment.equals("")) {
			Participant p = (Participant) sesion.getAttribute("user");
			Service.getCommentService().addComment(new Comment(p, s, comment));
			model.addAttribute("comments", Service.getCommentService().findAllCommentsBySuggestionId(s.getId()));
			comment = "";
		}
		
		System.out.println(sesion.getAttribute("suggestion"));
		System.out.println(comment);
		return "showSuggestion";
	}

	@RequestMapping("/votarPropSi")
	public String votarPropuestaSi(HttpSession sesion, Model model) {
		Suggestion s = (Suggestion) sesion.getAttribute("suggestion");
		Participant p = (Participant) sesion.getAttribute("user");

		s.increasePositiveVotes();
		Service.getSuggestionService().updateSuggestion(s);
		sesion.setAttribute("suggestion", s);

		return "showSuggestion";
	}

	@RequestMapping("/votarPropNo")
	public String votarPropuestaNo(HttpSession sesion, Model model) {
		Suggestion s = (Suggestion) sesion.getAttribute("suggestion");
		Participant p = (Participant) sesion.getAttribute("user");

		s.increaseNegativeVotes();
		Service.getSuggestionService().updateSuggestion(s);
		sesion.setAttribute("suggestion", s);

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

	@ModelAttribute("comments")
	public List<Comment> getComments(HttpSession sesion) {
		Suggestion s = (Suggestion) sesion.getAttribute("suggestion");
		if (s != null) {
			return Service.getCommentService().findAllCommentsBySuggestionId(s.getId());
		}
		return null;
	}

	@RequestMapping("/cerrarSesion")
	public String cerrarSesion(HttpSession session) {
		session.setAttribute("user", null);
		return "login";
	}

	@RequestMapping("/votaSiCom/{id}")
	public String votaSiCom(HttpSession session, @PathVariable("id") Long id, Model model) {
		Comment comment = Service.getCommentService().findCommentById(id);
		comment.increasePositiveVotes();
		Service.getCommentService().updateComment(comment);
		
		Suggestion s = (Suggestion) session.getAttribute("suggestion");
		model.addAttribute("comments", Service.getCommentService().findAllCommentsBySuggestionId(s .getId()));
		return "showSuggestion";
	}
	
	@RequestMapping("/votaNoCom/{id}")
	public String votaNoCom(HttpSession session, @PathVariable("id") Long id, Model model) {
		Comment comment = Service.getCommentService().findCommentById(id);
		comment.increaseNegativeVotes();
		Service.getCommentService().updateComment(comment);
		
		Suggestion s = (Suggestion) session.getAttribute("suggestion");
		model.addAttribute("comments", Service.getCommentService().findAllCommentsBySuggestionId(s .getId()));
		return "showSuggestion";
	}
	
}
