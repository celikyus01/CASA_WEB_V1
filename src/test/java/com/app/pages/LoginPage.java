package com.app.pages;

import com.app.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(id = "username")
    public WebElement userNameInputBox;

    @FindBy(id = "password")
    public WebElement passwordInputBox;

    @FindBy(xpath = "//*[@type='submit']")
    public WebElement submitBtn;


    public LoginPage() {
        PageFactory.initElements(Driver.get(), this);
    }



    public void login(String username, String password){

        userNameInputBox.sendKeys(username);
        passwordInputBox.sendKeys(password);
        submitBtn.click();


    }




}
