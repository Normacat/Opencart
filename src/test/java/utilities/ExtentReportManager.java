 package utilities;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceBaseResolver;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener{
	
	public ExtentSparkReporter sparkReporter; //UI of the extent report
	public ExtentReports extent; // porpulate common info on the report
	public ExtentTest test; //create tests cases entries and update status of the test methods
	
	String repName;
	
	public void onStart(ITestContext testContext) { //will execute only once	
		
		//timestamp store in 3 variables
		/* 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt = new Date();
		String currentDate = sdf.format(dt);
		*/
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //Time stamp store in 1 variable
		repName = "Test-Report-" + timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName ); //Specify the location of the report
		
		sparkReporter.config().setDocumentTitle("Automation report"); //Title of 
		sparkReporter.config().setReportName("Functional Testing"); //Name of the report
		sparkReporter.config().setTheme(Theme.DARK); //Theme of the report
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Aon");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("SubModule", "Customers");
		extent.setSystemInfo("UserName", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os = testContext.getCurrentXmlTest().getParameter("os");//get the os parameter into the report
		extent.setSystemInfo("Operating System", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser"); // get the browser parameter into the report
		extent.setSystemInfo("Browser", browser);

		List <String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups(); // get the list of the groups
		if(!includedGroups.isEmpty()) { //if the included groups is not empty then
			extent.setSystemInfo("Groups", includedGroups.toString()); //write the groups into the report
		}
		
	  }
	
		public void onTestSuccess(ITestResult result) {
			
			test = extent.createTest(result.getTestClass().getName());//Create a new entry of the result with getTestClass, we're getting the name
			test.assignCategory(result.getMethod().getGroups()); //To display groups in the report
			test.log(Status.PASS, result.getName() + " got succesfully executed"); 
			
		}
		
		public void onTestFailure(ITestResult result) {
		    test = extent.createTest(result.getTestClass().getName());
		    test.assignCategory(result.getMethod().getGroups());
		    
		    test.log(Status.FAIL, result.getName() + " got failed");
		    test.log(Status.INFO, result.getThrowable().getMessage());
		    
		    
		    try {// if the screenshoot is not properly taken or is not available we will get FileNotFoundException 
		    	String imgPath = new BaseClass().captureScreen(result.getName()); //create an object of base class but avoid to store in a variable
		    	//and from that object call captureScreen method, and that method is expecting a parameter the name in a String format, so we need to pass the name which is the result getName
		    	test.addScreenCaptureFromPath(imgPath); //attach the screenshot in the report
		    	
		    } catch (IOException e1) {
				e1.printStackTrace();
			}
		    
		  }
		
		public void onTestSkipped(ITestResult result) {
		    test = extent.createTest(result.getTestClass().getName());
		    test.assignCategory(result.getMethod().getGroups());
		    
		    test.log(Status.SKIP, result.getName() +"got skipped");
		    test.log(Status.INFO, result.getThrowable().getMessage());
		    
		  }
		
		public void onFinish(ITestContext testContext) {
		    
			extent.flush(); //Consolidate all the information from the report and finally generate it
			String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
			File extenReport = new File(pathOfExtentReport);
			
			try {
				Desktop.getDesktop().browse(extenReport.toURI()); // Open the report automatically when finished the execution
				
			}catch (IOException e) {
				e.printStackTrace();
			}
			
			/*
			 * try { URL url = new URL("file:///" + System.getProperty("user.dir")+
			 * "\\reports\\" + repName); //URL format
			 * 
			 * //Create the email message ImageHtmlEmail email = new ImageHtmlEmail();
			 * email.setDataSourceResolver(new DataSourceUrlResolver(url));
			 * email.setHostName("smtp.googleemail.com"); email.setSmtpPort(465); //works only for gmail company
			 * email.setAuthenticator(new
			 * DefaultAuthenticator("normacuevasmendoza@gmail.com", "password"));
			 * email.setSSLOnConnect(true); email.setFrom("normacuevasmendoza@gmail.com");
			 * //sender email.setSubject("Test Results");
			 * email.setMsg("Please find attached report");
			 * email.addTo("example@gmail.com");//receiver email.attach(url,
			 * "extent report", "please check report"); email.send(); // send the email
			 * 
			 * 
			 * 
			 * }catch (Exception e) {
			 * 	e.printStackTrace();
			 * }
			 */
			
			
		  }
			

}
