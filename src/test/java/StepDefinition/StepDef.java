package StepDefinition;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import PageObject.LoginPage;
import Utilities.ReadConfig;
import io.cucumber.java.*;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

//Child class of Base Class
public class StepDef extends BaseClass
{
	@Before
	public void setup()
	{
		//properties file initialization
		readConfig=new ReadConfig();
		
		
		
		//initialization of log class
		log=LogManager.getLogger("StepDef");
		
		System.out.println("setup method executed...");
		
		//read browser name from readconfig method
		String browser=readConfig.getBrowser();
		
		//launch browser
		switch(browser.toLowerCase())
		{
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			break;

		case "msedge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		default:
			driver = null;
			break;

		}
		
		
		log.info("Setup1 executed...");
	}
	
	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {

			
		loginPg=new LoginPage(driver);
		log.info("Chrome browser launched...");
	}

	@When("User opens URL {string}")
	public void user_opens_url(String url) {
		
		driver.get(url);
		log.info("url opened...");
	}
	
	
	@When("Click on Login")
	public void click_on_login() {
	    loginPg.clickOnLoginButton();
	    log.info("Clicked on login button...");
	}
	
	
	
	
	

}
