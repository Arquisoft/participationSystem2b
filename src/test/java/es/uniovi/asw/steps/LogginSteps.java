package es.uniovi.asw.steps;

import cucumber.api.java.en.*;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.PendingException;
import es.uniovi.asw.model.Participant;
import es.uniovi.asw.persistence.BBDD;
import es.uniovi.asw.service.Service;

import java.util.List;

import org.junit.Assert;
import junit.framework.TestCase;
//import net.sourceforge.jwebunit.junit.WebTester;
//import static net.sourceforge.jwebunit.junit.JWebUnit.*;

public class LogginSteps {
	
	private Participant participante;
	
    @Given("^una lista de usuarios:$")
    public void comprobarUsuarios(List<User> users) throws Throwable {
      for (User u: users) {
        System.out.println("Usuario ... " + u.id + " - " + u.password);
        Assert.assertNotNull(Service.getParticipantService().findLogableUser(u.id, u.password));
      }
    }
    
	
	  @When("^Un usuario hace login con id \"([^\"]*)\" y contraseña \"([^\"]*)\"$")
	  public void logConUsuario(String id, String password) {
		  participante = Service.getParticipantService().findLogableUser(id, password);
	      Assert.assertNotNull(participante);
	  }
	  
	  @Then("^debe mostrar el mensaje \"(.+)\"$")
	  public void mostrarMensaje(String mensaje){
		  if(participante!=null)
			  System.out.println(mensaje);
		  else
			  System.err.println("No se ha mostrado el mensaje correctamente");
	  }
	  

	  public static class User {
		    public String id;
		    public String password;
		    public User(String id, String password){
		    	this.id=id;
		    	this.password=password;
		    }
	  }
}
