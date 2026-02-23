package utilities;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;

import testBase.BaseClass;

public class DataProviders {
	WebDriver driver;
	BaseClass bc;
	
	//DataProvider 1
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException {
		
		String path = ".\\TestData\\OpenCart_LoginData.xlsx"; //taking xl file from testData
		
		ExcelUtility xlutil = new ExcelUtility(path); // creating an object from ExcelUtility class
		
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcolumns = xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][] = new String[totalrows][totalcolumns];
		
		for(int i=1; i<=totalrows; i++ ) {
			
			for(int j=0; j<totalcolumns; j++) {
				
				logindata[i-1][j] = xlutil.getCellData("Sheet1", i, j);
			}
		}
		
		return logindata; // returning two dimension array
		
	}
	
	//DataProvider 2
	@DataProvider(name="setLoginData")
	public String[][] setData() throws IOException {
		
		String path = ".\\TestData\\OpenCart_LoginData1.xlsx"; //taking xl file from testData
		
		ExcelUtility xlutil = new ExcelUtility(path); // creating an object from ExcelUtility class
		
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcolumns = xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][] = new String[totalrows][totalcolumns];
		
		/*
		for(int i=1; i<=totalrows; i++) {
			
			String data = RandomStringUtils.randomAlphabetic(5);
			xlutil.setCellData("Sheet1", i, 0, data);
			xlutil.setCellData("Sheet1", i, 1, data);
		}
		*/
		
		for(int i=1; i<=totalrows; i++) {
			
			String data = RandomStringUtils.randomAlphabetic(5);
			xlutil.setCellData("Sheet1", i, 0, data);
			xlutil.setCellData("Sheet1", i, 1, data);
			for (int j=0; j<totalcolumns; j++){
				
				logindata[i-1][j] = xlutil.getCellData("Sheet1", i, j);
				
			}
			
			
		}
		
			return logindata;
	}
	 
	
	//DataProvider 3

}
