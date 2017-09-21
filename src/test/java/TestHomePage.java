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

public class TestHomePage {

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
		assertEquals(url, "http://localhost:8000/#/login");
	}

	@Test
	public void testOpenRegisterPage() throws InterruptedException {
		driver.get(baseUrl + "/");
		Thread.sleep(1000);
		driver.findElement(By.linkText("Register")).click();
		Thread.sleep(1000);
		String url = driver.getCurrentUrl();
		assertEquals(url, "http://localhost:8000/#/register");
	}

	@Test
	public void testSetUpEventNoUserLoggedIn() throws InterruptedException {
		driver.get(baseUrl + "/");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button.btn.btn__main")).click();
		Thread.sleep(1000);
		String url = driver.getCurrentUrl();
		assertEquals(url, "http://localhost:8000/#/register");
	}

	@Test
	public void testHeadingText() throws InterruptedException {
		driver.get(baseUrl + "/");
		Thread.sleep(1000);
		String headingText = driver.findElement(By.className("text-over-image")).getText();
		String textAlignment = driver.findElement(By.className("text-over-image")).getCssValue("text-align");
		String fontSize = driver.findElement(By.className("text-over-image")).getCssValue("font-size");
		String color = driver.findElement(By.className("text-over-image")).getCssValue("color");
		assertEquals(headingText,
				"The social network for event organizing and scheduling meetings with shared Calendar.");
		assertEquals(textAlignment, "center");
		assertEquals(fontSize, "25px");
		assertEquals(color, "rgba(149, 165, 166, 1)");
	}

	@Test
	public void testButtons() throws InterruptedException {
		driver.get(baseUrl + "/");
		Thread.sleep(1000);
		String color = driver.findElement(By.linkText("Login")).getCssValue("color");
		String backColor = driver.findElement(By.linkText("Login")).getCssValue("background-color");
		String textAlignment = driver.findElement(By.linkText("Login")).getCssValue("text-align");
		assertEquals(color, "rgba(255, 255, 255, 1)");
		assertEquals(backColor, "transparent");
		assertEquals(textAlignment, "left");
		color = driver.findElement(By.linkText("Register")).getCssValue("color");
		backColor = driver.findElement(By.linkText("Register")).getCssValue("background-color");
		textAlignment = driver.findElement(By.linkText("Register")).getCssValue("text-align");
		assertEquals(color, "rgba(255, 255, 255, 1)");
		assertEquals(backColor, "transparent");
		assertEquals(textAlignment, "left");
		color = driver.findElement(By.cssSelector("button.btn.btn__main")).getCssValue("color");
		backColor = driver.findElement(By.cssSelector("button.btn.btn__main")).getCssValue("background-color");
		textAlignment = driver.findElement(By.cssSelector("button.btn.btn__main")).getCssValue("text-align");
		assertEquals(color, "rgba(127, 140, 141, 1)");
		assertEquals(backColor, "transparent");
		assertEquals(textAlignment, "center");
	}
}
