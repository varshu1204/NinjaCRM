package BaseClassConfig;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import GenericUtilities.PropertiesFileUtility;
import GenericUtilities.WebDriverUtility;
import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;

public class BaseClass {
	
	public WebDriver driver = null;
	public static WebDriver sdriver=null;//Listener purpose
	public PropertiesFileUtility plib = new PropertiesFileUtility();
	public WebDriverUtility wlib=new WebDriverUtility();

	@BeforeSuite(groups= {"Smoke","Regression"})
	public void beforeSuite() {
		System.out.println("establish database connectivity");
	}

	@BeforeTest(groups= {"Smoke","Regression"})
	public void beforeTest() {
		System.out.println("preconditions");
	}

	//@Parameters("BROWSER")
	@BeforeClass(groups= {"Smoke","Regression"})
	public void beforeClass() throws IOException {
		//String BROWSER=Browser;
		String BROWSER = plib.togetDataFromPropertiesFile("Browser");
		
		if (BROWSER.equals("Edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("Chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("Firefox")) {
			driver = new FirefoxDriver();
		}
		sdriver=driver; //Passing driver ref to static driver variable
		System.out.println("launching browser");
		
	}

	@BeforeMethod(groups= {"Smoke","Regression"})
	public void beforeMethod() throws IOException {
		//String URL = plib.togetDataFromPropertiesFile("Url");
		String URL = System.getProperty("url");

		String USERNAME = plib.togetDataFromPropertiesFile("Username");
		String PASSWORD = plib.togetDataFromPropertiesFile("Password");
		driver.manage().window().maximize();
        wlib.waitForPageToLoad(driver);
		driver.get(URL);
		LoginPage lp = new LoginPage(driver);
		lp.getUN().sendKeys(USERNAME);
		lp.getPW().sendKeys(PASSWORD);
		lp.getLoginBtn().click();
		System.out.println("Login done");
	}

	@AfterMethod(groups= {"Smoke","Regression"})
	public void afterMethod() {
		HomePage hp = new HomePage(driver);
		hp.getUserIcon().click();
		hp.getLogOutBtn().click();
		System.out.println("logout done");
	}

	@AfterClass(groups= {"Smoke","Regression"})
	public void afterClass() {
		driver.quit();
		System.out.println("closing browser");
	}

	@AfterTest(groups= {"Smoke","Regression"})
	public void afterTest() {
		System.out.println("post conditions");
	}

	@AfterSuite(groups= {"Smoke","Regression"})
	public void afterSuite() {
		System.out.println("closeDB connection");
	}
}
