package UtilitiesImplementation;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;

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

public class CreateCampaignWithStatus {

	public static void main(String[] args) throws IOException {

		PropertiesFileUtility plib = new PropertiesFileUtility();
		ExcelUtility elib = new ExcelUtility();
		JavaUtility jlib = new JavaUtility();

		String BROWSER = plib.togetDataFromPropertiesFile("Browser");
		String URL = plib.togetDataFromPropertiesFile("Url");
		String USERNAME = plib.togetDataFromPropertiesFile("Username");
		String PASSWORD = plib.togetDataFromPropertiesFile("Password");

		// Read data from excel
		String campname = elib.toreadDatafromExcelFile("Campaign", 1, 2);
		String size = elib.toreadDatafromExcelFile("Campaign", 1, 3);
		String status = elib.toreadDatafromExcelFile("Campaign", 1, 4);

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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
		driver.findElement(By.id("username")).sendKeys(USERNAME);
		driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//button[text()='Sign In']")).click();

		// create campaign
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		driver.findElement(By.name("campaignName")).sendKeys(campname + jlib.getRandomNumber());
		driver.findElement(By.name("campaignStatus")).sendKeys(status);
		WebElement target = driver.findElement(By.name("targetSize"));
		target.clear();
		target.sendKeys(size);
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();

		// validation
		WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(toastmsg));
		String msg = toastmsg.getText();

		if (msg.contains(campname)) {
			System.out.println("campaign created");
		} else {
			System.out.println("campaign not created");
		}
		driver.findElement(By.xpath("//button[@aria-label='close']")).click();

		// logout
		WebElement icon = driver.findElement(By.xpath("//div[@class='user-icon']"));
		Actions act = new Actions(driver);
		act.moveToElement(icon).perform();
		WebElement logoutbtn = driver.findElement(By.xpath("//div[@class='dropdown-item logout']"));
		act.moveToElement(logoutbtn).click().perform();

		driver.quit();

	}

}
