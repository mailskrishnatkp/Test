package com.oaknorth.testcases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.oaknorth.config.CommonMethods;
import com.oaknorth.pageobjects.HomePage;

public class TestCase1 extends CommonMethods{
	
	
  @Test
  public void tc1() throws IOException {
	 HomePage hPage=new HomePage(driver);
	 startR("Depositske");
	  hPage.verifyURL();
	  hPage.clickHomeSavingTab();
	  hPage.personalFixedDep();
	  hPage.personalFixedDepApplyNowBtn();
	  hPage.selectSavingAccDropDown();
	  hPage.select12TersmDep();
	  hPage.enterDepAmt("5000");
	  hPage.selectExistCust();
	  hPage.selectUKResidance();
	  hPage.selectUSPerson();
	  hPage.selecttaxPurposeYes();
	  hPage.clickNextBtn();
	  stopR();
  }
}
