package com.app.pages;

import com.app.utilities.BrowserUtils;
import com.app.utilities.ConfigurationReader;
import com.app.utilities.Driver;
import io.cucumber.java.eo.Se;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Locale;

public abstract class BasePage {


    @FindBy(xpath = "(//h2)[2]")
    public WebElement pageTitle;

    @FindBy(css = "span.avatar")
    public WebElement userIcon;

    @FindBy(css = ".submit")
    public WebElement submit;

    @FindBy(css = "span.greeting")
    public WebElement username;


    public BasePage() {
        PageFactory.initElements(Driver.get(), this);
    }

    public void navigateToMenu(String menuName) {
        BrowserUtils.waitFor(1);
        Driver.get().findElement(By.xpath("//a[.='" + menuName + "']")).click();

        if (menuName.equals("Live Floorplans")) {
            Assert.assertTrue(Driver.get().getCurrentUrl().toLowerCase().contains("maps"));
        } else {
            Assert.assertTrue(Driver.get().getCurrentUrl().toLowerCase().contains(menuName.toLowerCase()));
        }


    }

    public void logOut() {
        click(userIcon);
        BrowserUtils.waitForElementToBeVisible(getWebElementByText("Log Out"));
        click("Log Out");
    }


    public WebElement getWebElementByText(String visibleText) {
        return Driver.get().findElement(By.xpath("//*[.='" + visibleText + "']"));
    }

    /**
     * use this method only for rhe web elements starts with "//button" and "//a" tags
     *
     * @param elementName: Name of the button
     */
    public void click(String elementName) {
        By by1 = By.xpath("//*[.='" + elementName + "']");
        By by2 = By.xpath("//*[@value='" + elementName + "']");

        try {
            try {
                BrowserUtils.scrolltoElement(by1);
                BrowserUtils.click(by1);
            } catch (ElementClickInterceptedException e) {
                BrowserUtils.clickWithJS(by1);
            }

        } catch (NoSuchElementException n) {
            try {
                BrowserUtils.scrolltoElement(by2);
                BrowserUtils.click(by2);
            } catch (ElementClickInterceptedException e) {
                BrowserUtils.clickWithJS(by2);
            }
        }

    }

    public void click(WebElement element) {

        try {
            try {
                BrowserUtils.scrolltoElement(element);
                element.click();
            } catch (ElementClickInterceptedException e) {
                BrowserUtils.clickWithJS(element);
            }

        } catch (NoSuchElementException n) {
            try {
                BrowserUtils.scrolltoElement(element);
                element.click();
            } catch (ElementClickInterceptedException e) {
                BrowserUtils.clickWithJS(element);
            }
        }

    }


    public void submit() {
        Driver.get().findElement(By.cssSelector(".submit")).click();
    }

    /**
     * @param id:    the placeholder attribute of the web element
     * @param value: the value to be sent
     */
    public void sendKeysToInputBox(String id, String value) {
        WebElement element = Driver.get().findElement(By.xpath("//*[@id='" + id + "']"));

        BrowserUtils.scrolltoElement(element);
        element.clear();
        if (!(value == null) || !value.equals("")) {
            element.sendKeys(value);
        } else {
            BrowserUtils.log(id + "is NOT a valid id or placeholder");
        }
    }

    /**
     * @param id:    the placeholder attribute of the web element
     * @param value: the value to be sent
     */
    public void sendKeysToInputBoxAndHitToEnter(String id, String value) {
        WebElement element = Driver.get().findElement(By.xpath("//*[@id='" + id + "']"));

        BrowserUtils.scrolltoElement(element);
        element.clear();
        if (!(value == null) || !value.equals("")) {
            element.sendKeys(value);
            element.sendKeys(Keys.ENTER);
        } else {
            BrowserUtils.log(id + "is NOT a valid id or placeholder");
        }
    }

    public String getTextOfInputBox(String id) {
        WebElement element = Driver.get().findElement(By.xpath("//*[@id='" + id + "']"));

        BrowserUtils.scrolltoElement(element);
        return element.getAttribute("value").toString();


    }


    /**
     * @param dropDownID: the ID attribute of the dropdown
     * @param value:      the visible text of the dropdown
     */
    public void selectDD(String dropDownID, String value) {
        if (!value.equals("N/A")) {
            try {
                WebElement dd = Driver.get().findElement(By.id(dropDownID));
                Select select = new Select(dd);
                select.selectByVisibleText(value);
            } catch (ElementNotInteractableException a) {
                System.out.println("for certain user type, you can not select country");
            }
        }
    }


    /**
     * @param dropDownID: the ID attribute of the dropdown
     */
    public String getSelectedValueDD(String dropDownID) {
        WebElement dd = Driver.get().findElement(By.id(dropDownID));
        Select select = new Select(dd);
        return select.getFirstSelectedOption().getText();
    }

    /**
     * @param title: expected title
     * @return true if the expected title is displayed
     */
    public boolean verifyTitle(String title) {

        WebElement actualTitle = Driver.get().findElement(By.xpath("//h2[.='" + title + "']"));
        BrowserUtils.waitForElementToBeVisible(actualTitle);
        return actualTitle.isDisplayed();
    }

    public void navigateToMyProfile() {

        userIcon.click();
        click("My Profile");

    }



//    public List<String> getSitesByOrganisation(String organisationName){
//
//    }


}
