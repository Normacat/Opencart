package testCasesLogin;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.PageHomeLogin;
import testBase.BaseClass;

public class TC007_loginWithoutAnyCredentials extends BaseClass{
	
	@Test(groups = {"Master", "Regression"})
	public void loginWithoutAnyCredentials() {
		
		logger.info(" **** Starting TC007_loginWithoutAnyCredentials");
		
		
		try {
		PageHomeLogin phl = new PageHomeLogin(driver);
		phl.setPassword(p.getProperty("pwd"));
		
		logger.info("Providing customer details");
		
		phl.clickLogin();
		logger.info("Clicking to login");
		
		String msg = phl.getMsgInvalidCredential();
		
		if(msg.equals("El usuario es requerido.")) {
			
			Assert.assertTrue(true);
			
		} else {
			
			logger.error("Test failed");
			logger.debug("debug error");
			Assert.assertTrue(false);
			
		}
		
		}catch (Exception e) {
			Assert.fail();
		}
		
		logger.info("***** Finished TC007_loginWithoutAnyCredentials *******");
	}

}
