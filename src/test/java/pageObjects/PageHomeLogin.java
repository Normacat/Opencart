package pageObjects;

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
	
	
	
	
	public void setUserName(String username) {
		
		
		txt_user_loc.sendKeys(username);
	}
	
	public void clearUserName() {
		
		txt_user_loc.clear();
		
	}

		
	public void setPassword(String pwd) {
		
		txt_pwd_loc.sendKeys(pwd);
		
	}
	
	public void clickLogin() {
		
		btnlogin.click();
		
	}
	
	
	public String getMsgInvalidCredentials() {
		
		try {
			return (msgInvalidCredentials.getText());
		} catch (Exception e) {
			return (e.getMessage());
		}
		
		
	}
	
}
