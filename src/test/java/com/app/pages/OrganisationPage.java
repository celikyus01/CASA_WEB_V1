package com.app.pages;

import com.app.utilities.BrowserUtils;
import com.app.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrganisationPage extends BasePage {

    public List<String> getSitesByOrganisation(String organistaion) {

        navigateToMenu("Organisations");
        Assert.assertTrue("FAIL: failed to get title as View Organisation", new UsersPage().verifyTitle("View Organisations"));

        try {
            BrowserUtils.waitFor(2);
            Driver.get().findElement(By.id("search")).clear();
            Driver.get().findElement(By.id("search")).sendKeys(organistaion, Keys.ENTER);

            BrowserUtils.waitFor(2);
            click(Driver.get().findElement(By.xpath("//a[contains(text(),'"+organistaion+"')]")));
            BrowserUtils.waitFor(2);
        } catch (NoSuchElementException n) {
            n.printStackTrace();
            BrowserUtils.log(organistaion + " cannot be found in the Organisations page");
        }

        List<WebElement> list = Driver.get().findElements(By.cssSelector(".table-cell"));

        List<String> textList= new ArrayList<>();
        for (WebElement webElement : list) {
            textList.add(webElement.getText());
        }
        Collections.sort(textList);
        return textList;
    }

}
