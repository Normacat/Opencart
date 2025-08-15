package testCases;

import org.apache.commons.codec.language.bm.Rule.PhonemeList;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.PageHomeLogin;
import testBase.BaseClass;
import utilities.DataProviders;

/*
 Data is valid - Login is Succes - Test passed - Logout
 Data is valid - Login isn't succes -Test fail
 
 Data is invalid - Login is succes - Test fail - Logout
 Data is invalid - Login isn't succes - Test passed
 */

public class TC004_LoginDDT extends BaseClass {
	
	
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups ="Sanity")
	public void verify_LoginDDT(String user, String pwd, String exp) {
		
	logger.info("****Starting the test TC004_LoginDDT *****");
	
	try{
	//Home Page Login
	PageHomeLogin phl = new PageHomeLogin(driver);
	phl.setUserName(user);
	phl.setPassword(pwd);
	logger.info("Providing customer details");
	phl.clickLogin();
	logger.info("clicking to login");
	
	//MyAccount
	HomePage hp = new HomePage(driver);
	hp.switchFrame(driver);
	boolean userName = hp.isMyAccountPageExist();
	
	
	if(exp.equalsIgnoreCase("valid")) {
		if (userName == true) {
			
			hp.clickLogOut();
			Assert.assertTrue(true);
		}
		else {
			
			
			Assert.assertTrue(false);
			
		}
		
	}
	
	
	if(exp.equalsIgnoreCase("invalid")) {
		if(userName == true) {
			
			hp.clickLogOut();
			Assert.assertTrue(false);
			
		}
		else {
			
			Assert.assertTrue(true);
			
		}
		
	}
	
	
	}catch (Exception e) {
		
		PageHomeLogin phl = new PageHomeLogin(driver);
		phl.clearUserName();
		Assert.fail();
		
	}
		
		logger.info("****Finished the test TC004_LoginDDT *****");
	}
	
	

}
