package POMImplementation;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import BaseClassConfig.BaseClass;
import GenericUtilities.ExcelUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.PropertiesFileUtility;
import GenericUtilities.WebDriverUtility;
import ObjectRepository.AddProduct;
import ObjectRepository.HomePage;
import ObjectRepository.LoginPage;
import ObjectRepository.ProductPage;

public class CreateProduct extends BaseClass {

		@Test
		public void createProductWithMandatoryFields() throws EncryptedDocumentException, IOException {


		   ExcelUtility elib=new ExcelUtility();
		   JavaUtility jlib=new JavaUtility();
		   WebDriverUtility wlib=new WebDriverUtility();
		   
		  String productName = elib.toreadDatafromExcelFile("Product", 1, 0);
		  String quantity = elib.toreadDatafromExcelFile("Product", 1, 1);
		  String price = elib.toreadDatafromExcelFile("Product", 1, 2);


                HomePage hp=new HomePage(driver);
                hp.getProduct().click();
                ProductPage pp=new ProductPage(driver);
                pp.getAddProductBtn().click();
                
                AddProduct ap=new AddProduct(driver);
                ap.getProductName().sendKeys(productName+jlib.getRandomNumber());
                ap.getQuantity().clear();
                ap.getQuantity().sendKeys(quantity);
                ap.getPrice().clear();
                ap.getPrice().sendKeys(price);

               
                wlib.select(ap.getProdCategory(), 2);
                wlib.select(ap.getVendorId(), 3);
                ap.getAddProdButton().click();



}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	

}
