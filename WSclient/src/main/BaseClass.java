package Simulator.SimulatorTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseClass {
	public static WebDriver driver = null;
	
	public BaseClass()
	
	{
		
	if(driver == null)
	{
	          //Mention path of chrome driver of your system
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\anujg\\Documents\\driver\\chromedriver.exe");
	       driver = new ChromeDriver();
	System.out.println("**Chrome driver initiated**");
	}
	else
	{
	System.out.println("**Chrome driver already instantiated**");
	}
	}
	public static WebDriver getdriver()
	{
		if (driver == null){
			return driver;
			}else{
			return driver;
			}
	}
	}
