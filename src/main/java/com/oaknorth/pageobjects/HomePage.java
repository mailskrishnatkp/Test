package com.oaknorth.pageobjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.oaknorth.config.CommonMethods;

public class HomePage extends CommonMethods {

	public HomePage(WebDriver driver) {

		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 30);
		PageFactory.initElements(factory, this);
		//this.driver=driver;

	}

	@FindBy(xpath = "html/body/div[1]/div/header/div/div[2]/nav/ul/li[4]/a")
	public WebElement savingsLink;

	@FindBy(xpath = "html/body/div[1]/div/header/div/div[2]/nav/ul/li[4]/ul/li[2]/a")
	public WebElement personalFixedTermDep;

	@FindBy(xpath = "html/body/div[1]/main/header/div[1]/div/div/div/div/a[1]")
	public WebElement pFixedTermDepApplyBtn;
	
	@FindBy(id="ui-id-1-button")
	public WebElement selectSavingAccDropdown;
	
	@FindBy(id="ui-id-3")
	public WebElement month12TermDep;
	
	@FindBy(xpath="//*[@id='id1c']/div/input")
	public WebElement depAmt;
	
	@FindBy(xpath="//*[@id='id1c']/div/label/div")
	public WebElement depAmtErrMsg;
	
	
	@FindBy(xpath="//*[@id='id29']/div/label")
	public WebElement existCustomerNoRadioBtn;
	
	@FindBy(xpath=".//*[@id='id34']/div/label")
	public WebElement ukResidentYesBtn;
	
	
	@FindBy(xpath="//*[@id='id36']/div/label")
	public WebElement ukResidentNoBtn;
	
	@FindBy(xpath="//*[@id='id41']/div/label")
	public WebElement usPersonYesBtn;
	
	@FindBy(xpath="//*[@id='id43']/div/label")
	public WebElement usPersonNoBtn;
	
	@FindBy(xpath="//*[@id='id50']/div/label")
	public WebElement taxPurposeUKYes;
	
	@FindBy(xpath="//*[@id='id52']/div/label")
	public WebElement taxPurposeUKNo;
	
	@FindBy(xpath="//*[@id='id57']")
	public WebElement nextBtn;
	
	public void verifyURL() throws IOException {
		try {
			String url = prop.getProperty("URL");
			log("URL:" + url);
			driver.get(url);
			iwait(30);
			driver.manage().window().maximize();
			log("OAK North application launched successfully!");
			String currURL = driver.getCurrentUrl();

			assertEquals(currURL, url, "Wrong URL");

		} catch (Exception e) {

			e.printStackTrace();

		}
	}

	public void clickHomeSavingTab(){
		clickEle(savingsLink);
		sleep(3000);
	}
	
	public void personalFixedDep(){
		clickEle(personalFixedTermDep);
	}
	public void personalFixedDepApplyNowBtn(){
		clickEle(pFixedTermDepApplyBtn);
	}
	public void selectSavingAccDropDown(){
		clickEle(selectSavingAccDropdown);
		sleep(3000);
	}
	public void select12TersmDep(){
		clickEle(month12TermDep);
		sleep(2000);
	}
	public void enterDepAmt(String depAmount){
		enterTxt(depAmt, depAmount);
		sleep(2000);
	}
	
	public void selectExistCust(){
		clickEle(existCustomerNoRadioBtn);
	}
	public void selectUKResidance(){
		clickEle(ukResidentYesBtn);
	}
	public void selectUSPerson(){
		clickEle(usPersonNoBtn);
	}
	public void selecttaxPurposeYes(){
		clickEle(taxPurposeUKYes);
		sleep(2000);
	}
	public void clickNextBtn(){
		clickEle(nextBtn);
	}
}
