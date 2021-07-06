package com.app.pages;

import com.app.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class  BasePage {


    @FindBy(xpath = "//h2")
    public WebElement pageTitle;

    public BasePage() {
        PageFactory.initElements(Driver.get(), this);
    }

}
