import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class TestKeyboardActions {
	
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
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void testRegisterTab() throws InterruptedException {
		driver.findElement(By.linkText("Register")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys(Keys.TAB);
		WebElement active = driver.switchTo().activeElement();
		String identifier = active.getAttribute("placeholder");
		assertEquals("Last Name", identifier);
	}

	@Test
	public void testLoginTab() throws InterruptedException {
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys(Keys.TAB);
		WebElement active = driver.switchTo().activeElement();
		String identifier = active.getAttribute("placeholder");
		assertEquals("Password", identifier);
	}
	
	@Test
	public void testAddEventTab() throws InterruptedException {
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("sample@example.com");
		driver.findElement(By.xpath("//input[@type='password']")).clear();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("password");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button.btn.btn__add-event")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("eventName")).clear();
		driver.findElement(By.id("eventName")).sendKeys(Keys.TAB);
		WebElement active = driver.switchTo().activeElement();
		String identifier = active.getAttribute("id");
		assertEquals("eventLocation", identifier);
	}
	
	@Test
	public void testAddMeetingTab() throws InterruptedException {
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("sample@example.com");
		driver.findElement(By.xpath("//input[@type='password']")).clear();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("password");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button.btn.btn__add-meeting")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("meetingName")).clear();
		driver.findElement(By.id("meetingName")).sendKeys(Keys.TAB);
		WebElement active = driver.switchTo().activeElement();
		String identifier = active.getAttribute("id");
		assertEquals("meetingLocation", identifier);
	}
	
	@Test
	public void testRegisterEnter() throws InterruptedException {
		driver.findElement(By.linkText("Register")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		WebElement errorLabel = driver.findElement(By.className("ngn-message"));
		assertTrue(errorLabel.isDisplayed());
	}
	
	@Test
	public void testLoginEnter() throws InterruptedException {
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		WebElement errorLabel = driver.findElement(By.className("ngn-message"));
		assertTrue(errorLabel.isDisplayed());
	}
	
	@Test
	public void testAddEventEnter() throws InterruptedException {
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("sample@example.com");
		driver.findElement(By.xpath("//input[@type='password']")).clear();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("password");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(1000);
		driver.findElement(By.className("ngn-dismiss")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button.btn.btn__add-event")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("eventName")).clear();
		driver.findElement(By.id("eventName")).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		WebElement errorLabel = driver.findElement(By.className("ngn-message"));
		assertTrue(errorLabel.isDisplayed());
	}
	
	@Test
	public void testAddMeetingEnter() throws InterruptedException {
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("sample@example.com");
		driver.findElement(By.xpath("//input[@type='password']")).clear();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("password");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(1000);
		driver.findElement(By.className("ngn-dismiss")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button.btn.btn__add-meeting")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("meetingName")).clear();
		driver.findElement(By.id("meetingName")).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		WebElement errorLabel = driver.findElement(By.className("ngn-message"));
		assertTrue(errorLabel.isDisplayed());
	}
}
