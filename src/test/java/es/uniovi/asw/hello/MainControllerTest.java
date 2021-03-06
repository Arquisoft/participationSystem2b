package es.uniovi.asw.hello;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import es.uniovi.asw.controller.Application;
import es.uniovi.asw.hello.UserInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class MainControllerTest {

    @Value("${local.server.port}")
    private int port;

    private URL base;
	private RestTemplate template;
	
	@Autowired
	private WebApplicationContext context;
	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/");
		template = new TestRestTemplate();
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

//	@Test
//	public void getLanding() throws Exception {
//		String userURI = base.toString() + "/user";  
//		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
//		assertThat(response.getBody(), containsString("Hola"));
//	}
	
	@Test
	public void getUser() throws Exception {
		String userURI = base.toString() + "/user";  
		ResponseEntity<String> response = template.getForEntity(userURI, String.class);
		UserInfo expected = new UserInfo("pepe",0);
	}
	
	
	
	  @Test
	  public void testLanding() throws Exception {
	    mvc.perform(get("/")).andExpect(status().isOk()).andExpect(content().string(containsString("Sesion")));
	  }
//	
//	  @Test
//	  public void testSort() throws Exception {
//	    mvc.perform(get("/sort")).andExpect(status().isOk()).andExpect(content().string(containsString("sorted")));
//	  }
//	
//	  @Test
//	  public void testSearchOK() throws Exception {
//	    mvc.perform(get("/search?name=pepe")).andExpect(status().isOk()).andExpect(content().string(containsString("pepe")));
//	  }

}