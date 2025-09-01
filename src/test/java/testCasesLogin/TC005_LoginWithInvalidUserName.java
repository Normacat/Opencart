package testCasesLogin;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.PageHomeLogin;
import testBase.BaseClass;

public class TC005_LoginWithInvalidUserName extends BaseClass{
	
	
	@Test(groups = {"Master", "Regression"})
	public void loginWithInvalidUserName() throws IOException{
		
		logger.info("***** Starting TC005_LoginWithInvalidUserName ******");
		
		try {
			
		//Login Page
		PageHomeLogin phl = new PageHomeLogin(driver);
		phl.setUserName(setRandomeString());
		phl.setPassword(p.getProperty("pwd"));
		logger.info("Providing customer details");
		
		phl.clickLogin();
		logger.info("clicking to login");
		
		
		String msg = phl.getMsgInvalidCredentials();
		
		if(msg.equals("El usuario o el password no son correctos, verifique sus datos.")) {
			
			
			Assert.assertTrue(true);
			
		} else {
			logger.error("Test failed");
			logger.debug("Debugs logs");
			Assert.assertTrue(false);
			
		}
		
		}catch (Exception e) {
			Assert.fail();
		}
		
		logger.info("****** Finished TC005_LoginWithInvalidUserName ******");
		
		
	}
	
}
