package testpackage;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import com.google.gson.Gson;

import utilities.Constants;

import java.util.concurrent.TimeUnit;

import org.codehaus.jackson.map.ObjectMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.google.gson.Gson;

public class StopReq {
	static String StopRequest1;
	@Test(priority=1)
	public String StopRequestjason(String itTag,String Metervalue,String timestamp,String tid,String Reson) throws InterruptedException {
		 StopRequest org = new StopRequest(); 
		  
	        // Insert the data into the object 
	        getObjectData(org, itTag, Metervalue, timestamp, tid, Reson); 
	  
	        // Creating Object of ObjectMapper define in Jakson Api 
	        ObjectMapper Obj = new ObjectMapper(); 
	  
	        try { 
	  
	            // get Oraganisation object as a json string 
	            String jsonStr = Obj.writeValueAsString(org); 
	            String prefix="[2,\"448310\",\"StopTransaction\",";
	            String postfix="]";
	             StopRequest1=prefix+jsonStr+postfix;
	  
	            // Displaying JSON String 
	           // System.out.println(StopRequest1); 
	        } 
	  
	        catch (IOException e) { 
	            e.printStackTrace(); 
	        }
			//return StopRequest; 
			return StopRequest1;
	    }
	

	
	public static StopRequest getObjectData(StopRequest org,String itTag,String Metervalue,String timestamp,String tid,String Reson) 
    { 
  
        // Insert the data 
       org.setidTag(itTag);
       org.setmeterStop(Metervalue);
       org.setstimestamp(timestamp);
       org.settransactionId(tid);
       org.setreason(Reson);
  
        // Return the object 
        return org; 
    }
    
	  
	
}
