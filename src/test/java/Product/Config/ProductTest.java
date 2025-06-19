package Product.Config;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import BaseClassConfig.BaseClass;
import GenericUtilities.ExcelUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.WebDriverUtility;
import ObjectRepository.AddProduct;
import ObjectRepository.HomePage;
import ObjectRepository.ProductPage;

public class ProductTest extends BaseClass{
	
	@Test(groups = "Regression")
	public void createProductWithMandatoryFieldsTest() throws EncryptedDocumentException, IOException, InterruptedException {


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
            wlib.select(ap.getVendorId(), 1);
            ap.getAddProdButton().click();

        	driver.findElement(By.xpath("//button[@aria-label='close']")).click();
        	


	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
