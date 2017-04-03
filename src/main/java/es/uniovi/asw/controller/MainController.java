package es.uniovi.asw.controller;


import java.util.ArrayList;
import java.util.Date;
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
    @RequestMapping(value = "/loguearse", 
			method = RequestMethod.POST)
    public String loguearse( HttpSession sesion,Model model,@RequestParam String usuario, @RequestParam String password) {
        Participant user = Service.getParticipantService().findLogableUser(usuario,password);
        if(user!=null){
        	sesion.setAttribute("user", user);
        	List<Participant> lista = new ArrayList<Participant>();
        	
        	List<Suggestion> suggestions= new ArrayList<Suggestion>();// Service.getSuggestionService().getAllSuggestions();
        	suggestions.add(new Suggestion((Participant)sesion.getAttribute("user"), "prueba", "jeje", "jaja"));
        	suggestions.add(new Suggestion((Participant)sesion.getAttribute("user"), "Propuesta 2", "Lorem Ipsum es simplemente el texto de relleno de las imprentas y archivos de texto. Lorem Ipsum ha sido el texto de relleno estándar de las industrias desde el año 1500, cuando un impresor (N. del T. persona que se dedica a la imprenta) desconocido usó una galería de textos y los mezcló de tal manera que logró hacer un libro de textos especimen. No sólo sobrevivió 500 años, sino que tambien ingresó como texto de relleno en documentos electrónicos, quedando esencialmente igual al original. Fue popularizado en los 60s con la creación de las hojas , las cuales contenian pasajes de Lorem Ipsum, y más recientemente con software de autoedición, como por ejemplo Aldus PageMaker, el cual incluye versiones de Lorem Ipsum.", "cateogria1"));
        	model.addAttribute("suggestions",suggestions);
        	return "suggestions";
        }
    	return "login";
    }
    
    @RequestMapping(value = "/registrar", 
			method = RequestMethod.POST)
    public String registrar(HttpSession sesion,Model model,@RequestParam String archivo) {
        System.out.println(archivo);
    	return "principalUsuario";
    }
    
    @RequestMapping("/mostrarPropuestas")
    public String mostrarPropuestas(HttpSession sesion,Model model, @ModelAttribute Message message) {
    	// es de prueba, habria que llamr al servicefindAllSuggestions
    	model.addAttribute("suggestions",((Participant)sesion.getAttribute("user")).getSuggestions());
    	return "suggestions";
    }
    
    @RequestMapping("/verPropuesta/{id}")
    public String verPropuesta(HttpSession sesion,Model model, @PathVariable("id") String id, @ModelAttribute Message message) {
    	// es de prueba, habria que llamr al servicefindAllSuggestions
    	System.out.println(id);
    	return "suggestions";
    }

}