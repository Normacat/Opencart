package pageObjects;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PageHomeLogin extends BasePage{
	
	
	WebDriver driver;
	
	
	
	//Constructor
	public PageHomeLogin(WebDriver driver){
		
		super(driver);
		
	}
	
	//Locators
	@FindBy(id = "LearFlex_usuario") WebElement txt_user_loc;
	@FindBy(id = "LearFlex_password") WebElement txt_pwd_loc;
	@FindBy(id = "aceptar") WebElement btnlogin;
	@FindBy(xpath = "//*[@id=\"LearFlex\"]/table/tbody/tr[1]/td/table[1]/tbody/tr[2]/td/table[2]/tbody/tr/td/ul/li") WebElement msgInvalidCredentials;
	@FindBy(xpath = "/html/body/form/table/tbody/tr[1]/td/table[1]/tbody/tr[2]/td/table[2]/tbody/tr/td/ul/li") WebElement msgInvalidCredential;
	@FindBy(xpath = "//*[@id=\"LearFlex\"]/table/tbody/tr[1]/td/table[1]/tbody/tr[2]/td/table[2]/tbody/tr/td/ul/li") WebElement msgOpenSession;
	
	public void setUserName(String username) {
		
		
		txt_user_loc.sendKeys(username);
	}
	
	public void setUserNameWithTab(String username) {
		
		actions.sendKeys(Keys.TAB).sendKeys(username).perform();;
		//actions.keyDown(Keys.TAB).keyUp(Keys.TAB).sendKeys(username).perform();
		
	}
	
	
	public void clearUserName() {
		
		txt_user_loc.clear();
		
	}

		
	public void setPassword(String pwd) {
		
		txt_pwd_loc.sendKeys(pwd);
		
	}
	
	public void setPwdWithTab(String pwd) {
		
		
		actions.keyDown( Keys.TAB).keyUp(Keys.TAB).sendKeys(pwd).perform();
		
		
	}
	
	public void clickLogin() {
		
		btnlogin.click();
		
	}
	
	public void enterLogin() {
		
		actions.sendKeys(Keys.ENTER).perform();
	}
	
	
	public String getMsgInvalidCredentials() {
		
		try {
			return (msgInvalidCredentials.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
		
	}
	
	public String getMsgInvalidCredential() {
		
		try {
			return (msgInvalidCredential.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
		
	}
	
	public void switchAlert(WebDriver driver) {
		
		driver.switchTo().alert().accept();
	}
	
	public String getMsgOpenSession() {
		
		try {
			return (msgOpenSession.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
		
	}
	
	public void navigateBack(WebDriver driver) {
		
		driver.navigate().back();
		
	}
	
	public void navigateForward(WebDriver driver) {
		
		driver.navigate().forward();
		
	}
	
	
	public void navigateRefresh(WebDriver driver) {
		
		driver.navigate().refresh();
	}
	
	
	
}
