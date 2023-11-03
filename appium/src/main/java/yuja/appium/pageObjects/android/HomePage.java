package yuja.appium.pageObjects.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import yuja.appium.pageObjects.utils.AndroidActions;

public class HomePage extends AndroidActions{
	
AndroidDriver driver;
	
	public HomePage(AndroidDriver driver) {
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		}
	
	@AndroidFindBy(className="android.widget.TextView")
	private WebElement actualHomePageTitle;
	
	@AndroidFindBy(xpath="//android.view.View[@content-desc=\"Profile\"]/android.widget.TextView")
	private WebElement profile;
	
	public String getHomePageTitle() {
		return actualHomePageTitle.getText();		
	}
	
	public ProfilePage clickProfileIcon() {
		profile.click();
		return new ProfilePage(driver);
	}
	
	public String getProfilePageTitle() {
		return profile.getText();		
	}

}
