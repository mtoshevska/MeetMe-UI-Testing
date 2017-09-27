import static org.junit.Assert.*;

import java.io.File;
import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class TestAddEvent {

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
		driver.findElement(By.cssSelector("button.btn.btn__add-event")).click();
		Thread.sleep(1000);
	}

	@After
	public void tearDown() throws Exception {
		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(1000);
		driver.quit();
	}

	@Test
	public void testPlaceholders() {
		String placeholder1 = driver.findElement(By.id("eventName")).getAttribute("placeholder");
		String placeholder2 = driver.findElement(By.id("eventLocation")).getAttribute("placeholder");
		String placeholder3 = driver.findElement(By.xpath("//input[@value='']")).getAttribute("placeholder");
		String placeholder4 = driver.findElement(By.xpath("(//input[@value=''])[2]")).getAttribute("placeholder");
		assertEquals("Add title...", placeholder1);
		assertEquals("Add location...", placeholder2);
		assertEquals("Add start date and time...", placeholder3);
		assertEquals("Add end date and time...", placeholder4);
	}

	@Test
	public void testButton() throws InterruptedException {
		WebElement button = driver.findElement(By.xpath("//button[@type='button']"));
		Thread.sleep(1000);
		String color = button.getCssValue("color");
		String backColor = button.getCssValue("background-color");
		String textAlignment = button.getCssValue("text-align");
		String verticalAlignment = button.getCssValue("vertical-align");
		String text = button.getText();
		assertEquals("rgba(255, 255, 255, 1)", color);
		assertEquals("rgba(104, 184, 223, 1)", backColor);
		assertEquals("center", textAlignment);
		assertEquals("middle", verticalAlignment);
		assertEquals("Create Event", text);
	}

	@Test
	public void testStartDatePicker() throws InterruptedException {
		Thread.sleep(10000);
		driver.findElement(By.cssSelector("i.fa.fa-calendar")).click();
		WebElement pickerDays = driver.findElement(By.xpath(
				"//body/div[@class='datetimepicker datetimepicker-dropdown-bottom-right dropdown-menu'][position()=last()-1]/div[@class='datetimepicker-days']//td[@class='day active']"));
		Thread.sleep(1000);
		pickerDays.click();
		Thread.sleep(1000);
		WebElement pickerHours = driver.findElement(By.xpath(
				"//body/div[@class='datetimepicker datetimepicker-dropdown-bottom-right dropdown-menu'][position()=last()-1]/div[@class='datetimepicker-hours']//span[24]"));
		Thread.sleep(1000);
		pickerHours.click();
		Thread.sleep(1000);
		WebElement pickerMinutes = driver.findElement(By.xpath(
				"//body/div[@class='datetimepicker datetimepicker-dropdown-bottom-right dropdown-menu'][position()=last()-1]/div[@class='datetimepicker-minutes']//span[12]"));
		Thread.sleep(1000);
		pickerMinutes.click();
		Thread.sleep(5000);
		LocalDate date = LocalDate.now();
		String text = driver.findElement(By.xpath("//date-time-picker[@label='Event Start']//input"))
				.getAttribute("value");
		assertEquals(date.toString() + " 23:55", text);
	}

	@Test
	public void testEndDatePicker() throws InterruptedException {
		driver.findElement(By.xpath("//div[5]/date-time-picker/div[2]/span/i")).click();
		Thread.sleep(10000);
		WebElement pickerDays = driver.findElement(By.xpath(
				"//body/div[@class='datetimepicker datetimepicker-dropdown-bottom-right dropdown-menu'][last()]/div[@class='datetimepicker-days']//td[@class='day active']"));
		Thread.sleep(1000);
		pickerDays.click();
		Thread.sleep(1000);
		WebElement pickerHours = driver.findElement(By.xpath(
				"//body/div[@class='datetimepicker datetimepicker-dropdown-bottom-right dropdown-menu'][last()]/div[@class='datetimepicker-hours']//span[24]"));
		Thread.sleep(1000);
		pickerHours.click();
		Thread.sleep(1000);
		WebElement pickerMinutes = driver.findElement(By.xpath(
				"//body/div[@class='datetimepicker datetimepicker-dropdown-bottom-right dropdown-menu'][last()]/div[@class='datetimepicker-minutes']//span[12]"));
		Thread.sleep(1000);
		pickerMinutes.click();
		Thread.sleep(5000);
		LocalDate date = LocalDate.now();
		String text = driver.findElement(By.xpath("//date-time-picker[@label='Event End']//input"))
				.getAttribute("value");
		assertEquals(date.toString() + " 23:55", text);
	}

	@Test
	public void testHeadingText() {
		WebElement heading = driver.findElement(By.className("heading__style"));
		String headingText = heading.getText();
		String fontSize = heading.getCssValue("font-size");
		String color = heading.getCssValue("color");
		assertEquals("Schedule event", headingText);
		assertEquals("30px", fontSize);
		assertEquals("rgba(149, 165, 166, 1)", color);
	}
}
