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

public class TestRegister {

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
		driver.findElement(By.linkText("Register")).click();
		Thread.sleep(1000);
		String placeholder1 = driver.findElement(By.xpath("(//input[@type='text'])[2]")).getAttribute("placeholder");
		String placeholder2 = driver.findElement(By.xpath("(//input[@type='text'])[3]")).getAttribute("placeholder");
		String placeholder3 = driver.findElement(By.xpath("(//input[@type='text'])[4]")).getAttribute("placeholder");
		String placeholder4 = driver.findElement(By.xpath("//input[@type='password']")).getAttribute("placeholder");
		String placeholder5 = driver.findElement(By.xpath("(//input[@type='password'])[2]"))
				.getAttribute("placeholder");
		assertEquals(placeholder1, "First Name");
		assertEquals(placeholder2, "Last Name");
		assertEquals(placeholder3, "Email");
		assertEquals(placeholder4, "Password");
		assertEquals(placeholder5, "Confirm Password");
	}

	@Test
	public void testEqualWidth() throws InterruptedException {
		driver.get(baseUrl + "/");
		Thread.sleep(1000);
		driver.findElement(By.linkText("Register")).click();
		Thread.sleep(1000);
		String width1 = driver.findElement(By.xpath("(//input[@type='text'])[2]")).getCssValue("width");
		String width2 = driver.findElement(By.xpath("(//input[@type='text'])[3]")).getCssValue("width");
		String width3 = driver.findElement(By.xpath("(//input[@type='text'])[4]")).getCssValue("width");
		String width4 = driver.findElement(By.xpath("//input[@type='password']")).getCssValue("width");
		String width5 = driver.findElement(By.xpath("(//input[@type='password'])[2]")).getCssValue("width");
		String width6 = driver.findElement(By.xpath("//button[@type='submit']")).getCssValue("width");
		assertEquals(width1, width2);
		assertEquals(width2, width3);
		assertEquals(width3, width4);
		assertEquals(width4, width5);
		assertEquals(width5, width6);
	}

	@Test
	public void testAllEmpty() throws InterruptedException {
		driver.get(baseUrl + "/");
		Thread.sleep(1000);
		driver.findElement(By.linkText("Register")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(1000);
		String errorLabelText = driver.findElement(By.className("ngn-message")).getText();
		Thread.sleep(1000);
		assertNotEquals("", errorLabelText);
	}

	@Test
	public void testFirstNameEmpty() throws InterruptedException {
		driver.get(baseUrl + "/");
		Thread.sleep(1000);
		driver.findElement(By.linkText("Register")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("LastName");
		driver.findElement(By.xpath("(//input[@type='text'])[4]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[4]")).sendKeys("some.text@example.com");
		driver.findElement(By.xpath("//input[@type='password']")).clear();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("pass");
		driver.findElement(By.xpath("(//input[@type='password'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='password'])[2]")).sendKeys("pass");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(1000);
		String errorLabelText = driver.findElement(By.className("ngn-message")).getText();
		Thread.sleep(1000);
		assertTrue(errorLabelText.contains("The FirstName field is required"));
	}

	@Test
	public void testLastNameEmpty() throws InterruptedException {
		driver.get(baseUrl + "/");
		Thread.sleep(1000);
		driver.findElement(By.linkText("Register")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("FirstName");
		driver.findElement(By.xpath("(//input[@type='text'])[4]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[4]")).sendKeys("some.text@example.com");
		driver.findElement(By.xpath("//input[@type='password']")).clear();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("pass");
		driver.findElement(By.xpath("(//input[@type='password'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='password'])[2]")).sendKeys("pass");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(1000);
		String errorLabelText = driver.findElement(By.className("ngn-message")).getText();
		Thread.sleep(1000);
		assertTrue(errorLabelText.contains("The LastName field is required"));
	}

	@Test
	public void testEmailEmpty() throws InterruptedException {
		driver.get(baseUrl + "/");
		Thread.sleep(1000);
		driver.findElement(By.linkText("Register")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("FirstName");
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("LastName");
		driver.findElement(By.xpath("//input[@type='password']")).clear();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("pass");
		driver.findElement(By.xpath("(//input[@type='password'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='password'])[2]")).sendKeys("pass");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(1000);
		String errorLabelText = driver.findElement(By.className("ngn-message")).getText();
		Thread.sleep(1000);
		assertTrue(errorLabelText.contains("The Email field is required"));
	}

	@Test
	public void testPasswordEmpty() throws InterruptedException {
		driver.get(baseUrl + "/");
		Thread.sleep(1000);
		driver.findElement(By.linkText("Register")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("FirstName");
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("LastName");
		driver.findElement(By.xpath("(//input[@type='text'])[4]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[4]")).sendKeys("some.text@example.com");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(1000);
		String errorLabelText = driver.findElement(By.className("ngn-message")).getText();
		Thread.sleep(1000);
		assertTrue(errorLabelText.contains("The Password field is required"));
	}

	@Test
	public void testConfirmPasswordEmpty() throws InterruptedException {
		driver.get(baseUrl + "/");
		Thread.sleep(1000);
		driver.findElement(By.linkText("Register")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("FirstName");
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("LastName");
		driver.findElement(By.xpath("(//input[@type='text'])[4]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[4]")).sendKeys("some.text@example.com");
		driver.findElement(By.xpath("//input[@type='password']")).clear();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("password");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(1000);
		String errorLabelText = driver.findElement(By.className("ngn-message")).getText();
		Thread.sleep(1000);
		assertTrue(errorLabelText.contains("The password and confirmation password do not match"));
	}

	@Test
	public void testNotMatchingPaswords() throws InterruptedException {
		driver.get(baseUrl + "/");
		Thread.sleep(1000);
		driver.findElement(By.linkText("Register")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("FirstName");
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("LastName");
		driver.findElement(By.xpath("(//input[@type='text'])[4]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[4]")).sendKeys("some.text@example.com");
		driver.findElement(By.xpath("//input[@type='password']")).clear();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("password");
		driver.findElement(By.xpath("(//input[@type='password'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='password'])[2]")).sendKeys("password1");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(1000);
		String errorLabelText = driver.findElement(By.className("ngn-message")).getText();
		Thread.sleep(1000);
		assertTrue(errorLabelText.contains("The password and confirmation password do not match"));
	}

	@Test
	public void testWrongEmailFormat() throws InterruptedException {
		driver.get(baseUrl + "/");
		Thread.sleep(1000);
		driver.findElement(By.linkText("Register")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("FirstName");
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("LastName");
		driver.findElement(By.xpath("(//input[@type='text'])[4]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[4]")).sendKeys("some.text");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(1000);
		String errorLabelText = driver.findElement(By.className("ngn-message")).getText();
		Thread.sleep(1000);
		assertTrue(errorLabelText.contains("The email format is wrong"));
	}

	@Test
	public void testSuccessfullRegistration() throws InterruptedException {
		driver.get(baseUrl + "/");
		Thread.sleep(1000);
		driver.findElement(By.linkText("Register")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("FirstName");
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("LastName");
		driver.findElement(By.xpath("(//input[@type='text'])[4]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[4]")).sendKeys("example1@example.com");
		driver.findElement(By.xpath("//input[@type='password']")).clear();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("password");
		driver.findElement(By.xpath("(//input[@type='password'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='password'])[2]")).sendKeys("password");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(1000);
		String labelText = driver.findElement(By.className("ngn-message")).getText();
		Thread.sleep(1000);
		assertTrue(labelText.contains("User has been registered successfully"));
		//page url can be used for assertion
	}

	@Test
	public void testRegisterWithTakenName() throws InterruptedException {
		driver.get(baseUrl + "/");
		Thread.sleep(1000);
		driver.findElement(By.linkText("Register")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("FirstName");
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[3]")).sendKeys("LastName");
		driver.findElement(By.xpath("(//input[@type='text'])[4]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[4]")).sendKeys("sample@example.com");
		driver.findElement(By.xpath("//input[@type='password']")).clear();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("password");
		driver.findElement(By.xpath("(//input[@type='password'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='password'])[2]")).sendKeys("password");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(1000);
		String errorLabelText = driver.findElement(By.className("ngn-message")).getText();
		Thread.sleep(1000);
		assertTrue(errorLabelText.contains("Name sample@example.com is already taken"));
	}
}
