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

public class TestGraphCoverage {

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
	public void test0() throws InterruptedException {
		String url = driver.getCurrentUrl();
		assertEquals("http://localhost:8000/", url);
		driver.findElement(By.linkText("Register")).click();
		Thread.sleep(1000);
		url = driver.getCurrentUrl();
		assertEquals(url, "http://localhost:8000/#/register");
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(1000);
		url = driver.getCurrentUrl();
		assertEquals(url, "http://localhost:8000/#/login");
		driver.findElement(By.cssSelector("img.logo__size")).click();
		url = driver.getCurrentUrl();
		assertEquals(url, "http://localhost:8000/#");
	}

	@Test
	public void test1() throws InterruptedException {
		String url = driver.getCurrentUrl();
		assertEquals("http://localhost:8000/", url);
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(1000);
		url = driver.getCurrentUrl();
		assertEquals(url, "http://localhost:8000/#/login");
		driver.findElement(By.linkText("Register")).click();
		Thread.sleep(1000);
		url = driver.getCurrentUrl();
		assertEquals(url, "http://localhost:8000/#/register");
		driver.findElement(By.cssSelector("img.logo__size")).click();
		Thread.sleep(1000);
		url = driver.getCurrentUrl();
		assertEquals(url, "http://localhost:8000/#");
	}

	@Test
	public void test2() throws InterruptedException {
		String url = driver.getCurrentUrl();
		assertEquals("http://localhost:8000/", url);
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(1000);
		url = driver.getCurrentUrl();
		assertEquals(url, "http://localhost:8000/#/login");
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("sample@example.com");
		driver.findElement(By.xpath("//input[@type='password']")).clear();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("password");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(1000);
		url = driver.getCurrentUrl();
		assertEquals(url, "http://localhost:8000/#/calendar");
		driver.findElement(By.cssSelector("button.btn.btn__add-meeting")).click();
		Thread.sleep(1000);
		url = driver.getCurrentUrl();
		assertEquals("http://localhost:8000/#/meeting", url);
		driver.findElement(By.cssSelector("img.logo__size")).click();
		Thread.sleep(1000);
		url = driver.getCurrentUrl();
		assertEquals(url, "http://localhost:8000/#/calendar");
		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(1000);
		url = driver.getCurrentUrl();
		assertEquals(url, "http://localhost:8000/#/");
	}

	@Test
	public void test3() throws InterruptedException {
		String url = driver.getCurrentUrl();
		assertEquals("http://localhost:8000/", url);
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(1000);
		url = driver.getCurrentUrl();
		assertEquals(url, "http://localhost:8000/#/login");
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("sample@example.com");
		driver.findElement(By.xpath("//input[@type='password']")).clear();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("password");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(1000);
		url = driver.getCurrentUrl();
		assertEquals(url, "http://localhost:8000/#/calendar");
		driver.findElement(By.xpath("//a/i")).click();
		Thread.sleep(1000);
		url = driver.getCurrentUrl();
		assertEquals("http://localhost:8000/#/profile/", url);
		driver.findElement(By.cssSelector("img.logo__size")).click();
		Thread.sleep(1000);
		url = driver.getCurrentUrl();
		assertEquals(url, "http://localhost:8000/#/calendar");
		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(1000);
		url = driver.getCurrentUrl();
		assertEquals(url, "http://localhost:8000/#/");
	}

	@Test
	public void test4() throws InterruptedException {
		String url = driver.getCurrentUrl();
		assertEquals("http://localhost:8000/", url);
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(1000);
		url = driver.getCurrentUrl();
		assertEquals(url, "http://localhost:8000/#/login");
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("sample@example.com");
		driver.findElement(By.xpath("//input[@type='password']")).clear();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("password");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(1000);
		url = driver.getCurrentUrl();
		assertEquals(url, "http://localhost:8000/#/calendar");
		driver.findElement(By.xpath("//li[3]/a/notification-icon/div/div[2]/ng-transclude/i")).click();
		Thread.sleep(1000);
		url = driver.getCurrentUrl();
		assertEquals("http://localhost:8000/#/connectionNotifications", url);
		driver.findElement(By.cssSelector("img.logo__size")).click();
		Thread.sleep(1000);
		url = driver.getCurrentUrl();
		assertEquals(url, "http://localhost:8000/#/calendar");
		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(1000);
		url = driver.getCurrentUrl();
		assertEquals(url, "http://localhost:8000/#/");
	}

	@Test
	public void test5() throws InterruptedException {
		String url = driver.getCurrentUrl();
		assertEquals("http://localhost:8000/", url);
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(1000);
		url = driver.getCurrentUrl();
		assertEquals(url, "http://localhost:8000/#/login");
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("sample@example.com");
		driver.findElement(By.xpath("//input[@type='password']")).clear();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("password");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(1000);
		url = driver.getCurrentUrl();
		assertEquals(url, "http://localhost:8000/#/calendar");
		driver.findElement(By.xpath("//ng-transclude/i")).click();
		Thread.sleep(1000);
		url = driver.getCurrentUrl();
		assertEquals("http://localhost:8000/#/meetingRequests", url);
		driver.findElement(By.cssSelector("img.logo__size")).click();
		Thread.sleep(1000);
		url = driver.getCurrentUrl();
		assertEquals(url, "http://localhost:8000/#/calendar");
		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(1000);
		url = driver.getCurrentUrl();
		assertEquals(url, "http://localhost:8000/#/");
	}

	@Test
	public void test6() throws InterruptedException {
		String url = driver.getCurrentUrl();
		assertEquals("http://localhost:8000/", url);
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(1000);
		url = driver.getCurrentUrl();
		assertEquals(url, "http://localhost:8000/#/login");
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("sample@example.com");
		driver.findElement(By.xpath("//input[@type='password']")).clear();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("password");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(1000);
		url = driver.getCurrentUrl();
		assertEquals(url, "http://localhost:8000/#/calendar");
		driver.findElement(By.cssSelector("button.btn.btn__add-event")).click();
		Thread.sleep(1000);
		url = driver.getCurrentUrl();
		assertEquals("http://localhost:8000/#/event", url);
		driver.findElement(By.cssSelector("img.logo__size")).click();
		Thread.sleep(1000);
		url = driver.getCurrentUrl();
		assertEquals(url, "http://localhost:8000/#/calendar");
		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(1000);
		url = driver.getCurrentUrl();
		assertEquals(url, "http://localhost:8000/#/");
	}
}
