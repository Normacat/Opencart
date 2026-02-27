package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class BaseClass {

	public Logger logger;
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	public Properties p;

	
	//@Given("lounch browser")
	@Parameters({ "os", "browser" })
	@BeforeClass(groups = { "Sanity", "Regression", "Master" })
	public void lounch_browser(String os, String br) throws IOException {
		
		WebDriver localdriver;

		FileReader file = new FileReader("./src//test//resources//config.properties");

		p = new Properties();
		p.load(file);

		logger = LogManager.getLogger(this.getClass());

		if (p.getProperty("execution_env").equalsIgnoreCase("remote")) {

			DesiredCapabilities cap = new DesiredCapabilities();

			if (os.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN10);
			} else if (os.equalsIgnoreCase("mac")) {
				cap.setPlatform(Platform.MAC);
			} else if (os.equalsIgnoreCase("linux")) {
				cap.setPlatform(Platform.LINUX);
			}

			else {
				System.out.println("No matching os");
				return; // to exit the execution
			}

			switch (br.toLowerCase()) {
			case "chrome":
				cap.setBrowserName("chrome");
				break;
			case "edge":
				cap.setBrowserName("MicrosoftEdge");
				break;
			case "firefox":
				cap.setBrowserName("firefox");
				break;
			default:
				System.out.println("No matching browser");
				return; // To exit the execution

			}

			localdriver = new RemoteWebDriver(new URL("http://localhost:4445/wd/hub"), cap);
			driver.set(localdriver);

		}

		if (p.getProperty("execution_env").equalsIgnoreCase("local")) {

			switch (br.toLowerCase()) {
			case "chrome":
				localdriver = new ChromeDriver();
				driver.set(localdriver);
				break;
			case "edge":
				localdriver = new EdgeDriver();
				driver.set(localdriver);
				break;
			case "firefox":
				localdriver = new FirefoxDriver();
				driver.set(localdriver);
				break;

			default:
				System.out.println("Invalid browser name");
				return;
			}

		}

		// driver = new ChromeDriver();
		
		driver.get().manage().deleteAllCookies();
		driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get().get(p.getProperty("url"));
		driver.get().manage().window().maximize();
		
	}
	
	public WebDriver getDriver() {
		
		return driver.get();
	}

	
	 //@And("tearDown")
	 @AfterClass(groups = { "Sanity", "Regression", "Master" }) 
	 public void tearDown() { 
		 driver.get().quit();
		 
	
	  }

	public String setRandomeString() {

		String randomeString = RandomStringUtils.randomAlphabetic(5);
		return randomeString;

	}

	public String setRandomeNumber() {

		String randomeNumber = RandomStringUtils.randomNumeric(5);
		return randomeNumber;

	}

	public String setRandomeAlphabeticNumber() {
		String randomeString = RandomStringUtils.randomAlphabetic(4);
		String randomeNumber = RandomStringUtils.randomNumeric(4);
		return (randomeString + randomeNumber);

	}

	public String captureScreen(String tname) throws IOException {

		
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		
		TakesScreenshot takesScreenshot = (TakesScreenshot) (getDriver());
		
		
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\screenshoots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;
	}

}
