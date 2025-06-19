package POMImplementation;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import BaseClassConfig.BaseClass;
import GenericUtilities.ExcelUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.PropertiesFileUtility;
import GenericUtilities.WebDriverUtility;
import ObjectRepository.CampaignPage;
import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;

public class CreateCampaignWithStatus extends BaseClass {
	
	@Test
	public void createCampaignWithStatus() throws EncryptedDocumentException, IOException{


		ExcelUtility elib = new ExcelUtility();
		JavaUtility jlib = new JavaUtility();
		WebDriverUtility wlib=new WebDriverUtility();

		// Read data from excel
		String campname = elib.toreadDatafromExcelFile("Campaign", 1, 2);
		String size = elib.toreadDatafromExcelFile("Campaign", 1, 3);
		String status = elib.toreadDatafromExcelFile("Campaign", 1, 4);


		// create campaign
		HomePage hp=new HomePage(driver);
		hp.getCreatecampaignBtn().click();
		
		CampaignPage cp=new CampaignPage(driver);
		cp.getCampaignNameTF().sendKeys(campname+jlib.getRandomNumber());
		cp.getCampaignStatusTF().sendKeys(status);
		cp.getTargetSizeTF().clear();
		cp.getTargetSizeTF().sendKeys(size);
		cp.getCreateCampaignSubmitBtn().click();

		// validation
        wlib.waitForVisibilityOfElement(driver, hp.getToastmsg());
		String msg = hp.getToastmsg().getText();

		if (msg.contains(campname)) {
			System.out.println("campaign created");
		} else {
			System.out.println("campaign not created");
		}
		hp.getCloseMsg().click();
		
	}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}


