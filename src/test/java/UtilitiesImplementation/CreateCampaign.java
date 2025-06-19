package UtilitiesImplementation;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import GenericUtilities.ExcelUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.PropertiesFileUtility;
import GenericUtilities.WebDriverUtility;

public class CreateCampaign {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		// Reading data from Properties file
		PropertiesFileUtility plib = new PropertiesFileUtility();
		ExcelUtility elib = new ExcelUtility();
		JavaUtility jlib = new JavaUtility();
		WebDriverUtility wlib=new WebDriverUtility();

		String BROWSER = plib.togetDataFromPropertiesFile("Browser");
		String URL = plib.togetDataFromPropertiesFile("Url");
		String USERNAME = plib.togetDataFromPropertiesFile("Username");
		String PASSWORD = plib.togetDataFromPropertiesFile("Password");

		// Read data from excel
		String campname = elib.toreadDatafromExcelFile("Campaign", 1, 2);
		String size = elib.toreadDatafromExcelFile("Campaign", 1, 3);
		int ran = jlib.getRandomNumber();

		WebDriver driver = null;

		if (BROWSER.equals("Edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("Chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("Firefox")) {
			driver = new FirefoxDriver();
		}

		// login action
		driver.manage().window().maximize();
        wlib.waitForPageToLoad(driver);
		driver.get(URL);
		driver.findElement(By.id("username")).sendKeys(USERNAME);
		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();

		// create campaign
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		driver.findElement(By.name("campaignName")).sendKeys(campname + ran);
		WebElement target = driver.findElement(By.name("targetSize"));
		target.clear();
		target.sendKeys(size);
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();

		// validation
		WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
        wlib.waitForVisibilityOfElement(driver, toastmsg);
		String msg = toastmsg.getText();

		if (msg.contains(campname)) {
			System.out.println("campaign created");
		} else {
			System.out.println("campaign not created");
		}
		driver.findElement(By.xpath("//button[@aria-label='close']")).click();

		// logout
		WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']"));
        wlib.mouseHoverOnWebElement(driver, icon);
        WebElement logoutbtn = driver.findElement(By.xpath("//div[@class='dropdown-item logout']"));
        wlib.clickOnWebElement(driver, logoutbtn);

		driver.quit();

	}

}
