package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.PageHomeLogin;
import testBase.BaseClass;

public class TC003_ValidatingUserProfile extends BaseClass{

	
	@Test(groups = "Regression")
	public void ValidatingUserProfileName() {
		
		logger.info("*** TC003_ValidatingUserProfile ***");
		
		//HomePageLogin
		
		try {
		PageHomeLogin phl = new PageHomeLogin(driver);
		
		phl.setUserName(p.getProperty("user"));;
		phl.setPassword(p.getProperty("pwd"));
		logger.info("Providing customer details");
		phl.clickLogin();
		logger.info("clicking to login");
		
		//MyAccount
		HomePage hp = new HomePage(driver);
		hp.switchFrame(driver);
		boolean userName = hp.isMyAccountPageExist();
		Assert.assertTrue(userName);
		
		} catch (Exception e) {
			
			Assert.fail();
		}
		
		logger.info("*** Finished TC003_ValidatingUserProfile ***");
	}
	
	
}
