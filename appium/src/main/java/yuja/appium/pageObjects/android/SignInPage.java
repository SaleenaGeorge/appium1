package yuja.appium.pageObjects.android;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import yuja.appium.pageObjects.utils.AndroidActions;

public class SignInPage extends AndroidActions{
	
	AndroidDriver driver;
	
	public SignInPage(AndroidDriver driver) {
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		}
	
	@AndroidFindBy(accessibility="SIGN IN")
	private WebElement signInButton;
	
	@AndroidFindBy(className="android.widget.EditText")
	private WebElement OrganisationTextbox;
	
	@AndroidFindBy(accessibility="Next")
	private WebElement nextButton;
	
	@AndroidFindBy(className="android.widget.EditText")
	private List<WebElement> usernameTextbox;
	
	@AndroidFindBy(className="android.widget.EditText")
	private List<WebElement> passwordTextbox;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"SIGN IN\"]/android.widget.TextView")
	private WebElement finalSigninButton;
	
	@AndroidFindBy(className="android.widget.TextView")
	private List<WebElement> actualSignInPageTitle;
	
	
	public void clickWelcomeSignin() {
		signInButton.click();
	}
	
	public void setOrganisationName(String name) throws InterruptedException {
		OrganisationTextbox.sendKeys(name);
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc='"+name+"']/android.widget.TextView")).click();
		Thread.sleep(2000);
		nextButton.click();		
	}
	
	public HomePage enterCredentialsAndLogin(String userName,String password) {
		usernameTextbox.get(0).sendKeys(userName);
		passwordTextbox.get(1).sendKeys(password);
		finalSigninButton.click();
		return new HomePage(driver);
	}
	
	public String getSignInPageTitle() {
		return actualSignInPageTitle.get(1).getText();		
	}
	
	public void setActivity() {
		Activity activity=new Activity("com.yuja_procurement.yujaevpmobile", "com.yuja_procurement.yujaevpmobile.MainActivity");
		driver.startActivity(activity);
	}

}
