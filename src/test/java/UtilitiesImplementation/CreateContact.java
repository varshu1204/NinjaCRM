package UtilitiesImplementation;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import GenericUtilities.ExcelUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.PropertiesFileUtility;
import GenericUtilities.WebDriverUtility;

public class CreateContact {

	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException {

        PropertiesFileUtility plib=new PropertiesFileUtility();
		ExcelUtility elib = new ExcelUtility();
		JavaUtility jlib = new JavaUtility();
		WebDriverUtility wlib=new WebDriverUtility();

		// read data from properties file

		String BROWSER = plib.togetDataFromPropertiesFile("Browser");
		String URL = plib.togetDataFromPropertiesFile("Url");
		String USERNAME = plib.togetDataFromPropertiesFile("Username");
		String PASSWORD = plib.togetDataFromPropertiesFile("Password");
	
				
				//read from excel
		String org = elib.toreadDatafromExcelFile("Contact",1,2);
		String title = elib.toreadDatafromExcelFile("Contact",1,3);
		String mobile = elib.toreadDatafromExcelFile("Contact",1,4);
		String cname = elib.toreadDatafromExcelFile("Contact",1,5);



				    WebDriver driver=null;
				    if(BROWSER.equals("Chrome"))
				    {
				    	driver=new ChromeDriver();
				    }
				    else if(BROWSER.equals("Edge"))
				    {
				    	driver=new EdgeDriver();
				    }
				    else {
				    	driver=new ChromeDriver();
				    }
					
				   //login to app
					
					driver.manage().window().maximize();
                    wlib.waitForPageToLoad(driver);
					driver.get(URL);
					driver.findElement(By.id("username")).sendKeys(USERNAME);
					driver.findElement(By.id("inputPassword")).sendKeys(PASSWORD);
					driver.findElement(By.xpath("//button[text()='Sign In']")).click();
					Thread.sleep(5000);
				
				  //click on contact
					driver.findElement(By.linkText("Contacts")).click();
					driver.findElement(By.xpath("//span[text()='Create Contact']")).click();
				
				  //enter mandatory details
					driver.findElement(By.name("organizationName")).sendKeys(org);
					driver.findElement(By.name("title")).sendKeys(title);
					driver.findElement(By.name("contactName")).sendKeys(cname);
					driver.findElement(By.name("mobile")).sendKeys(mobile);
					
				    driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
				    
				   //switching
				    String parentId = driver.getWindowHandle();
                    wlib.switchToWindow(driver);

				    //select class usage
                    WebElement ref = driver.findElement(By.id("search-criteria"));
                    wlib.select(ref, 1);
				   
				    driver.findElement(By.id("search-input")).sendKeys(org);
				    driver.findElement(By.xpath("(//button[@class='select-btn'])[1]")).click();
				    
				    //switch back
                    driver.switchTo().window(parentId);
                    
				    driver.findElement(By.xpath("//button[@type='submit']")).click();
				    Thread.sleep(2000);
				    
				    //mousehover
				    WebElement logout = driver.findElement(By.xpath("//div[@class='user-icon']"));
				    wlib.mouseHoverOnWebElement(driver, logout);
                    driver.findElement(By.xpath("//div[@class='dropdown-item logout']")).click();

			}

	}


