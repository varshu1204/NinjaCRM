package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	public HomePage(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	
	
	@FindBy(linkText = "Campaigns")
	private WebElement campaign;
	
	@FindBy(linkText = "Contacts")
	 private WebElement contact;
	
	@FindBy(linkText="Products")
	private WebElement product;
	
	public WebElement getProduct() {
		return product;
	}

	public void setProduct(WebElement product) {
		this.product = product;
	}


	@FindBy(xpath="//span[text()='Create Campaign']")
	 private WebElement createcampaignBtn;
	
	@FindBy(className = "user-icon")
	private WebElement userIcon;
	
	@FindBy(xpath="//div[text()='Logout ']")
	private WebElement logOutBtn;
	
	@FindBy(xpath="//div[@role='alert']")
	private WebElement toastmsg;
	
	@FindBy(xpath="//button[@aria-label='close']")
	private WebElement closeMsg;
	

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getCloseMsg() {
		return closeMsg;
	}

	public void setCloseMsg(WebElement closeMsg) {
		this.closeMsg = closeMsg;
	}

	public WebElement getToastmsg() {
		return toastmsg;
	}

	public WebElement getCampaign() {
		return campaign;
	}

	public WebElement getContact() {
		return contact;
	}

	public WebElement getCreatecampaignBtn() {
		return createcampaignBtn;
	}

	public WebElement getUserIcon() {
		return userIcon;
	}

	public WebElement getLogOutBtn() {
		return logOutBtn;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
