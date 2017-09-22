import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

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

public class TestCalendar {

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
	}

	@After
	public void tearDown() throws Exception {
		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(1000);
		driver.quit();
	}

	@Test
	public void testSearchBox() throws InterruptedException {
		String placeholder = driver.findElement(By.id("ex1_value")).getAttribute("placeholder");
		String color = driver.findElement(By.id("ex1_value")).getCssValue("color");
		assertEquals("Search for user...", placeholder);
		assertEquals("rgba(255, 255, 255, 1)", color);
		driver.findElement(By.id("ex1_value")).clear();
		driver.findElement(By.id("ex1_value")).sendKeys("ma");
		Thread.sleep(1000);
		List<WebElement> elements = driver.findElements(By.cssSelector("#ex1_dropdown > .angucomplete-row"));
		for (WebElement element : elements) {
			assertTrue(element.getText().toLowerCase().startsWith("ma"));
		}
	}

	@Test
	public void testButtons() {
		WebElement addEventButton = driver.findElement(By.cssSelector("button.btn.btn__add-event"));
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
		assertEquals("0px", marginTop);
		assertEquals(text, "Add event");
		WebElement addMeetingButton = driver.findElement(By.cssSelector("button.btn.btn__add-meeting"));
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
		assertEquals(text, "Add meeting");
	}

	@Test
	public void testNavigationToEvent() throws InterruptedException {
		driver.findElement(By.cssSelector("button.btn.btn__add-event")).click();
		Thread.sleep(1000);
		String url = driver.getCurrentUrl();
		assertEquals("http://localhost:8000/#/event", url);
	}

	@Test
	public void testNavigationToMeeting() throws InterruptedException {
		driver.findElement(By.cssSelector("button.btn.btn__add-meeting")).click();
		Thread.sleep(1000);
		String url = driver.getCurrentUrl();
		assertEquals("http://localhost:8000/#/meeting", url);
	}

	@Test
	public void testNavigationToRequests() throws InterruptedException {
		driver.findElement(By.xpath("//ng-transclude/i")).click();
		Thread.sleep(1000);
		String url = driver.getCurrentUrl();
		assertEquals("http://localhost:8000/#/meetingRequests", url);
	}

	@Test
	public void testNavigationToNotifications() throws InterruptedException {
		driver.findElement(By.xpath("//li[3]/a/notification-icon/div/div[2]/ng-transclude/i")).click();
		Thread.sleep(1000);
		String url = driver.getCurrentUrl();
		assertEquals("http://localhost:8000/#/connectionNotifications", url);
	}

	@Test
	public void testNavigationToProfile() throws InterruptedException {
		driver.findElement(By.xpath("//a/i")).click();
		Thread.sleep(1000);
		String url = driver.getCurrentUrl();
		assertEquals("http://localhost:8000/#/profile/", url);
	}

	@Test
	public void testHighlightedDay() throws InterruptedException {
		Thread.sleep(1000);
		List<WebElement> elements = driver.findElements(By.className("fc-state-highlight"));
		String date = LocalDate.now().toString();
		for (WebElement element : elements) {
			String text = element.getAttribute("data-date");
			assertEquals(date, text);
		}
		Thread.sleep(1000);
		driver.findElement(By.className("fc-agendaWeek-button")).click();
		Thread.sleep(1000);
		elements = driver.findElements(By.className("fc-state-highlight"));
		for (WebElement element : elements) {
			String text = element.getAttribute("data-date");
			assertEquals(date, text);
		}
		Thread.sleep(1000);
		driver.findElement(By.className("fc-agendaDay-button")).click();
		Thread.sleep(1000);
		elements = driver.findElements(By.className("fc-state-highlight"));
		for (WebElement element : elements) {
			String text = element.getAttribute("data-date");
			assertEquals(date, text);
		}
	}

	@Test
	public void testMonthNavigations() throws InterruptedException {
		Thread.sleep(1000);
		LocalDate date = LocalDate.now();
		String realText = date.getMonth() + " " + date.getYear();
		String text = driver.findElement(By.cssSelector(".fc-center > h2")).getText();
		assertEquals(realText.toLowerCase(), text.toLowerCase());
		Thread.sleep(1000);
		LocalDate nextDate = date.plusMonths(1);
		driver.findElement(By.className("fc-next-button")).click();
		realText = nextDate.getMonth() + " " + nextDate.getYear();
		text = driver.findElement(By.cssSelector(".fc-center > h2")).getText();
		assertEquals(realText.toLowerCase(), text.toLowerCase());
		Thread.sleep(1000);
		LocalDate prevDate = nextDate.minusMonths(1);
		driver.findElement(By.className("fc-prev-button")).click();
		realText = prevDate.getMonth() + " " + prevDate.getYear();
		text = driver.findElement(By.cssSelector(".fc-center > h2")).getText();
		assertEquals(realText.toLowerCase(), text.toLowerCase());
	}

	@Test
	public void testDayNavigations() throws InterruptedException {
		Thread.sleep(1000);
		LocalDate date = LocalDate.now();
		driver.findElement(By.className("fc-agendaDay-button")).click();
		String realText = date.getMonth() + " " + date.getDayOfMonth() + ", " + date.getYear();
		String text = driver.findElement(By.cssSelector(".fc-center > h2")).getText();
		assertEquals(realText.toLowerCase(), text.toLowerCase());
		Thread.sleep(1000);
		LocalDate prevDate = date.minusDays(1);
		driver.findElement(By.className("fc-prev-button")).click();
		realText = prevDate.getMonth() + " " + prevDate.getDayOfMonth() + ", " + prevDate.getYear();
		text = driver.findElement(By.cssSelector(".fc-center > h2")).getText();
		assertEquals(realText.toLowerCase(), text.toLowerCase());
		Thread.sleep(1000);
		LocalDate nextDate = prevDate.plusDays(1);
		driver.findElement(By.className("fc-next-button")).click();
		realText = nextDate.getMonth() + " " + nextDate.getDayOfMonth() + ", " + nextDate.getYear();
		text = driver.findElement(By.cssSelector(".fc-center > h2")).getText();
		assertEquals(realText.toLowerCase(), text.toLowerCase());
	}

	@Test
	public void testTodayButton() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.className("fc-next-button")).click();
		driver.findElement(By.className("fc-next-button")).click();
		driver.findElement(By.className("fc-today-button")).click();
		String text = driver.findElement(By.cssSelector(".fc-center > h2")).getText();
		LocalDate date = LocalDate.now();
		String realText = date.getMonth() + " " + date.getYear();
		assertEquals(realText.toLowerCase(), text.toLowerCase());
		Thread.sleep(1000);
		driver.findElement(By.className("fc-agendaDay-button")).click();
		driver.findElement(By.className("fc-prev-button")).click();
		driver.findElement(By.className("fc-today-button")).click();
		realText = date.getMonth() + " " + date.getDayOfMonth() + ", " + date.getYear();
		text = driver.findElement(By.cssSelector(".fc-center > h2")).getText();
		assertEquals(realText.toLowerCase(), text.toLowerCase());
	}

	@Test
	public void testTodayButtonDisablement() throws InterruptedException {
		Thread.sleep(1000);
		String disabled = driver.findElement(By.className("fc-today-button")).getAttribute("disabled");
		assertEquals("true", disabled);
		driver.findElement(By.className("fc-next-button")).click();
		disabled = driver.findElement(By.className("fc-today-button")).getAttribute("disabled");
		assertNull(disabled);
		driver.findElement(By.className("fc-next-button")).click();
		driver.findElement(By.className("fc-today-button")).click();
		disabled = driver.findElement(By.className("fc-today-button")).getAttribute("disabled");
		assertEquals("true", disabled);
		driver.findElement(By.className("fc-agendaWeek-button")).click();
		driver.findElement(By.className("fc-prev-button")).click();
		disabled = driver.findElement(By.className("fc-today-button")).getAttribute("disabled");
		assertNull(disabled);
	}
}
