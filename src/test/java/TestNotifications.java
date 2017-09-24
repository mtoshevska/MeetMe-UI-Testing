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

public class TestNotifications {

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
		driver.findElement(By.xpath("//li[3]/a/notification-icon/div/div[2]/ng-transclude/i")).click();
		Thread.sleep(10000);
	}

	@After
	public void tearDown() throws Exception {
		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(1000);
		driver.quit();
	}

	@Test
	public void testNotificationStyle() {
		List<WebElement> notifications = driver.findElements(By.cssSelector(".col-md-8 > div > div"));
		for (WebElement notification : notifications) {
			String color = notification.getCssValue("color");
			String backColor = notification.getCssValue("background-color");
			String borderColor = notification.getCssValue("border-top-color");
			String padding = notification.getCssValue("padding-top");
			String marginBottom = notification.getCssValue("margin-bottom");
			assertEquals("rgba(49, 112, 143, 1)", color);
			assertEquals("rgba(217, 237, 247, 1)", backColor);
			assertEquals("rgba(188, 232, 241, 1)", borderColor);
			assertEquals("15px", padding);
			assertEquals("20px", marginBottom);
		}
	}

	@Test
	public void testNotificationLinks() throws InterruptedException {
		List<WebElement> notifications = driver.findElements(By.cssSelector(".col-md-8 > div > div > a"));
		int n = notifications.size();
		for (int i = 0; i < n; i++) {
			WebElement notification = notifications.get(i);
			String href = notification.getAttribute("href");
			notification.click();
			Thread.sleep(10000);
			String url = driver.getCurrentUrl();
			assertEquals(href, url);
			driver.navigate().back();
			Thread.sleep(10000);
			notifications = driver.findElements(By.cssSelector(".col-md-8 > div > div > a"));
			Thread.sleep(1000);
		}
	}

	@Test
	public void testButton() {
		WebElement button = driver.findElement(By.cssSelector("button.btn.btn-default"));
		String color = button.getCssValue("color");
		String backColor = button.getCssValue("background-color");
		String borderColor = button.getCssValue("border-top-color");
		String text = button.getText();
		assertEquals("rgba(51, 51, 51, 1)", color);
		assertEquals("rgba(255, 255, 255, 1)", backColor);
		assertEquals("rgba(204, 204, 204, 1)", borderColor);
		assertEquals("Load More", text);
	}

	@Test
	public void testHeadingText() {

	}
}
