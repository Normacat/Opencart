package testCasesLogin;


import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import pageObjects.PageHomeLogin;
import testBase.BaseClass;

public class TC002_LoginTestWithInvalidCredentials extends BaseClass{
	
	
	
	@Test(groups = {"Master", "Regression"})
	public void loginWithInvalidCreddentials() {
		
		logger.info("TC002_LoginTestWithInvalidCredentials starting...");
		
		try {
		PageHomeLogin phl = new PageHomeLogin(driver);
		
		phl.setUserName(setRandomeString());
		phl.setPassword(setRandomeAlphabeticNumber());
		logger.info("Providing customer details");
		
		phl.clickLogin();
		logger.info("clicking to login");
		
		String msgInvalidCredentials = phl.getMsgInvalidCredentials();
		logger.info("Validating expected message");
		
		if(msgInvalidCredentials.equals("El usuario o el password no son correctos, verifique sus datos.")) {
		Assert.assertTrue(true);
		} else {
			
			logger.info("logger info");
			logger.debug("Logger debug");
			Assert.assertTrue(false);
			
		}
		
		}catch(Exception e) {
			
			Assert.fail();
			
		}
		
		logger.info("TC002_LoginTestWithInvalidCredentials finished...");
	}

}
