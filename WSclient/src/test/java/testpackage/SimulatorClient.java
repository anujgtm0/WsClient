package testpackage;
import static org.testng.Assert.assertEquals;

import java.util.List;
import com.google.gson.Gson;

import utilities.Constants;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.google.gson.Gson;

public class SimulatorClient extends BaseClass {
String bootbody="[2,\"216128\",\"BootNotification\",{\"chargePointVendor\":\"Greenlots\",\"chargePointModel\":\"VirtualCP\",\"chargePointSerialNumber\":\"1234\",\"chargeBoxSerialNumber\":\"1234\",\"firmwareVersion\":\"v101alpha\",\"iccid\":\"iccid\",\"imsi\":\"imsi\",\"meterType\":\"Increasing\",\"meterSerialNumber\":\"B7A4CA2E\"}]";
	@Test(priority=1)
	public void initiateclient(String hostname,String port,String endpoint) throws InterruptedException {
		driver.get(utilities.Constants.URL);
		driver.findElement(By.id("hostname")).sendKeys(hostname);
		driver.findElement(By.id("port")).sendKeys(port);
		driver.findElement(By.id("endpoint")).sendKeys(endpoint);
		driver.findElement(By.id("btnConnect")).click();
	    Thread.sleep(1500);
	
	}
	
	@Test(priority=2)
	public void BootNotification(String RequestBody) throws InterruptedException {
		   Thread.sleep(1500);
		   driver.findElement(By.id("incomingMsgOutput")).clear();
		driver.findElement(By.id("message")).sendKeys(RequestBody);
		driver.findElement(By.id("btnSend")).click();
		   Thread.sleep(1500);
		
		
	}
	@Test(priority=3)
	public void StartTransaction(String RequestBody) throws InterruptedException {
		   Thread.sleep(1500);
		   driver.findElement(By.id("incomingMsgOutput")).clear();
		driver.findElement(By.id("message")).sendKeys(RequestBody);
		driver.findElement(By.id("btnSend")).click();
		   Thread.sleep(1500);
		
		
	}
	@Test(priority=3)
	public void StopTransaction(String RequestBody) throws InterruptedException {
		   Thread.sleep(1500);
		   driver.findElement(By.id("incomingMsgOutput")).clear();
		driver.findElement(By.id("message")).sendKeys(RequestBody);
		driver.findElement(By.id("btnSend")).click();
		   Thread.sleep(1500);
		
		
	}


}
