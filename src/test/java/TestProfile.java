import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class TestProfile {

	public static WebDriver driver;
	public static String baseUrl;

	@Before
	public void setUp() throws Exception {
		File pathToBinary = new File("D:\\firefox-sdk\\bin\\firefox.exe");
		FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		driver = new FirefoxDriver(ffBinary, firefoxProfile);
		baseUrl = "localhost:8000";
		driver.get(baseUrl + "/");
		Thread.sleep(1000);
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("sample@example.com");
		driver.findElement(By.xpath("//input[@type='password']")).clear();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("password");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(10000);
		driver.findElement(By.xpath("//a/i")).click();
		Thread.sleep(10000);
	}

	@After
	public void tearDown() throws Exception {
		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(1000);
		driver.quit();
	}

	@Test
	public void testButtons() throws InterruptedException {
		Thread.sleep(10000);
		WebElement addEventButton = driver.findElement(By.cssSelector("button.btn.btn__add-event-pr"));
		Thread.sleep(1000);
		String color = addEventButton.getCssValue("color");
		String backColor = addEventButton.getCssValue("background-color");
		String textAlignment = addEventButton.getCssValue("text-align");
		String verticalAlignment = addEventButton.getCssValue("vertical-align");
		String marginTop = addEventButton.getCssValue("margin-top");
		String text = addEventButton.getText();
		assertEquals("rgba(255, 255, 255, 1)", color);
		assertEquals("rgba(104, 184, 223, 1)", backColor);
		assertEquals("center", textAlignment);
		assertEquals("middle", verticalAlignment);
		assertEquals("23px", marginTop);
		assertEquals("Add event", text);
		WebElement addMeetingButton = driver.findElement(By.cssSelector("button.btn.btn__add-meeting-pr"));
		Thread.sleep(1000);
		color = addMeetingButton.getCssValue("color");
		backColor = addMeetingButton.getCssValue("background-color");
		textAlignment = addMeetingButton.getCssValue("text-align");
		verticalAlignment = addMeetingButton.getCssValue("vertical-align");
		marginTop = addMeetingButton.getCssValue("margin-top");
		text = addMeetingButton.getText();
		assertEquals("rgba(255, 255, 255, 1)", color);
		assertEquals("rgba(170, 205, 75, 1)", backColor);
		assertEquals("center", textAlignment);
		assertEquals("middle", verticalAlignment);
		assertEquals("23px", marginTop);
		assertEquals("Add meeting", text);
	}

	@Test
	public void testLabelsMyProfile() throws InterruptedException {
		Thread.sleep(10000);
		List<WebElement> elements = driver.findElements(By.cssSelector(".col-md-5 > div > h3"));
		Thread.sleep(10000);
		WebElement name = elements.get(0);
		WebElement email = elements.get(1);
		assertTrue(name.getText().contains("FirstName"));
		assertTrue(name.getText().contains("LastName"));
		assertTrue(email.getText().contains("sample@example.com"));
	}

	@Test
	public void testLabelsProfile() throws InterruptedException {
		Thread.sleep(10000);
		driver.findElement(By.id("ex1_value")).clear();
		driver.findElement(By.id("ex1_value")).sendKeys("martina");
		Thread.sleep(1000);
		List<WebElement> results = driver.findElements(By.cssSelector("#ex1_dropdown > .angucomplete-row"));
		Thread.sleep(1000);
		WebElement result = results.get(0);
		result.click();
		Thread.sleep(1000);
		List<WebElement> elements = driver.findElements(By.cssSelector(".col-md-5 > div > h3"));
		Thread.sleep(10000);
		WebElement name = elements.get(0);
		WebElement email = elements.get(1);
		assertTrue(name.getText().contains("Martina"));
		assertTrue(name.getText().contains("Toshevska"));
		assertTrue(email.getText().contains("martina.tosevska.95@gmail.com"));
	}

	@Test
	public void testHeadingText() {
		WebElement heading = driver.findElement(By.className("heading__style"));
		String headingText = heading.getText();
		String fontSize = heading.getCssValue("font-size");
		String color = heading.getCssValue("color");
		assertEquals("My Profile", headingText);
		assertEquals("30px", fontSize);
		assertEquals("rgba(149, 165, 166, 1)", color);
	}
}
