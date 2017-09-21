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

public class TestLogin {

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
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void testPlaceholders() throws InterruptedException {
		driver.get(baseUrl + "/");
		Thread.sleep(1000);
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(1000);
		String placeholder1 = driver.findElement(By.xpath("(//input[@type='text'])[2]")).getAttribute("placeholder");
		String placeholder2 = driver.findElement(By.xpath("//input[@type='password']")).getAttribute("placeholder");
		assertEquals(placeholder1, "Email");
		assertEquals(placeholder2, "Password");
	}

	@Test
	public void testEqualWidth() throws InterruptedException {
		driver.get(baseUrl + "/");
		Thread.sleep(1000);
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(1000);
		String width1 = driver.findElement(By.xpath("(//input[@type='text'])[2]")).getCssValue("width");
		String width2 = driver.findElement(By.xpath("//input[@type='password']")).getCssValue("width");
		String width3 = driver.findElement(By.xpath("//button[@type='submit']")).getCssValue("width");
		String width4 = driver.findElement(By.xpath("//button[@type='button']")).getCssValue("width");
		String width5 = driver.findElement(By.xpath("(//button[@type='button'])[2]")).getCssValue("width");
		assertEquals(width1, width2);
		assertEquals(width2, width3);
		assertEquals(width3, width4);
		assertEquals(width4, width5);
	}

	@Test
	public void testEmailEmpty() throws InterruptedException {
		driver.get(baseUrl + "/");
		Thread.sleep(1000);
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@type='password']")).clear();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("password");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(1000);
		String errorLabelText = driver.findElement(By.className("ngn-message")).getText();
		Thread.sleep(1000);
		assertEquals(errorLabelText, "The user name or password is incorrect.");
	}

	@Test
	public void testPasswordEmpty() throws InterruptedException {
		driver.get(baseUrl + "/");
		Thread.sleep(1000);
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("sample@example.com");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(1000);
		String errorLabelText = driver.findElement(By.className("ngn-message")).getText();
		Thread.sleep(1000);
		assertEquals(errorLabelText, "The user name or password is incorrect.");
	}

	@Test
	public void testWrongPassword() throws InterruptedException {
		driver.get(baseUrl + "/");
		Thread.sleep(1000);
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("sample@example.com");
		driver.findElement(By.xpath("//input[@type='password']")).clear();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("password11");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(1000);
		String errorLabelText = driver.findElement(By.className("ngn-message")).getText();
		Thread.sleep(1000);
		assertEquals(errorLabelText, "The user name or password is incorrect.");
	}

	@Test
	public void testWrongEmail() throws InterruptedException {
		driver.get(baseUrl + "/");
		Thread.sleep(1000);
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("sample11@example.com");
		driver.findElement(By.xpath("//input[@type='password']")).clear();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("password");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(1000);
		String errorLabelText = driver.findElement(By.className("ngn-message")).getText();
		Thread.sleep(1000);
		assertEquals(errorLabelText, "The user name or password is incorrect.");
	}

	@Test
	public void testSuccessfullLogin() throws InterruptedException {
		driver.get(baseUrl + "/");
		Thread.sleep(1000);
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("sample@example.com");
		driver.findElement(By.xpath("//input[@type='password']")).clear();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("password");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(1000);
		String url = driver.getCurrentUrl();
		assertEquals(url, "http://localhost:8000/#/calendar");
	}

	@Test
	public void testButtons() throws InterruptedException {
		driver.get(baseUrl + "/");
		Thread.sleep(1000);
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(1000);
		String color = driver.findElement(By.xpath("//button[@type='submit']")).getCssValue("color");
		String backColor = driver.findElement(By.xpath("//button[@type='submit']")).getCssValue("background-color");
		String textAlignment = driver.findElement(By.xpath("//button[@type='submit']")).getCssValue("text-align");
		String verticalAlignment = driver.findElement(By.xpath("//button[@type='submit']"))
				.getCssValue("vertical-align");
		String text = driver.findElement(By.xpath("//button[@type='submit']")).getText();
		assertEquals(color, "rgba(255, 255, 255, 1)");
		assertEquals(backColor, "rgba(91, 192, 222, 1)");
		assertEquals(textAlignment, "center");
		assertEquals(verticalAlignment, "middle");
		assertEquals(text, "Login");
		color = driver.findElement(By.xpath("//button[@type='button']")).getCssValue("color");
		backColor = driver.findElement(By.xpath("//button[@type='button']")).getCssValue("background-color");
		textAlignment = driver.findElement(By.xpath("//button[@type='button']")).getCssValue("text-align");
		verticalAlignment = driver.findElement(By.xpath("//button[@type='button']")).getCssValue("vertical-align");
		text = driver.findElement(By.xpath("//button[@type='button']")).getText();
		assertEquals(color, "rgba(51, 51, 51, 1)");
		assertEquals(backColor, "rgba(240, 240, 240, 1)");
		assertEquals(textAlignment, "center");
		assertEquals(verticalAlignment, "middle");
		assertEquals(text, "| Connect with Facebook");
		color = driver.findElement(By.xpath("(//button[@type='button'])[2]")).getCssValue("color");
		backColor = driver.findElement(By.xpath("(//button[@type='button'])[2]")).getCssValue("background-color");
		textAlignment = driver.findElement(By.xpath("(//button[@type='button'])[2]")).getCssValue("text-align");
		verticalAlignment = driver.findElement(By.xpath("(//button[@type='button'])[2]")).getCssValue("vertical-align");
		text = driver.findElement(By.xpath("(//button[@type='button'])[2]")).getText();
		assertEquals(color, "rgba(51, 51, 51, 1)");
		assertEquals(backColor, "rgba(240, 240, 240, 1)");
		assertEquals(textAlignment, "center");
		assertEquals(verticalAlignment, "middle");
		assertEquals(text, "| Connect with Google+");
	}
}
