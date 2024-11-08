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
	
	
	@When("User enters Email as {string}")
	public void user_enters_email_as(String emailadd) {
		//move on login Window
		String origionalWindow=driver.getWindowHandle();
		Set<String> allPages=driver.getWindowHandles();
		for(String page:allPages)
		{
			driver.switchTo().window(page);
		}
		System.out.println("move to login window");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    loginPg.enterEmail(emailadd);
		log.info("Entered Email Id...");
	}
	
	@When("Click on Next")
	public void click_on_next() {
	    loginPg.clickOnFirstNextButton();
	    log.info("Clicked on First next Button...");
	}
	
	@When("User enters Password as {string}")
	public void user_enters_password_as(String passwordadd) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    loginPg.enterPassword(passwordadd);
	    log.info("Entered Password...");
	}
	
	@When("Click on Next2")
	public void click_on_next2() {
		loginPg.clickOnSecondNextButton();
		log.info("Clicked on Second next Button...");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Login success");
	    
		}
	
	@Then("Page Title should be {string}")
	public void page_title_should_be(String expectedTitle) {
		//move on Dash Board
		Set<String> allWindowHandles = driver.getWindowHandles();

		// Iterate through the handles and switch to the last one
		for (String windowHandle : allWindowHandles) {
		    driver.switchTo().window(windowHandle);
		}
		
		//page title verify
		String actualTitle=driver.getTitle();

		if(actualTitle.equals(expectedTitle))
		{
			log.warn("Test passed: Login feature :Page title matched.");

			Assert.assertTrue(true);//pass
		}
		else
		{
			Assert.assertTrue(false);//fail
			log.warn("Test Failed: Login feature- page title not matched.");


		}
	}
	
	@After
	public void teardown(Scenario sc)
	{
		System.out.println("Tear Down method executed..");
		if(sc.isFailed()==true)
		{
			//Convert web driver object to TakeScreenshot

			String fileWithPath = "D:\\Shivam Download\\Eclipse Workspace\\CucumberFramework\\Screenshot\\failedScreenshot.png";
			TakesScreenshot scrShot =((TakesScreenshot)driver);

			//Call getScreenshotAs method to create image file
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

			//Move image file to new destination
			File DestFile=new File(fileWithPath);

			//Copy file at destination

			try {
				FileUtils.copyFile(SrcFile, DestFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		driver.quit();
	}
	

	}
	
	

