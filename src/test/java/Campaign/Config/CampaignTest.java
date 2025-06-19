package Campaign.Config;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import BaseClassConfig.BaseClass;
import GenericUtilities.ExcelUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.WebDriverUtility;
import ObjectRepository.CampaignPage;
import ObjectRepository.HomePage;

@Listeners(ListenerUtility.ListenerImplementation.class)
public class CampaignTest extends BaseClass {
	
@Test(groups = "Smoke")
public void createCampaignWithMandatoryTest() throws EncryptedDocumentException, IOException {
	
	ExcelUtility elib = new ExcelUtility();
	JavaUtility jlib = new JavaUtility();
	WebDriverUtility wlib = new WebDriverUtility();

	// Read data from excel
	String campname = elib.toreadDatafromExcelFile("Campaign", 1, 2);
	String size = elib.toreadDatafromExcelFile("Campaign", 1, 3);
	int ran = jlib.getRandomNumber();
	String CampaignName=campname + ran;

	// click on create campaign button
	HomePage hp = new HomePage(driver);
	hp.getCreatecampaignBtn().click();

	// enter mandatory fields
	CampaignPage cp = new CampaignPage(driver);
	cp.getCampaignNameTF().sendKeys(CampaignName);
	cp.getTargetSizeTF().sendKeys(size);
	cp.getCreateCampaignSubmitBtn().click();

	// validation
	WebElement toastmsg = driver.findElement(By.xpath("//div[@role='alert']"));
	wlib.waitForVisibilityOfElement(driver, toastmsg);
	String msg = toastmsg.getText();
	Assert.assertEquals(msg,"Campaign "+CampaignName+" Successfully Added");

	driver.findElement(By.xpath("//button[@aria-label='close']")).click();

}


	@Test(groups = "Regression")
	public void creatCampaignWithExpectedDateTest() throws InterruptedException, EncryptedDocumentException, IOException
	{
	ExcelUtility elib = new ExcelUtility();
	JavaUtility jlib = new JavaUtility();
	WebDriverUtility wlib=new WebDriverUtility();
	
	// Read data from excel
	String campname = elib.toreadDatafromExcelFile("Campaign", 1, 2);
	String size = elib.toreadDatafromExcelFile("Campaign", 1, 3);
	
	int ran = jlib.getRandomNumber();
	String CampaignName=campname + ran;
	String expectedDate = jlib.togetRequiredDate(30);
	
	// create campaign
	HomePage hp=new HomePage(driver);
	hp.getCreatecampaignBtn().click();
	CampaignPage cp=new CampaignPage(driver);
	cp.getCampaignNameTF().sendKeys(CampaignName);
	cp.getTargetSizeTF().sendKeys(size);
	Thread.sleep(2000);
	
	wlib.passInput(driver,cp.getExpectedCloseDateTF(),jlib.togetRequiredDate(30));
	cp.getCreateCampaignSubmitBtn().click();
	
	// validation
	

	wlib.waitForVisibilityOfElement(driver, hp.getToastmsg());
	String msg = hp.getToastmsg().getText();
	Assert.assertTrue(msg.contains(CampaignName));
	driver.findElement(By.xpath("//button[@aria-label='close']")).click();
	
	}

@Test(groups = "Smoke")
public void createCampaignWithStatusTest() throws EncryptedDocumentException, IOException{


	ExcelUtility elib = new ExcelUtility();
	JavaUtility jlib = new JavaUtility();
	WebDriverUtility wlib=new WebDriverUtility();

	// Read data from excel
	String campname = elib.toreadDatafromExcelFile("Campaign", 1, 2);
	String size = elib.toreadDatafromExcelFile("Campaign", 1, 3);
	String status = elib.toreadDatafromExcelFile("Campaign", 1, 4);
	
	int ran = jlib.getRandomNumber();
	String CampaignName=campname + ran;

	// create campaign
	HomePage hp=new HomePage(driver);
	hp.getCreatecampaignBtn().click();
	
	CampaignPage cp=new CampaignPage(driver);
	cp.getCampaignNameTF().sendKeys(CampaignName);
	cp.getCampaignStatusTF().sendKeys(status);
	cp.getTargetSizeTF().clear();
	cp.getTargetSizeTF().sendKeys(size);
	cp.getCreateCampaignSubmitBtn().click();

	// validation
    wlib.waitForVisibilityOfElement(driver, hp.getToastmsg());
	String msg = hp.getToastmsg().getText();
	Assert.assertTrue(msg.contains(CampaignName));
	driver.findElement(By.xpath("//button[@aria-label='close']")).click();
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
