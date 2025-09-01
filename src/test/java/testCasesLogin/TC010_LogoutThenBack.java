package testCasesLogin;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.PageHomeLogin;
import testBase.BaseClass;

public class TC010_LogoutThenBack extends BaseClass{
	
	@Test(groups = {"Master"})
	public void login() throws InterruptedException {
		
		logger.info("*** Starting TC010_LogoutThenBack ***");
		
		//HomePageLogin
		try {
		
		PageHomeLogin phl = new PageHomeLogin(driver);
		phl.setUserName(p.getProperty("user"));;
		phl.setPassword(p.getProperty("pwd"));
		logger.info("Providing customer details");
		phl.clickLogin();
		logger.info("clicking to login");
		
		
		HomePage hp = new HomePage(driver);
		hp.switchFrame(driver);
		String pageusername= hp.pageUserName();
		//Assert.assertEquals(pagetitle, "AON FLEX");
		logger.info("Validating expected page title");
		
		if(pageusername.equals("BANDA SIETE MENOS ELECTRICAL S XXI")){
			
			
			hp.clickLogOut();
			Thread.sleep(3000);
			phl.navigateBack(driver);
			
			phl.navigateRefresh(driver);
			
			if(pageusername.equals("BANDA SIETE MENOS ELECTRICAL S XXI")) {
				
				logger.error("Test failed");
				logger.debug("debug logs");
				Assert.assertTrue(true);
				
				hp.switchFrame(driver);
				hp.clickLogOut();
				
			} else {
				
				Assert.assertTrue(false);
			}
			
			
		} else {
			
			logger.error("Test failed");
			logger.debug("Debugs logs");
			Assert.assertTrue(false);
		}

		}
		catch (Exception e) {
			
			Assert.fail();
			
		}
		
		logger.info(" *** Finished TC010_LogoutThenBack *** ");
	}
	
	

}
