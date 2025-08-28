package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.PageHomeLogin;
import testBase.BaseClass;

public class TC006_LoginWithInvalidPassword extends BaseClass{

	
	@Test(groups = {"Master", "Regression"})
	public void loginWithInvalidPassword() {
		
		logger.info(" **** Starting TC006_LoginWithInvalidPassword *****");
		
		try {
			
		
		PageHomeLogin hpl = new PageHomeLogin(driver);
		hpl.setUserName(p.getProperty("user"));
		hpl.setPassword(setRandomeAlphabeticNumber());
		logger.info("Providing customer details");
		
		hpl.clickLogin();
		logger.info("Clicking to login");
		
		String pwd = hpl.getMsgInvalidCredentials();
		
		if(pwd.equals("El usuario o el password no son correctos, verifique sus datos.")) {
			
			Assert.assertTrue(true);
		} else {
			
			logger.error("Test failed");
			logger.debug("debug logs");
			Assert.assertTrue(false);
			
		}
		
		}catch (Exception e) {
			Assert.fail();
		}
		
		logger.info(" *****Finished TC006_LoginWithInvalidPassword ******");
		
	}
	
}
