import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class TestPageOpenings {

	public static WebDriver driver;
	public static String baseUrl;

	@Before
	public void setUp() throws Exception {
		File pathToBinary = new File("D:\\firefox-sdk\\bin\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		driver = new FirefoxDriver(ffBinary, firefoxProfile);
		baseUrl = "localhost:8000";
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void testOpenLoginPage() throws InterruptedException {
		driver.get(baseUrl + "/");
		Thread.sleep(1000);
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(1000);
		String url = driver.getCurrentUrl();
		System.out.println(url);
		assertEquals(url, "http://localhost:8000/#/login");
	}

	@Test
	public void testOpenRegisterPage() throws InterruptedException {
		driver.get(baseUrl + "/");
		Thread.sleep(1000);
		driver.findElement(By.linkText("Register")).click();
		Thread.sleep(1000);
		String url = driver.getCurrentUrl();
		System.out.println(url);
		assertEquals(url, "http://localhost:8000/#/register");
	}

	@Test
	public void testSetUpEventNoUserLoggedIn() throws InterruptedException {
		driver.get(baseUrl + "/");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button.btn.btn__main")).click();
		Thread.sleep(1000);
		String url = driver.getCurrentUrl();
		System.out.println(url);
		assertEquals(url, "http://localhost:8000/#/register");
	}
}
