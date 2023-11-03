package yuja.appium.pageObjects.android;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import yuja.appium.pageObjects.utils.AndroidActions;

public class ProfilePage extends AndroidActions{
	
AndroidDriver driver;
	
	public ProfilePage(AndroidDriver driver) {
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
		}
	
	@AndroidFindBy(accessibility="Logout")
	private WebElement logoutButton;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"Media Playback\"]/android.widget.TextView")
	private WebElement mediaPlayback;
	
	public void clickLogoutButton() {
		logoutButton.click();
	}
	
	public String getActualMediaPlaybackText() {
		return mediaPlayback.getText();
	}

}
