package testCasesLogin;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.PageHomeLogin;
import testBase.BaseClass;

public class TC009_LoginThenBackBotton extends BaseClass{
	
	@Test(groups = { "Master"})
	public void login() throws InterruptedException {
		
		logger.info("*** Starting TC009_LoginThenBackBotton ***");
		
		//HomePageLogin
		try {
		
		PageHomeLogin phl = new PageHomeLogin(getDriver());
		
		phl.setUserName(p.getProperty("user"));;
		phl.setPassword(p.getProperty("pwd"));
		logger.info("Providing customer details");
		phl.clickLogin();
		logger.info("clicking to login");
		
		
		
		HomePage hp = new HomePage(getDriver());
		hp.switchFrame(getDriver());
		String pageusername= hp.pageUserName();
		
		//Assert.assertEquals(pagetitle, "AON FLEX");
		logger.info("Validating expected page title");
		
		if(pageusername.equals("BANDA SIETE MENOS ELECTRICAL S XXI")){
			
			//driver.navigate().back();
			hp.navigateBack(getDriver());
			Thread.sleep(3000);
			phl.switchAlert(getDriver());
			
			
			String msgOpenSession = phl.getMsgOpenSession();
			
			if(msgOpenSession.equals("Se ha detectado una sesión abierta con anterioridad. Por seguridad de tu información, es necesario cerrar la ventana anterior antes de ingresar nuevamente al sistema. Gracias.")) {
				
				Assert.assertTrue(true);
				phl.navigateForward(getDriver());
				phl.navigateRefresh(getDriver());
				hp.switchFrame(getDriver());
				hp.clickLogOut();
				
			} else {
				
				logger.error("Test failed");
				logger.debug("Debugs logs");
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
		
		logger.info(" *** Finished TC009_LoginThenBackBotton*** ");

	}
}
