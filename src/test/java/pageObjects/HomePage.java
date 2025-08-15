package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	WebDriver driver;
	

	public HomePage(WebDriver driver) {
		super(driver);
		
	}
	
	//Locators 
	@FindBy(xpath = "/html/head/title") WebElement pagetitle;
	@FindBy(xpath = "/html/frameset/frame[1]") WebElement frame;
	
	@FindBy(id = "salirId") WebElement lblLogout;
	@FindBy(xpath = "/html/body/div[1]/div/font/strong/font") WebElement profileName;
	
	public String pageTitle() {
		
		return (profileName.getText());
		
	}
	
	public void switchFrame(WebDriver driver) {
		
		driver.switchTo().frame(frame);
		
	}

	public void clickLogOut() {
		

		lblLogout.click();
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].click()", lblLogout);

		//WebElement salir = driver.findElement(By.xpath("//a[starts-with(@id='salirId'])"));
		
	}
	
	public boolean isMyAccountPageExist() {
		
		try {
		return (profileName.isDisplayed());
		} catch (Exception e) {
			return(false);
			
		}
	}
	
	
}
