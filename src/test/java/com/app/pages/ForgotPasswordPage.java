package com.app.pages;

import com.app.stepdefinitions.LoginStepDefs;
import com.app.utilities.BrowserUtils;
import com.app.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ForgotPasswordPage {


    @FindBy(xpath = "//h3")
    public WebElement pageDescription;

    @FindBy(xpath = "//*[@class='xW xY ']/span/span")
    public WebElement emailTime;

    @FindBy(xpath = "(//*[.='Your RackEye Account'])[1]")
    public WebElement email;

    @FindBy(css = ".fail")
    public List<WebElement> resetPasswordFailMessages;

    @FindBy(xpath = "//*[@Value='Change Password']")
    public WebElement submitBtn;


    public ForgotPasswordPage() {
        PageFactory.initElements(Driver.get(), this);
    }


    public List<String> getFailMessagesForResetPassword() {
        List<WebElement> elements = Driver.get().findElements(By.xpath("//li"));
        return elements.stream().map(e -> e.toString()).collect(Collectors.toList());
    }

    public void verifyFailMessage(String message) {
        try {
            Driver.get().findElement(By.xpath("//li[@class='fail' and text()='" + message + "']"));
            System.out.println("message = " + message);
        } catch (NoSuchElementException e) {
            Assert.fail("!! FAIL:  No such error message " + message);
        }


    }

    public boolean verifyFailMessageInGrey(String message) {

        System.out.println("message expected to be be grey = " + message);
        List<WebElement> elements = Driver.get().findElements(By.xpath("//li[@class='pending']"));
        System.out.println("Grey messages : ");
        for (WebElement element : elements) {
            System.out.println(" ---" + element.getText());
        }

        try {
            Driver.get().findElement(By.xpath("//li[@class='pending' and text()='" + message + "']"));
            System.out.println("is message actually grey:" + true);

            submitBtn.click();
            BrowserUtils.waitFor(1);
            try {
                Driver.get().findElement(By.xpath("//li[@class='fail' and text()='" + message + "']"));
                System.out.println("message turned to red");
            } catch (NoSuchElementException n) {
                BrowserUtils.log(" FAIL:  Message expected to be turned red after clicking on Submit Button, But NOT!! ");
                new LoginStepDefs().isTestFailed = true;
            }

            return true;
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("is message actually grey:" + false + " or the message is not exist");
            return false;
        }


    }

    public void enterPasswordToReset(String value) {
        new UsersPage().sendKeysToInputBox("newpassword", value);
        new UsersPage().sendKeysToInputBox("confirmpassword", value);
    }


}
