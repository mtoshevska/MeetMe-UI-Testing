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

public class TestZoom {

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
	public void testCalendarButtons() throws InterruptedException {
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("sample@example.com");
		driver.findElement(By.xpath("//input[@type='password']")).clear();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("password");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(1000);
		driver.findElement(By.tagName("html")).sendKeys(Keys.CONTROL, Keys.ADD);
		WebElement addEventButton = driver.findElement(By.cssSelector("button.btn.btn__add-event"));
		String marginTop = addEventButton.getCssValue("margin-top");
		assertEquals("0px", marginTop);
		WebElement addMeetingButton = driver.findElement(By.cssSelector("button.btn.btn__add-meeting"));
		marginTop = addMeetingButton.getCssValue("margin-top");
		assertEquals("0px", marginTop);
		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(1000);
	}

	@Test
	public void testDetailsButtons() throws InterruptedException {
		driver.findElement(By.linkText("Login")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@type='text'])[2]")).sendKeys("sample@example.com");
		driver.findElement(By.xpath("//input[@type='password']")).clear();
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("password");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(1000);
		driver.findElement(By.tagName("html")).sendKeys(Keys.CONTROL, Keys.ADD);
		driver.findElement(By.tagName("html")).sendKeys(Keys.CONTROL, Keys.ADD);
		Thread.sleep(10000);
		driver.findElements(By.cssSelector("a.fc-day-grid-event")).get(0).click();
		Thread.sleep(1000);
		double maxWidth = driver.findElement(By.xpath("//div[@ng-show='SelectedEvent']")).getSize().getWidth();
		double paddingLeft = Double.parseDouble(driver.findElement(By.xpath("//div[@ng-show='SelectedEvent']"))
				.getCssValue("padding-left").replace("px", ""));
		double paddingRight = Double.parseDouble(driver.findElement(By.xpath("//div[@ng-show='SelectedEvent']"))
				.getCssValue("padding-right").replace("px", ""));
		WebElement editButton = driver.findElement(By.cssSelector("button.btn.btn-edit__style.btn-large"));
		double editButtonWidth = editButton.getSize().getWidth();
		double editButtonLeftMargin = Double.parseDouble(editButton.getCssValue("margin-left").replace("px", ""));
		double editButtonRightMargin = Double.parseDouble(editButton.getCssValue("margin-right").replace("px", ""));
		double editButtonLeftBorder = Double.parseDouble(editButton.getCssValue("border-left-width").replace("px", ""));
		double editButtonRightBorder = Double
				.parseDouble(editButton.getCssValue("border-right-width").replace("px", ""));
		double editButtonSize = editButtonWidth + editButtonLeftMargin + editButtonRightMargin + editButtonLeftBorder
				+ editButtonRightBorder;
		WebElement removeButton = driver.findElement(By.cssSelector("button.btn.btn-remove__style.btn-large"));
		double removeButtonWidth = removeButton.getSize().getWidth();
		double removeButtonLeftMargin = Double.parseDouble(removeButton.getCssValue("margin-left").replace("px", ""));
		double removeButtonRightMargin = Double.parseDouble(removeButton.getCssValue("margin-right").replace("px", ""));
		double removeButtonLeftBorder = Double
				.parseDouble(removeButton.getCssValue("border-left-width").replace("px", ""));
		double removeButtonRightBorder = Double
				.parseDouble(removeButton.getCssValue("border-right-width").replace("px", ""));
		double removeButtonSize = removeButtonWidth + removeButtonLeftMargin + removeButtonRightMargin
				+ removeButtonLeftBorder + removeButtonRightBorder;
		WebElement meetingDetailsButton = driver.findElement(By.xpath("//button[@class='btn btn-large']"));
		double meetingDetailsButtonWidth = meetingDetailsButton.getSize().getWidth();
		double meetingDetailsButtonLeftMargin = Double
				.parseDouble(meetingDetailsButton.getCssValue("margin-left").replace("px", ""));
		double meetingDetailsButtonRightMargin = Double
				.parseDouble(meetingDetailsButton.getCssValue("margin-right").replace("px", ""));
		double meetingDetailsButtonLeftBorder = Double
				.parseDouble(meetingDetailsButton.getCssValue("border-left-width").replace("px", ""));
		double meetingDetailsButtonRightBorder = Double
				.parseDouble(meetingDetailsButton.getCssValue("border-right-width").replace("px", ""));
		double meetingDetailsButtonSize = meetingDetailsButtonWidth + meetingDetailsButtonLeftMargin
				+ meetingDetailsButtonRightMargin + meetingDetailsButtonLeftBorder + meetingDetailsButtonRightBorder;
		assertTrue(maxWidth > editButtonSize + removeButtonSize + paddingLeft + paddingRight);
		assertTrue(maxWidth > meetingDetailsButtonSize + paddingLeft + paddingRight);
		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(1000);
	}
}
