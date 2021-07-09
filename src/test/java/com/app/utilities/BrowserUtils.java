package com.app.utilities;

import io.cucumber.java.Scenario;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BrowserUtils {


    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static WebDriverWait wait(int secondds) {
        return new WebDriverWait(Driver.get(), secondds);
    }


    public static void waitForPageLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };

        try {
            WebDriverWait wait = new WebDriverWait(Driver.get(), timeOutInSeconds);
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    public static void waitForPageLoad() {

        waitFor(2);
        // wait(10).until(executeScript("return document.readyState").equals("complete"));

        // wait(10).until(Driver.get()-> executeScript("return document.readyState").equals("a"))


    }


    public static Object executeScript(String script) {

        return ((JavascriptExecutor) Driver.get()).executeScript(script);

    }

    public static boolean wait(WebElement webElement, String elementName) {

        WebDriverWait wait = wait(20);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
            return true;
        } catch (NoSuchElementException n) {
            Assert.fail("The element " + elementName + " does not exist on the current page");
        } catch (TimeoutException e) {
            Assert.fail("I was unable to find the element " + elementName + " within the wait time");
        }
        return false;
    }

    public static void wait(String elementCSSLocator, String elementName) {
        //        {
        WebDriverWait wait = wait(20);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(elementCSSLocator)));
        } catch (NoSuchElementException n) {
            Assert.fail("The element " + elementName + " does not exist on the current page");
        } catch (TimeoutException t) {
            Assert.fail("I was unable to find the element " + elementName + " within the wait time");
        }

    }

    public static boolean areAllElementsVisible(List<WebElement> elements) {
        wait(30).until(ExpectedConditions.visibilityOfAllElements(elements));
        return elements.size() >= 1;
    }


    public static void click(WebElement element, String elementName) {
        try {
            // WaitForPageLoad();  // implicit wait will do that
            wait(element, elementName);
            element.click();
            //steplog.AddStepLog("Element ( " + elementname + " )  is clicked");


        } catch (StaleElementReferenceException s) {
            //wait();

            ScrolltoElement(element);
            click(element, elementName);
        } catch (Exception e) {
            ScrolltoElement(element);
            element.click();
            //steplog.AddStepLog("Element (" + elementName + " )  is clicked");
        }
    }

    public static void click(By by){
        try{
            Driver.get().findElement(by).click();
        }catch (StaleElementReferenceException s){
            ScrolltoElement(by);
            click(by);
        }

    }


    public static void scrollDown() {
        executeScript("window.scrollTo(0, 200)");
    }

    public static void ScrolltoElement(WebElement element) {
        ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void ScrolltoElement(By by) {
        ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].scrollIntoView(true);", Driver.get().findElement(by));
    }

    public static void scrollToBottom() {
        executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

//    public static String[] GetTextInArray(WebElement element, String elementname) {
//        return GetText(element, elementname).replace(" ", "").replace(",", "").Split(new[]{
//            Environment.NewLine
//        },StringSplitOptions.None);
//    }


    public static String getText(WebElement element, String elementname) {
        String VisibleText = "";
        try {
            wait(element, elementname);
            VisibleText = element.getText();
        } catch (Exception e) {
            ScrolltoElement(element);
            VisibleText = element.getText();
        }
        return VisibleText;
    }


    public static String getAttribute(WebElement element, String attributeName, String elementname) {
        wait(element, elementname);
        return element.getAttribute(attributeName);
    }


    public static void elementSelectedByText(List<WebElement> elements, String text, String elementname) {
        if (text.equals(""))
            return;


        if (elements.stream().allMatch(e -> e.isEnabled()) && elements.stream().allMatch(e -> e.isDisplayed())) {
            WebElement element = elements.stream().filter(e -> e.getText().equals(text)).findFirst().get();
            element.click();
            System.out.println("Element ( " + elementname + " )  is Selected");
            //
        } else {
            Assert.fail("Element ( " + elementname + " ) Not present/enabled during wait time. Unable to select element by its text");
        }
    }


    public static void elementSelectFilterByText(String locator, String text, String elementname) {

        if (text.equals(""))
            return;

        int length = 0, temp = 0;
        boolean condition = false;
        List<WebElement> elements = getElementsBycssSelector(locator);
        temp = length = elements.size();
        do {
            if (elements.stream().findAny().equals(text.trim())) {
                elements.stream().filter(x -> x.equals(text.trim())).findFirst().get().click();
            } else {
                ScrolltoElement(getElementsBycssSelector(locator).get(length - 1));

                elements = getElementsBycssSelector(locator);
                length = elements.size();
            }
            if (length == temp) {
                condition = true;
                //steplog.AddStepLog("Given input is not on dropdown list");
                Assert.fail("Given input is not on dropdown list");
            } else {
                temp = length;
            }


        } while (!condition);

    }


    public static WebElement getElement(By locator) {
        WebElement element = null;
        int tryCount = 0;
        int[] trials = {0, 0, 2};

        while (element == null) {
            try {
                element = Driver.get().findElement(locator);
            } catch (Exception e) {
                if (tryCount == 3) {
                    throw e;
                }
                // int second = new TimeSpan(0, 0, 2);
                try {
                    Thread.sleep(trials[tryCount]);
                } catch (InterruptedException i) {
                }

                tryCount++;
            }
            //Console.WriteLine(element.ToString() + "Now Retrieved");
        }
        return element;

    }


    public static WebElement getElementById(String id) {
        By locator = By.id(id);
        return getElement(locator);
    }


    public static WebElement getElementByClassName(String clasname) {
        By locator = By.className(clasname);
        return getElement(locator);
    }

    public static WebElement getElementByName(String name) {
        By locator = By.name(name);
        return getElement(locator);
    }

    public static WebElement getElementBycssSelector(String cssSelector) {
        By locator = By.cssSelector(cssSelector);
        return getElement(locator);
    }

    public static WebElement getElementByLinktext(String linktext) {
        By locator = By.linkText(linktext);
        return getElement(locator);
    }

    public static WebElement getElementByXPath(String xPath) {
        By locator = By.xpath(xPath);
        return getElement(locator);
    }

    public static List<WebElement> getElements(By locator) {
        List<WebElement> element = null;
        int tryCount = 0;
        int[] seconds = {0, 0, 2};

        while (element == null) {
            try {
                element = Driver.get().findElements(locator);
            } catch (Exception e) {
                if (tryCount == 3) {
                    throw e;
                }

                try {
                    Thread.sleep(seconds[tryCount]);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }

                tryCount++;
            }
        }
        //Console.WriteLine(element.ToString() + "Now Retrieved");
        return element;
    }

    public static List<WebElement> getElementsById(String id) {
        By locator = By.id(id);
        return getElements(locator);
    }

    public static List<WebElement> getElementsByClassName(String className) {
        By locator = By.className(className);
        return getElements(locator);
    }

    public List<WebElement> getElementsByName(String name) {
        By locator = By.name(name);
        return getElements(locator);
    }

    public static List<WebElement> getElementsBycssSelector(String cssSelector) {
        By locator = By.cssSelector(cssSelector);
        return getElements(locator);
    }

    public static List<WebElement> getElementsByXPath(String xPath) {
        By locator = By.xpath(xPath);
        return getElements(locator);
    }


    public static List<String> elementSelectFilterByText_GetElement(String locator, String elementname) {


        int length = 0, temp = 0;
        boolean condition = false;
        List<String> AllItems = new ArrayList<>();

        List<WebElement> elements;
        elements = getElementsBycssSelector(locator);

        temp = length = elements.size();
        do {

            ScrolltoElement(getElementsBycssSelector(locator).get(length - 1));
            elements = getElementsBycssSelector(locator);
            length = elements.size();

            if (length == temp) {
                condition = true;

                for (WebElement element : elements) {
                    AllItems.add(element.getText());
                }

            } else
                temp = length;

        } while (!condition);

        return AllItems;

    }


    public static void elementSelectedByIndex(List<WebElement> elements, int index, String elementname) {
        if (elements.size() == 0 || elements.size() < index)
            Assert.fail("Element (" + elementname + " ) not present/enabled during wait time. Unable to select element");
        else
            click(elements.get(index), elementname + " Index - " + index);

    }


    public static void sendKeys(WebElement element, String text, String elementname) {
        if (text.equals(""))
            return;

        try {
            wait(element, elementname);
            element.clear();
            element.sendKeys(text);
            System.out.println("'" + text + "'" + " is Entered in " + elementname);
        } catch (StaleElementReferenceException s) {
            WebElement Ele = element;
            sendKeys(Ele, text, elementname);
        }

    }

    public static void clearText(WebElement element, String elementname) {
        wait(element, elementname);
        element.clear();
        System.out.println("Cleared Text on the Element ( " + elementname + " ) ");

    }

    public static void selectDropDownByText(WebElement element, String text, String elementname) {
        wait(element, elementname);
        Select elementToBeSelected = new Select(element);
        elementToBeSelected.selectByVisibleText(text);

        System.out.println("'" + text + "'" + " is selected from dropdown ( " + elementname + " )");

    }

    public static void addScreenshot(String filename, Scenario scenario) {

        final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);

        scenario.attach(screenshot, "image/png", "screenshot"); // not sure id it will work :)


    }


    public static void selectDropDownByValue(WebElement element, String value, String elementname, Scenario scenario) {
        wait(element, elementname);
        Select elementToBeSelected = new Select(element);
        elementToBeSelected.selectByValue(value);
        String text = elementToBeSelected.getFirstSelectedOption().getText();
        System.out.println("'" + text + "'" + " is selected from dropdown .( " + elementname + " )");


    }

    public static void selectDropDownByIndex(WebElement element, int index, String elementname) {
        wait(element, elementname);
        Select elementToBeSelected = new Select(element);
        elementToBeSelected.selectByIndex(index);
        String text = elementToBeSelected.getFirstSelectedOption().getText().toLowerCase();
        System.out.println("'" + text + "'" + " is selected from dropdown . ( " + elementname + " )");
    }

    public static String getSelectedOption(WebElement element, String elementname) {
        wait(element, "element");
        Select elementSelected = new Select(element);
        return elementSelected.getFirstSelectedOption().getText();
    }


    public static void tabAcross(WebElement element, String elementname) {
        wait(element, elementname);
        element.sendKeys(Keys.TAB);
    }

    public static void staleWait() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitForLoadingIconToDisappear() {

        try {
            WebElement Progressbar = Driver.get().findElement(By.cssSelector("[role='progressbar']"));
            while (Progressbar.isDisplayed()) {
                Driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            }
        } catch (Exception e) {
        }
    }

    public static void log(Scenario scenario){

        scenario.log("DEMO");

    }



}




