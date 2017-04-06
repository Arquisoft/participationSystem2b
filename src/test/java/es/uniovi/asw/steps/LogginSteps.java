package es.uniovi.asw.steps;

import cucumber.api.java.en.*;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.PendingException;
import es.uniovi.asw.model.Participant;
import es.uniovi.asw.persistence.BBDD;


import org.junit.Assert;
import junit.framework.TestCase;
import net.sourceforge.jwebunit.junit.WebTester;


//import static net.sourceforge.jwebunit.junit.JWebUnit.*;

public class LogginSteps {
	
	private Participant participante;
	private WebTester navegador;  

    @Given("un usuario de id (.*) y contraseña (.*)$")
    public void existeUsuario(String id, String password) {
    	participante = BBDD.getParticipantByIDandPassword(id, password);
    	navegador = new WebTester();	
        navegador.setBaseUrl("http://localhost:8080");    	  	
    }
	
	  @When("^hace click (.+)$")
	  public void click(String nombreBoton) {
		  navegador.beginAt("/sesion2.JSP");  // Navegar a la URL
          navegador.assertTextPresent("identificador"); // Comprobar texto presente en la pÃ¡gina
//      	  mary.assertTitleEquals("Inicie sesiÃ³n");  // Comprobar tÃ­tulo de la pÃ¡gina
//    	  mary.assertTextPresent("usuario"); // Comprobar texto presente en la pÃ¡gina
//    	  navegador.assertButtonPresentWithText("Inicio sesión");
	  }
//	  
//	  @Then("^la página actual del navegador debe ser (.+)$")
//	  public void{
//		  
//	  }
//    
////
//    @When("^I move the mower$")
//    public void i_move_the_mower() {
//        mower.move();
//    }
//
//    @Then("^the mower should be in (\\d+),(\\d+) facing (.*)$")
//    public void the_mower_should_be_in_facing(int x, int y, String orientation) {
//        Position expectedPosition = new Position(x, y);
//        Orientation expectedOrientation = getOrientation(orientation);
//        Assert.assertEquals("Mower position", expectedPosition, mower.getPosition());
//        Assert.assertEquals("Mower orientation", expectedOrientation, mower.getOrientation());
//    }
//
//    @When("^I pivot the mower to the (.*)$")
//    public void i_pivot_the_mower_to_the(String direction) {
//        if (direction.equalsIgnoreCase("right")) {
//            mower.pivotRight();
//        } else if (direction.equalsIgnoreCase("left")) {
//            mower.pivotLeft();
//        }
//    }
//
//
//    private Orientation getOrientation(String orientation) {
//        return Orientation.valueOf(StringUtils.capitalize(orientation.toLowerCase()));
//}

}
