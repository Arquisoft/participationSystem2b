package es.uniovi.asw.steps;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URL;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import es.uniovi.asw.controller.Application;

@ContextConfiguration(classes=Application.class, loader=SpringApplicationContextLoader.class)
@IntegrationTest
@WebAppConfiguration
public class LandingSteps {
  
  @Autowired
  protected WebApplicationContext context;

  protected MockMvc mvc;
  protected MvcResult result;

  
  @Value("${local.server.port}")
  protected int port;

  
  @When("^el cliente llama a (\\s+)/$")
  public void the_client_calls() throws Throwable {
    Assert.notNull(context);
    this.mvc = MockMvcBuilders.webAppContextSetup(context).build();
    result = mvc.perform(get("/")).andReturn();
  }

  @Then("^el usuario recibe status de pagina de inicio (\\d+)$")
  public void the_client_receives_status_code_of(int status) throws Throwable {
    assertThat(result.getResponse().getStatus(), is(status));
    System.out.println("status recibido: "+result.getResponse().getStatus());
  }

  @Then("^the client receives the string \"Home\"$")
  public void the_client_receives_the_string(String str) throws Throwable {
   assertThat(result.getResponse().getContentAsString(), containsString(str));
   System.out.println(result.getResponse().getContentAsString());
  }

}
