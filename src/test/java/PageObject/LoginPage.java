package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage 
{
	WebDriver ldriver;
	
	//Constructor
	public LoginPage(WebDriver rDriver)
	{
		ldriver=rDriver;
		
		PageFactory.initElements(rDriver, this);
	}
	
	@FindBy(xpath="//div[@class='upload_text']")
	WebElement LoginBtn;
	
	@FindBy(id="identifierId")
	WebElement email;
	
	@FindBy(xpath="//span[text()='Next']")
	WebElement FirstNextBtn;
	
	@FindBy(name="Passwd")
	WebElement password;
	
	@FindBy(xpath="//span[text()='Next']")
	WebElement SecondNextButton;
	
	
	public void clickOnLoginButton()
	{
		LoginBtn.click();
	}
	
	public void enterEmail(String emailAdd)
	{
		email.sendKeys(emailAdd);
	}
	
	public void clickOnFirstNextButton()
	{
		FirstNextBtn.click();
	}
	
	public void enterPassword(String pwd)
	{
		password.sendKeys(pwd);
	}
	
	public void clickOnSecondNextButton()
	{
		SecondNextButton.click();
	}
}
