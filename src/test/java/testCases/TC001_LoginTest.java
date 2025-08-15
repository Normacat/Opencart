package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.PageHomeLogin;
import testBase.BaseClass;


public class TC001_LoginTest extends BaseClass {
	
	
	@Test(groups = {"Sanity", "Master"})
	public void login() throws InterruptedException {
		
		logger.info("*** Starting TC001_LoginTest ***");
		
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
		String pagetitle= hp.pageTitle();
		//Assert.assertEquals(pagetitle, "AON FLEX");
		logger.info("Validating expected page title");
		
		if(pagetitle.equals("BANDA SIETE MENOS ELECTRICAL S XXI")){
			
			
			hp.clickLogOut();
			Assert.assertTrue(true);
		} else {
			
			logger.error("Test failed");
			logger.debug("Debugs logs");
			Assert.assertTrue(false);
		}
//		Thread.sleep(3000);
//		hp.switchFrame(driver);
//		hp.clickLogOut();
			
		}
		catch (Exception e) {
			
			Assert.fail();
			
		}
		
		logger.info(" *** Finished TC001_LoginTest *** ");
	}
	
	
	
	
}
