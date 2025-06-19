package POMImplementation;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import BaseClassConfig.BaseClass;
import GenericUtilities.ExcelUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.PropertiesFileUtility;
import GenericUtilities.WebDriverUtility;
import ObjectRepository.CampaignPage;
import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;

public class CreateCampaignWithMandatoryFields extends BaseClass{

		@Test
		public void createCampaignWithMandatory() throws EncryptedDocumentException, IOException {
			
		ExcelUtility elib = new ExcelUtility();
		JavaUtility jlib = new JavaUtility();
		WebDriverUtility wlib = new WebDriverUtility();

		// Read data from excel
		String campname = elib.toreadDatafromExcelFile("Campaign", 1, 2);
		String size = elib.toreadDatafromExcelFile("Campaign", 1, 3);
		int ran = jlib.getRandomNumber();

		// click on create campaign button
		HomePage hp = new HomePage(driver);
		hp.getCreatecampaignBtn().click();

		// enter mandatory fields
		CampaignPage cp = new CampaignPage(driver);
		cp.getCampaignNameTF().sendKeys(campname);
		cp.getTargetSizeTF().sendKeys(size);
		cp.getCreateCampaignSubmitBtn().click();

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



	}

}
