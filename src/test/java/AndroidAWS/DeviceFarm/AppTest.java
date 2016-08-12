package AndroidAWS.DeviceFarm;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class AppTest {
	private AndroidDriver driver;

	@BeforeSuite
	public void setUp() throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "device");
		caps.setCapability("app", "/Users/maharshishah/Desktop/app-debug.apk");
		caps.setCapability("appPackage", "mobiquity.devicefarm");
		caps.setCapability("appActivity", "MainActivity");
		caps.setCapability("platformName", "Android");
		caps.setCapability("version", "6.0");
		driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), caps);
	}

	@Test
	public void lifeStyle() {
		List<WebElement> digits = driver.findElements(By.className("android.widget.ImageView"));
		digits.get(0).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		takeScreenshot(driver, "screen");
	}

	@AfterSuite
	public void tearDown() {
		driver.quit();
	}

	public boolean takeScreenshot(WebDriver driver, final String name) {
		String screenshotDirectory = System.getProperty(
				"appium.screenshots.dir",
				System.getProperty("java.io.tmpdir", ""));
		File screenshot = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		return screenshot.renameTo(new File(screenshotDirectory, String.format(
				"%s.png", name)));
	}

}
