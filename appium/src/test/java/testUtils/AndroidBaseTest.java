package testUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import yuja.appium.pageObjects.android.SignInPage;
import yuja.appium.pageObjects.utils.AppiumUtils;

public class AndroidBaseTest extends AppiumUtils{
	
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public SignInPage signInPage;
	
	@BeforeClass(alwaysRun=true)
	public void ConfigureAppium() throws IOException {
		
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resourses\\data.properties"); 
		prop.load(fis);
		String ipAddress=prop.getProperty("IPAddress");
		String port=prop.getProperty("port");
		String AndroidDeviceName=System.getProperty("AndroidDeviceName");
		String udid=System.getProperty("udid")!=null ? System.getProperty("udid") : prop.getProperty("udid");
		
		
		service=startAppiumServer(ipAddress, port);
		
		
		UiAutomator2Options options= new UiAutomator2Options();
		options.setDeviceName(AndroidDeviceName);
		options.setUdid(udid);
		
		//options.setDeviceName("Android Device");
		options.setApp(System.getProperty("user.dir")+"//src//test//java//resources//yuja-evp-mobile.apk");
				
		driver= new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		signInPage=new SignInPage(driver);
	}
	
	@AfterClass(alwaysRun=true)
	public void tearDown() {
		driver.quit();
		service.stop();
	}

}
