package yuja.appium;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testUtils.AndroidBaseTest;
import yuja.appium.pageObjects.android.HomePage;
import yuja.appium.pageObjects.android.ProfilePage;
import yuja.appium.pageObjects.android.SignInPage;

public class ValidationOfPages extends AndroidBaseTest{
	@Test(dataProvider="getData")
	public void ValidateHomePage(HashMap<String,String>input) throws InterruptedException {
		//SignInPage signInPage= new SignInPage(driver);
		HomePage homePage=signInPage.enterCredentialsAndLogin(input.get("userName"), input.get("password"));
		Thread.sleep(2000);
		
		String actualHomePageTitle=homePage.getHomePageTitle();
		Assert.assertEquals(actualHomePageTitle, "Homepage");
	}
	
	@Test(dataProvider="getData")
	public void ValidateProfilePage(HashMap<String,String>input) throws InterruptedException {
		HomePage homePage=signInPage.enterCredentialsAndLogin(input.get("userName"), input.get("password"));
		Thread.sleep(2000);
		ProfilePage profilePage=homePage.clickProfileIcon();
		String actualMediaPlaybackText=profilePage.getActualMediaPlaybackText();
		Assert.assertEquals(actualMediaPlaybackText, "Media Playback");
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException 
	{
		List<HashMap<String, String>> data= getJsonData(System.getProperty("user.dir")+"\\src\\test\\java\\testData\\credentials.json");
		return new Object[][] {{data.get(0)},{data.get(1)},{data.get(2)}};
		
	}
	
	@BeforeMethod
	public void preSetUp() throws InterruptedException {
		SignInPage signInPage= new SignInPage(driver);
		signInPage.clickWelcomeSignin();
		signInPage.setOrganisationName("Blue Hills College");
		Thread.sleep(2000);
		//signInPage.setActivity();
		}
	
	@AfterMethod
	public void postSetUp() throws InterruptedException {
		HomePage homePage= new HomePage(driver);
		ProfilePage profilePage=homePage.clickProfileIcon();
		profilePage.clickLogoutButton();
		Thread.sleep(2000);
		}

}
