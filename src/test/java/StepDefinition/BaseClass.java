package StepDefinition;

import org.openqa.selenium.WebDriver;

import PageObject.LoginPage;
import Utilities.ReadConfig;

import java.util.Properties;

import org.apache.logging.log4j.*;

//Parent Class
public class BaseClass 
{
	public static WebDriver driver;
	public LoginPage loginPg;

	public static Logger log;
	
	public ReadConfig readConfig;
}
