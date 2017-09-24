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

public class TestRequests {

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
		Thread.sleep(1000);
		driver.findElement(By.xpath("//ng-transclude/i")).click();
		Thread.sleep(10000);
	}

	@After
	public void tearDown() throws Exception {
		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(1000);
		driver.quit();
	}

	@Test
	public void testRequestStyle() {
		List<WebElement> requests = driver.findElements(By.cssSelector(".col-md-8 > div > div"));
		for (WebElement request : requests) {
			String color = request.getCssValue("color");
			String backColor = request.getCssValue("background-color");
			String borderColor = request.getCssValue("border-top-color");
			String padding = request.getCssValue("padding-top");
			String marginBottom = request.getCssValue("margin-bottom");
			assertEquals("rgba(49, 112, 143, 1)", color);
			assertEquals("rgba(217, 237, 247, 1)", backColor);
			assertEquals("rgba(188, 232, 241, 1)", borderColor);
			assertEquals("15px", padding);
			assertEquals("20px", marginBottom);
		}
	}

	@Test
	public void testRequestButtons() {
		List<WebElement> acceptButtons = driver
				.findElements(By.cssSelector(".col-md-8 > div > div > button.btn.btn-default.active"));
		for (WebElement button : acceptButtons) {
			String color = button.getCssValue("color");
			String backColor = button.getCssValue("background-color");
			String borderColor = button.getCssValue("border-top-color");
			String text = button.getText();
			assertEquals("rgba(51, 51, 51, 1)", color);
			assertEquals("rgba(230, 230, 230, 1)", backColor);
			assertEquals("rgba(173, 173, 173, 1)", borderColor);
			assertEquals("Accept", text);
		}
		List<WebElement> rejectButtons = driver
				.findElements(By.cssSelector(".col-md-8 > div > div > button.btn.btn-danger.active"));
		for (WebElement button : rejectButtons) {
			String color = button.getCssValue("color");
			String backColor = button.getCssValue("background-color");
			String borderColor = button.getCssValue("border-top-color");
			String text = button.getText();
			assertEquals("rgba(255, 255, 255, 1)", color);
			assertEquals("rgba(201, 48, 44, 1)", backColor);
			assertEquals("rgba(172, 41, 37, 1)", borderColor);
			assertEquals("Reject", text);
		}
	}

	@Test
	public void testHeadingText() {
		WebElement heading = driver.findElement(By.className("heading__style"));
		String headingText = heading.getText();
		String fontSize = heading.getCssValue("font-size");
		String color = heading.getCssValue("color");
		assertEquals("Meeting Requests", headingText);
		assertEquals("30px", fontSize);
		assertEquals("rgba(149, 165, 166, 1)", color);
	}
}
