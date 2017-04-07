package es.uniovi.asw;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestSelenium {
	WebDriver driver = new HtmlUnitDriver();
	private static String baseUrl = "http://localhost:8080";

	@Test
	public void loginlogout() {
		driver.get(baseUrl + "/");
		// Usuario y contraseña correctos
		rellenaLogin("Hugo", "password");
		textoPresentePagina("Propuestas");
		logout();

		driver.get(baseUrl + "/");
		// Usuario correcto, contraseña incorrecta
		rellenaLogin("Hugo", "Password");
		textoPresentePagina("Inicio Sesion");

		driver.get(baseUrl + "/");
		// Usuario incorrecto, contraseña correcta
		rellenaLogin("hugo", "password");
		textoPresentePagina("Inicio Sesion");
	}
	
	@Test
	public void añadirPropuesta(){
		driver.get(baseUrl + "/");
		// Usuario y contraseña correctos
		rellenaLogin("Hugo", "password");
		textoPresentePagina("Propuestas");
		WebElement nombre = driver.findElement(By.id("crearPropuesta"));
		nombre.click();
		nombre = driver.findElement(By.id("title"));
		nombre.click();
		
		String hora = ""+System.currentTimeMillis();
		nombre.sendKeys("Propuesta " + hora);
		nombre = driver.findElement(By.id("suggestionText"));
		nombre.click();
		nombre.sendKeys("Propuesta " + hora);
		
		nombre = driver.findElement(By.id("btnCrearPropuesta"));
		
		textoPresentePagina("Propuesta " + hora);
	}

	public void rellenaLogin(String SNombre, String SPassword) {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		WebElement nombre = driver.findElement(By.id("usuario"));
		nombre.click();
		nombre.clear();
		nombre.sendKeys(SNombre);
		WebElement password = driver.findElement(By.id("password"));
		password.click();
		password.clear();
		password.sendKeys(SPassword);
		By boton = By.id("login");
		driver.findElement(boton).click();
	}

	public void logout() {
		By boton = By.id("logout");
		driver.findElement(boton).click();
	}

	public void textoPresentePagina(String texto) {
		List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + texto + "')]"));
		assertTrue("Texto " + texto + " no localizado!", list.size() > 0);
	}

	public void textoNoPresentePagina(String texto) {
		List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + texto + "')]"));
		assertTrue("Texto " + texto + " aun presente !", list.size() == 0);
	}

	public void EsperaCargaPaginaNoTexto(String texto, int timeout) {
		Boolean resultado = (new WebDriverWait(driver, timeout)).until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'" + texto + "')]")));

		assertTrue(resultado);
	}

	public List<WebElement> EsperaCargaPaginaxpath(String xpath, int timeout) {
		WebElement resultado = (new WebDriverWait(driver, timeout))
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		assertTrue(resultado != null);
		List<WebElement> elementos = driver.findElements(By.xpath(xpath));

		return elementos;
	}

	// Permite buscar por Id o Class con espera
	// @param criterio. "id" or "class" or "text"
	// Aviso. Que se usa espera por la visibilidad del elemento
	// De esta forma sirve tanto para carga de páginas enteras
	// como para elementos que estan ocultos y se hace visibles
	public List<WebElement> EsperaCargaPagina(String criterio, String id, int timeout) {
		String busqueda;
		if (criterio.equals("id"))
			busqueda = "//*[contains(@id,'" + id + "')]";
		else if (criterio.equals("class"))
			busqueda = "//*[contains(@class,'" + id + "')]";
		else
			busqueda = "//*[contains(text(),'" + id + "')]";
		return EsperaCargaPaginaxpath(busqueda, timeout);
	}
}
