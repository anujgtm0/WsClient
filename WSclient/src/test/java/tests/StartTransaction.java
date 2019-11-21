package tests;
import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import testpackage.BaseClass;

import testpackage.IdTagInfo;
import testpackage.SimulatorClient;
import testpackage.StartResponse;
import utilities.Constants;
import utilities.ExcelUtility_SingleDataSet;
import utilities.Write;
import utilities.testconnections;

public class StartTransaction extends BaseClass{
	//private WebDriver driver;
public String status=null;
public int DataSet = -1;
public int rownum;
boolean testskip=false;
ITestResult result;
String Classname=this.getClass().getName();
boolean teststatus=true;
String evseid=Constants.EVSEid;
String evsestatus;
String Transid;

Write wr= new Write();
testconnections tc= new testconnections();
	@BeforeClass
	public void setUp() throws Exception {
		
		
		// Tell the code about the location of Excel file
		ExcelUtility_SingleDataSet.setExcelFile(
		Constants.File_Path + Constants.File_Name, "Configurations");
		 Classname=this.getClass().getName();
		 rownum=ExcelUtility_SingleDataSet.getDateCellData1(Classname);
		 status = ExcelUtility_SingleDataSet.getCellData(rownum, 2);
		// Set the test result in the Excel file
		if (status.equalsIgnoreCase("No")) {
		ExcelUtility_SingleDataSet.setCellData("SKIP", rownum, 3);
		testskip=true;
		
		
		}
		
		
	}

	

	@Test(dataProvider="Starttrans",dataProviderClass =ExcelUtility_SingleDataSet.class)
	public void testconnection(String Request,String Response,String Status,String ExpectedResult,String ActualResult,String Res) throws Exception
	{
		 DataSet++;
		 if(status.equalsIgnoreCase("No"))
		 {
			 wr.WriteResult("SKIP", DataSet+1, "StartTransaction","Result"); 
			 throw new SkipException("Skipping tests because resource was not available.");
		 }
		   		 
		 else if (status.equalsIgnoreCase("Yes")) 
		 {
			if (Status.equalsIgnoreCase("No"))
			{
				
				wr.WriteResult("SKIP", DataSet+1, "StartTransaction","Result");
			}
			
			else if (Status.equalsIgnoreCase("Yes"))
			{
				driver.findElement(By.id("message")).clear();
	     SimulatorClient Sc=new SimulatorClient();
	   Sc.BootNotification(Request);
	   
	   String bootresponse=driver.findElement(By.id("incomingMsgOutput")).getAttribute("value");
		String payloadString = bootresponse.substring(bootresponse.indexOf("{"),bootresponse.length()-2);
		
		 wr.WriteResult(payloadString, DataSet+1, "StartTransaction","Response");
		 StartResponse transid = new Gson().fromJson(payloadString, StartResponse.class);
		 Transid=transid.getTransactionId().toString();
		 ActualResult=transid.getIdTagInfo().getStatus();
		  WebElement evsstts=driver.findElement(By.id("status1"));
		   WebElement trasID=driver.findElement(By.id("tid"));
	
		 wr.WriteResult(ActualResult, DataSet+1, "StartTransaction","Actual Result");
		 
		
	    Thread.sleep(1500);
	 
		   Thread.sleep(1500);
		   if(ActualResult.equalsIgnoreCase(ExpectedResult))
		   {
			
	    wr.WriteResult("PASS", DataSet+1, "StartTransaction", "Result");
	    evsestatus= tc.testevsestatus(evseid);
	    evsstts.sendKeys(evsestatus);
	    trasID.sendKeys(Transid);
	    
			}
		   else {
			   wr.WriteResult("FAIL", DataSet+1, "StartTransaction","Result");
			   teststatus=false;
		   }
		   
		   Assert.assertEquals(ActualResult, ExpectedResult);  
		  
			}
		}
		
	}
	
	@AfterClass
	public void tearDown() throws Exception {
		if(teststatus==false) {
			ExcelUtility_SingleDataSet.setExcelFile(Constants.File_Path + Constants.File_Name, "Configurations");
			ExcelUtility_SingleDataSet.setCellData("FAIL", rownum, 3);
			
		}
		else if(status.equalsIgnoreCase("NO")) {
			ExcelUtility_SingleDataSet.setExcelFile(
					Constants.File_Path + Constants.File_Name, "Configurations");
					ExcelUtility_SingleDataSet.setCellData("SKIP", rownum, 3);
			
		}
		else {
			ExcelUtility_SingleDataSet.setExcelFile(
					Constants.File_Path + Constants.File_Name, "Configurations");
					ExcelUtility_SingleDataSet.setCellData("PASS", rownum, 3);
		}
	   // driver.quit();
	}
}
