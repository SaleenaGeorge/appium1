package yuja.appium;



import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testUtils.AndroidBaseTest;
import yuja.appium.pageObjects.android.HomePage;
import yuja.appium.pageObjects.android.ProfilePage;
import yuja.appium.pageObjects.android.SignInPage;

public class AndroidTest1 extends AndroidBaseTest{
	@Test(dataProvider="getData" , groups={"Smoke"})
	public void loginLogoutTest(HashMap<String,String>input) throws MalformedURLException, InterruptedException 
	{
		SignInPage signInPage= new SignInPage(driver);
		signInPage.clickWelcomeSignin();
		signInPage.setOrganisationName("Blue Hills College");
		Thread.sleep(2000);
		HomePage homePage=signInPage.enterCredentialsAndLogin(input.get("userName"), input.get("password"));
		Thread.sleep(2000);
		
		String actualHomePageTitle=homePage.getHomePageTitle();
		Assert.assertEquals(actualHomePageTitle, "Homepage");
		
		ProfilePage profilePage=homePage.clickProfileIcon();
		profilePage.clickLogoutButton();
		Thread.sleep(2000);
		
		String actualSignInPageTitle=signInPage.getSignInPageTitle();
		Assert.assertEquals(actualSignInPageTitle, "SIGN IN");
    }
	
	@DataProvider
	public Object[][] getData() throws IOException 
	{
		List<HashMap<String, String>> data= getJsonData(System.getProperty("user.dir")+"\\src\\test\\java\\testData\\credentials.json");
		return new Object[][] {{data.get(0)},{data.get(1)},{data.get(2)}};
		
	}
	

}
