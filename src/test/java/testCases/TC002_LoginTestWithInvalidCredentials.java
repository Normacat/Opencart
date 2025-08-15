package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.PageHomeLogin;
import testBase.BaseClass;

public class TC002_LoginTestWithInvalidCredentials extends BaseClass{
	
	@Test(groups = {"Master", "Regression"})
	public void loginWithInvalidCreddentials() {
		
		PageHomeLogin phl = new PageHomeLogin(driver);
		
		phl.setUserName(setRandomeString());
		phl.setPassword(setRandomeAlphabeticNumber());
		phl.clickLogin();
		
		 String msgInvalidCredentials = phl.getMsgInvalidCredentials();
		
		Assert.assertEquals(msgInvalidCredentials, "El usuario o el password no son correctos, verifique sus datos.");
			
	}

}
