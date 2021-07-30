package com.app.pages;

import com.app.utilities.BrowserUtils;
import com.app.utilities.ConfigurationReader;
import com.app.utilities.Driver;
import io.cucumber.java.Scenario;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class LoginPage {
    @FindBy(id = "username")
    public WebElement userNameInputBox;

    @FindBy(id = "password")
    public WebElement passwordInputBox;

    @FindBy(css = ".submit")
    public WebElement submitBtn;

    @FindBy(xpath = "//p[@class='error'][1]")
    public WebElement incorrectUsername;

    @FindBy(xpath = "//p[@class='error'][2]")
    public WebElement incorrectPassword;

    @FindBy(xpath = "//p[@class='error'][1]")
    public WebElement firstErrorMessage;

    @FindBy(xpath = "//p[@class='error'][2]")
    public WebElement secondErrorMessage;



    public LoginPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    public List<String> getFailMessagesForResetPassword(){
        List<WebElement> elements = Driver.get().findElements(By.xpath("//*[@class='fail']"));
        return elements.stream().map(e-> e.toString()).collect(Collectors.toList());
    }

    public void login(String username, String password){
        Driver.get().navigate().to(ConfigurationReader.get("url"));
        userNameInputBox.sendKeys(username);
        passwordInputBox.sendKeys(password);
        submitBtn.click();
        Assert.assertTrue("Title was expected to be Dashboard, but NOT. ",Driver.get().getCurrentUrl().contains("dashboard"));
        Assert.assertEquals("FATAL FAIL: Mismatch between logged in username and username in the application.. EXPECTED: "+username+" , FOUND: "+ new DashboardPage().username.toString(),username,new DashboardPage().username.getText().trim());




       // Assert.assertTrue(Driver.get().getCurrentUrl().contains("dashboard"));

    }

    public void loginAsTechnician(){
        Driver.get().get(ConfigurationReader.get("url"));
        login(ConfigurationReader.get("usernameTech"), ConfigurationReader.get("passwordTech"));
    }

    public void loginAsDeveloper(){
        login(ConfigurationReader.get("usernameDeveloper"), ConfigurationReader.get("passwordDeveloper"));
    }

    public static void main(String[] args) {
       new LoginPage().loginAsTechnician();
       new DashboardPage().navigateToMyProfile();
        System.out.println("aa");


    }




}
