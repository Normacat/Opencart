package testCasesLogin;


import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.PageHomeLogin;
import testBase.BaseClass;

public class TC008_loginWithTabs extends BaseClass{
	
	
	@Test(groups = {"Master"})
	public void loginWithTabs() {
		
		logger.info("****** Starting TC008_loginWithTabs *****");
		
		try {
		
		PageHomeLogin phl = new PageHomeLogin(getDriver());
		
		
		phl.setUserNameWithTab(p.getProperty("user"));
		phl.setPwdWithTab(p.getProperty("pwd"));
		
		logger.info("Providing customer details");
		
		phl.enterLogin();;
		logger.info("Clicking to login");
		
		HomePage hp = new HomePage(getDriver());
		hp.switchFrame(getDriver());
		String userName = hp.pageUserName();
		
		if(userName.equals("BANDA SIETE MENOS ELECTRICAL S XXI")) {
			
			hp.clickLogOut();
			Assert.assertTrue(true);
			
		} else {
			
			logger.error("Test failed");
			logger.debug("debug error");
			Assert.assertTrue(false);
			
		}
		
		}catch (Exception e) {
			Assert.fail();
		}
		
		
		logger.info("****** Finished TC008_loginWithTabs *****");
		
	}
	

}
