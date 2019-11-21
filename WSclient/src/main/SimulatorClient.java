package webClient;

import static org.testng.Assert.assertEquals;

import java.util.List;
import com.google.gson.Gson;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import Simulator.SimulatorTest.BaseClass;
import Simulator.SimulatorTest.Payload;

public class SimulatorClient extends BaseClass {
String bootbody="[2,\"216128\",\"BootNotification\",{\"chargePointVendor\":\"Greenlots\",\"chargePointModel\":\"VirtualCP\",\"chargePointSerialNumber\":\"1234\",\"chargeBoxSerialNumber\":\"1234\",\"firmwareVersion\":\"v101alpha\",\"iccid\":\"iccid\",\"imsi\":\"imsi\",\"meterType\":\"Increasing\",\"meterSerialNumber\":\"B7A4CA2E\"}]";
	@Test(priority=1)
	public void initiateclient() throws InterruptedException {
		driver.get("C://Users/anujg/Desktop/client.html");
		driver.findElement(By.id("hostname")).sendKeys("localhost");
		driver.findElement(By.id("port")).sendKeys("8887");
		driver.findElement(By.id("endpoint")).sendKeys("/1234");
		driver.findElement(By.id("btnConnect")).click();
	    Thread.sleep(1500);
		String bootresponse=driver.findElement(By.id("incomingMsgOutput")).getAttribute("value");
		
		
		   Thread.sleep(1500);
		assertEquals(bootresponse, "Connection Opened Successfully", "Connection Opened Successfully");
	}
	
	@Test(priority=2)
	public void BootNotification() throws InterruptedException {
		   Thread.sleep(1500);
		   driver.findElement(By.id("incomingMsgOutput")).clear();
		driver.findElement(By.id("message")).sendKeys(bootbody);
		driver.findElement(By.id("btnSend")).click();
		   Thread.sleep(1500);
		String bootresponse=driver.findElement(By.id("incomingMsgOutput")).getAttribute("value");
		//System.out.println("Response is   "+ bootresponse);
		String payloadString = bootresponse.substring(bootresponse.indexOf("{"),bootresponse.length()-2);
		System.out.println(payloadString);
		bootpayload payload = new Gson().fromJson(payloadString, bootpayload.class);
		String status=payload.getStatus();
		
		assertEquals(status, "Accepted", "Boot Notification sent successfully");
		
	}
}
