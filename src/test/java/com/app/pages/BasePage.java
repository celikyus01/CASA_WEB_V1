package com.app.pages;

import com.app.utilities.BrowserUtils;
import com.app.utilities.ConfigurationReader;
import com.app.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class  BasePage {


    @FindBy(xpath = "//h2")
    public WebElement pageTitle;

    public BasePage() {
        PageFactory.initElements(Driver.get(), this);
    }

    public void navigateToMenu(String menuName){

        Driver.get().findElement(By.xpath("//a[.='"+menuName+"']")).click();

    }

    public void logOut(){

        Driver.get().findElement(By.xpath("//span[.='"+ ConfigurationReader.get("username") +"']")).click();


    }

    /**
     * use this method only for rhe web elements starts wtrh //button and //a tags
     * @param elementName: Name of the button
     */

    public void clickButton(String elementName){

       // try {
          //  By by= By.xpath("a//[.='"+elementName+"']");
       // }



    }













}
