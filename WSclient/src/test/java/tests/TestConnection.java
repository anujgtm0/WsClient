package tests;
import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import testpackage.BaseClass;
import testpackage.SimulatorClient;
import utilities.Constants;
import utilities.ExcelUtility_SingleDataSet;
import utilities.Write;

public class TestConnection extends BaseClass{
	//private WebDriver driver;
public String status=null;
public int DataSet = -1;
public int rownum;
boolean testskip=false;
ITestResult result;
String Classname=this.getClass().getName();
boolean teststatus=true;

Write wr= new Write();
	@BeforeClass
	public void setUp() throws Exception {
		
		
		// Tell the code about the location of Excel file
		utilities.ExcelUtility_SingleDataSet.setExcelFile(
		utilities.Constants.File_Path + utilities.Constants.File_Name, "Configurations");
		 Classname=this.getClass().getName();
		 rownum=utilities.ExcelUtility_SingleDataSet.getDateCellData1(Classname);
		 status = utilities.ExcelUtility_SingleDataSet.getCellData(rownum, 2);
		// Set the test result in the Excel file
		if (status.equalsIgnoreCase("No")) {
		utilities.ExcelUtility_SingleDataSet.setCellData("SKIP", rownum, 3);
		testskip=true;
		
		
		}
		
		
	}

	

	@Test(dataProvider="Readconfigdata",dataProviderClass =utilities.ExcelUtility_SingleDataSet.class)
	public void testconnection(String hostname,String port,String Endpoint,String Status,String ExpectedResult,String ActualResult,String Res) throws Exception
	{
		 DataSet++;
		 if(status.equalsIgnoreCase("No"))
		 {
			 wr.WriteResult("SKIP", DataSet+1, "Connection","Result"); 
			 throw new SkipException("Skipping tests because resource was not available.");
		 }
		   		 
		 else if (status.equalsIgnoreCase("Yes")) 
		 {
			if (Status.equalsIgnoreCase("No"))
			{
				
				wr.WriteResult("SKIP", DataSet+1, "Connection","Result");
			}
			
			else if (Status.equalsIgnoreCase("Yes"))
			{
		
	     SimulatorClient Sc=new SimulatorClient();
	     Sc.initiateclient(hostname, port, Endpoint);
		 ActualResult=driver.findElement(By.id("incomingMsgOutput")).getAttribute("value");
		 wr.WriteResult(ActualResult, DataSet+1, "Connection","Actual Result");
		 
		//driver.findElement(By.id("btnDisconnect")).click();
	    Thread.sleep(1500);
	   // ActualResult=driver.findElement(By.id("incomingMsgOutput")).getAttribute("value");
		
	   
		   Thread.sleep(1500);
		   if(ActualResult.equalsIgnoreCase(ExpectedResult))
		   {
			   //teststatus=true;
		   //assertEquals(ActualResult, ExpectedResult);
	    wr.WriteResult("PASS", DataSet+1, "Connection", "Result");
			}
		   else {
			   wr.WriteResult("FAIL", DataSet+1, "Connection","Result");
			   teststatus=false;
		   }
		   
		   Assert.assertEquals(ActualResult, ExpectedResult);
		  
			}
		}
		
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		if(teststatus==false) {
			utilities.ExcelUtility_SingleDataSet.setExcelFile(utilities.Constants.File_Path + utilities.Constants.File_Name, "Configurations");
			utilities.ExcelUtility_SingleDataSet.setCellData("FAIL", rownum, 3);
			
		}
		else if(status.equalsIgnoreCase("NO")) {
			utilities.ExcelUtility_SingleDataSet.setExcelFile(
					utilities.Constants.File_Path + utilities.Constants.File_Name, "Configurations");
					utilities.ExcelUtility_SingleDataSet.setCellData("SKIP", rownum, 3);
			
		}
		else {
			utilities.ExcelUtility_SingleDataSet.setExcelFile(
					utilities.Constants.File_Path + utilities.Constants.File_Name, "Configurations");
					utilities.ExcelUtility_SingleDataSet.setCellData("PASS", rownum, 3);
		}
	   // driver.quit();
	}
}
