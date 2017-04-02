package es.uniovi.asw.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        	lista.add(new Participant("1", "", "", "", "", "", new Date(), ""));
        	lista.add(new Participant("2", "", "", "", "", "", new Date(), ""));
        	System.out.println("exito");
        	model.addAttribute("lista",lista);
        	return "principalUsuario";
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
    	System.out.println(sesion.getAttribute("user"));
    	List<Suggestion> suggestions= new ArrayList<Suggestion>();// Service.getSuggestionService().getAllSuggestions();
    	suggestions.add(new Suggestion((Participant)sesion.getAttribute("user"), "prueba", "jeje", "jaja"));
    	model.addAttribute("suggestions",suggestions);
    	return "suggestions";
    }

}