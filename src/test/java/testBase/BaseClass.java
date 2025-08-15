package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.compress.harmony.pack200.NewAttribute;
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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;



public class BaseClass {
	
	public Logger logger;
	public WebDriver driver;
	public Properties p;
	
	@Parameters({"os", "browser"})
	@BeforeClass(groups = {"Sanity", "Regression", "Master"})
	public void lounchBrowser(String os, String br) throws IOException {
		
		
		FileReader file = new FileReader("./src//test//resources//config.properties");
		
		p = new Properties();
		p.load(file);
		
		logger = LogManager.getLogger(this.getClass());
		
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
			DesiredCapabilities cap = new DesiredCapabilities();
			
			if(os.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN10);
			} else if(os.equalsIgnoreCase("mac")) {
				cap.setPlatform(Platform.MAC);
			} else if(os.equalsIgnoreCase("linux")) {
				cap.setPlatform(Platform.LINUX);
			}
			
			else {
				System.out.println("No matching os");
				return;
			}
			
			
			switch (br.toLowerCase()) {
			case "chrome": cap.setBrowserName("chrome");
			break;
			case "edge": cap.setBrowserName("MicrosoftEdge");
			break;
			case "firefox": cap.setBrowserName("firefox");
			break;
			default: System.out.println("No matching browser");
				return;
						
			}
			
			driver = new RemoteWebDriver(new URL ("http://192.168.1.93:4444/wd/hub"), cap);
			
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
		
		switch (br.toLowerCase()) {
		case "chrome": driver = new ChromeDriver();
			break;
		case "edge" : driver = new EdgeDriver();
			break;
		case "firefox" : driver = new FirefoxDriver();
			break;

		default: System.out.println("Invalid browser name");
			return;
			}
		
		}

		//driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("url"));
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups = {"Sanity", "Regression", "Master"})
	public void tearDown() {
		driver.quit();
		
	}
	
	
	public String setRandomeString() {
		
		String randomeString = RandomStringUtils.randomAlphabetic(5);
		return randomeString;
		
	}
	
	public String setRandomeNumber() {
		
		String randomeNumber =RandomStringUtils.randomNumeric(5);
		return randomeNumber;
		
	}
	
	public String setRandomeAlphabeticNumber() {
		String randomeString = RandomStringUtils.randomAlphabetic(4);
		String randomeNumber =RandomStringUtils.randomNumeric(4);
		return (randomeString+randomeNumber);
		
	}
	
	public String captureScreen(String tname) throws IOException {
		
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir") + "\\screenshoots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
	}
	

}
