package com.app.utilities;

public class CommonMethods {





}


//
//
//        namespace CASA_UI.Helper
//        {
//        [Binding]
//public class CommonMethods : Steps
//        {
//private IWebDriver commonMethodsDriver;
//        Steplogger steplog;
//protected CommonMethods(IWebDriver driver)
//        {
//        commonMethodsDriver = driver;
//        steplog = new Steplogger();
//
//        }
//
//protected void Wait()
//        {
//        Thread.Sleep(1000);
//        }
//
//protected void WaitForPageLoad()
//        {
//        WebDriverWait wait = Wait(2000);
//        wait.Until(driver => ((IJavaScriptExecutor)driver).ExecuteScript("return document.readyState").Equals("complete"));
//        }
//protected WebDriverWait Wait(int timeout)
//        {
//        return new WebDriverWait(commonMethodsDriver, TimeSpan.FromSeconds(timeout));
//        }
//
//protected bool Wait(IWebElement element, string elementname)
//        {
//        WebDriverWait wait = Wait(20);
//
//        try
//        {
//        wait.Until(ExpectedConditions.ElementToBeClickable(element));
//        return true;
//        }
//        catch (NoSuchElementException)
//        {
//        Assert.Fail("The element " + elementname + " does not exist on the current page");
//        }
//        catch (WebDriverTimeoutException)
//        {
//        Assert.Fail("I was unable to find the element " + elementname + " within the wait time");
//        }
//        return false;
//        }
//
//protected void Wait(string element, string elementname)
//        {
//        WebDriverWait wait = Wait(20);
//
//        try
//        {
//        wait.Until(ExpectedConditions.ElementToBeClickable(By.CssSelector(element)));
//        }
//        catch (NoSuchElementException)
//        {
//        Assert.Fail("The element " + elementname + " does not exist on the current page");
//        }
//        catch (WebDriverTimeoutException)
//        {
//        Assert.Fail("I was unable to find the element " + elementname + " within the wait time");
//        }
//        }
//
//protected bool AreAllElementsVisible(IList<IWebElement> elements)
//        {
//        Wait(30).Until(ExpectedConditions.VisibilityOfAllElementsLocatedBy(new ReadOnlyCollection<IWebElement>(elements)));
//        return elements.Count >= 1;
//        }
//
//protected void Click(IWebElement element, string elementname)
//        {
//        try
//        {
//        WaitForPageLoad();
//        Wait(element, elementname);
//        element.Click();
//        steplog.AddStepLog("Element ( " + elementname + " )  is clicked");
//
//
//        }
//        catch (StaleElementReferenceException)
//        {
//        Wait();
//        IWebElement Ele = element;
//        ScrolltoElement(Ele);
//        Click(Ele, elementname);
//        }
//        catch (InvalidOperationException)
//        {
//        ScrolltoElement(element);
//        element.Click();
//        steplog.AddStepLog("Element (" + elementname + " )  is clicked");
//        }
//        }
//
//protected string[] GetTextInArray(IWebElement element, string elementname)
//        {
//        return GetText(element, elementname).Replace(" ", "").Replace(",", "").Split(new[] { Environment.NewLine }, StringSplitOptions.None);
//        }
//
//protected string GetText(IWebElement element, string elementname)
//        {
//        string VisibleText = "";
//        try
//        {
//        Wait(element, elementname);
//        VisibleText = element.Text;
//        }
//        catch (InvalidOperationException)
//        {
//        ScrolltoElement(element);
//        VisibleText = element.Text;
//        }
//        return VisibleText;
//        }
//
//protected string GetAttribute(IWebElement element, string attributeName, string elementname)
//        {
//        Wait(element, elementname);
//        return element.GetAttribute(attributeName);
//        }
//
//protected void ElementSelectedByText(IList<IWebElement> elements, string text, string elementname)
//        {
//        if (text.Equals(""))
//        return;
//
//        if (elements.All(e => e.Enabled).Equals(true) && AreAllElementsVisible(elements))
//        {
//        IWebElement element = elements.First(e => e.Text.Equals(text.Trim()));
//        element.Click();
//        steplog.AddStepLog("Element ( " + elementname + " )  is Selected");
//        Wait();
//        }
//        else
//        {
//        Assert.Fail("Element ( " + elementname + " ) Not present/enabled during wait time. Unable to select element by its text");
//        }
//        }
//
//
//protected void ElementSelectFilterByText(string locator, string text, string elementname)
//        {
//        Wait();
//        if (text.Equals(""))
//        return;
//
//        int length = 0, temp = 0;
//        bool condition = false;
//        IList<IWebElement> elements;
//        elements = GetElementsBycssSelector(locator);
//        temp = length = elements.Count();
//        do
//        {
//        if (elements.Any(e => e.Text.Equals(text.Trim())))
//        {
//        elements.First(e => e.Text.Equals(text.Trim())).Click();
//        Wait();
//        return;
//        }
//        else
//        {
//        ScrolltoElement(GetElementsBycssSelector(locator).ElementAt(length - 1));
//        Wait(); Wait();
//        elements = GetElementsBycssSelector(locator);
//        length = elements.Count();
//        }
//        if (length == temp)
//        {
//        condition = true;
//        steplog.AddStepLog("Given input is not on dropdown list");
//        Assert.Fail("Given input is not on dropdown list");
//        }
//        else
//        temp = length;
//
//        } while (!condition);
//
//        }
//
//protected IList<string> ElementSelectFilterByText_GetElement(string locator, string elementname)
//        {
//        Wait();
//
//
//        int length = 0, temp = 0;
//        bool condition = false;
//        IList<string> AllItems = new List<string>();
//
//        IList<IWebElement> elements;
//        elements = GetElementsBycssSelector(locator);
//
//        temp = length = elements.Count();
//        do
//        {
//
//        ScrolltoElement(GetElementsBycssSelector(locator).ElementAt(length - 1));
//        Wait(); Wait();
//        elements = GetElementsBycssSelector(locator);
//        length = elements.Count();
//
//        if (length == temp)
//        {
//        condition = true;
//        foreach (IWebElement e in elements)
//        {
//        AllItems.Add(e.Text);
//        }
//        }
//        else
//        temp = length;
//
//        } while (!condition);
//
//        return AllItems;
//
//        }
//
//protected void ElementSelectedByIndex(IList<IWebElement> elements, int index, string elementname)
//        {
//        if (elements.Count == 0 || elements.Count < index)
//        Assert.Fail("Element (" + elementname + " ) not present/enabled during wait time. Unable to select element");
//        else
//        Click(elements[index], elementname + " Index - " + index.ToString());
//
//        }
//
//protected void SendKeys(IWebElement element, string text, string elementname)
//        {
//        if (text.Equals(""))
//        return;
//
//        try
//        {
//        Wait(element, elementname);
//        element.Clear();
//        element.SendKeys(text);
//        steplog.AddStepLog("'" + text + "'" + " is Entered in " + elementname);
//        }
//
//        catch (StaleElementReferenceException)
//        {
//        Wait();
//        IWebElement Ele = element;
//        SendKeys(Ele, text, elementname);
//        }
//        Wait();
//        }
//
//protected void ClearText(IWebElement element, string elementname)
//        {
//        Wait(element, elementname);
//        element.Clear();
//        steplog.AddStepLog("Cleared Text on the Element ( " + elementname + " ) ");
//        Wait();
//        }
//
//protected void SelectDropDownByText(IWebElement element, string text, string elementname)
//        {
//        Wait(element, elementname);
//        SelectElement elementToBeSelected = new SelectElement(element);
//        elementToBeSelected.SelectByText(text);
//        Wait();
//        steplog.AddStepLog("'" + text + "'" + " is selected from dropdown ( " + elementname + " )");
//
//        }
//
//protected void AddScreenshot(string filename)
//        {
//        ((ITakesScreenshot)commonMethodsDriver).GetScreenshot().SaveAsFile(filename);
//
//        }
//
//protected void SelectDropDownByValue(IWebElement element, string value, string elementname)
//        {
//        Wait(element, elementname);
//        SelectElement elementToBeSelected = new SelectElement(element);
//        elementToBeSelected.SelectByValue(value);
//        string text = elementToBeSelected.SelectedOption.ToString();
//        steplog.AddStepLog("'" + text + "'" + " is selected from dropdown .( " + elementname + " )");
//
//        }
//
//protected void SelectDropDownByIndex(IWebElement element, int index, string elementname)
//        {
//        Wait(element, elementname);
//        SelectElement elementToBeSelected = new SelectElement(element);
//        elementToBeSelected.SelectByIndex(index);
//        string text = elementToBeSelected.SelectedOption.Text.ToString();
//        steplog.AddStepLog("'" + text + "'" + " is selected from dropdown . ( " + elementname + " )");
//        }
//
//protected string GetSelectedOption(IWebElement element, string elementname)
//        {
//        Wait(element, "element");
//        SelectElement elementSelected = new SelectElement(element);
//        return elementSelected.SelectedOption.Text;
//        }
//
//public void ScrollDown()
//        {
//        var js = string.Format("window.scrollTo(0, 200)");
//        ((IJavaScriptExecutor)commonMethodsDriver).ExecuteScript(js);
//        }
//
//protected void ScrolltoElement(IWebElement Element)
//        {
//        ((IJavaScriptExecutor)commonMethodsDriver).ExecuteScript("arguments[0].scrollIntoView(true);", Element);
//        }
//
//protected void ScrollToBottom()
//        {
//        ((IJavaScriptExecutor)commonMethodsDriver).ExecuteScript("window.scrollTo(0, document.body.scrollHeight)");
//        }
//
//protected void TabAcross(IWebElement element, string elementname)
//        {
//        Wait(element, elementname);
//        element.SendKeys(Keys.Tab);
//        }
//
//protected void StaleWait()
//        {
//        Thread.Sleep(6000);
//        }
//
//protected void WaitForLoadingIconToDisappear()
//        {
//        Wait();
//        try
//        {
//        IWebElement Progressbar = commonMethodsDriver.FindElement(By.CssSelector("[role='progressbar']"));
//        while (Progressbar.Displayed)
//        {
//        commonMethodsDriver.Manage().Timeouts().ImplicitWait = TimeSpan.FromSeconds(5);
//        }
//        }
//        catch (Exception)
//        {
//        }
//        }
//
//public IWebElement GetElement(By locator)
//        {
//        IWebElement element = null;
//        int tryCount = 0;
//
//        while (element == null)
//        {
//        try
//        {
//        element = commonMethodsDriver.FindElement(locator);
//        }
//        catch (Exception e)
//        {
//        if (tryCount == 3)
//        {
//        throw e;
//        }
//        var second = new TimeSpan(0, 0, 2);
//        Thread.Sleep(second);
//
//        tryCount++;
//        }
//        }
//        //Console.WriteLine(element.ToString() + "Now Retrieved");
//        return element;
//        }
//
//
//public IWebElement GetElementById(string id)
//        {
//        By locator = By.Id(id);
//        return GetElement(locator);
//        }
//
//
//public IWebElement GetElementByClassName(string className)
//        {
//        By locator = By.ClassName(className);
//        return GetElement(locator);
//        }
//public IWebElement GetElementByName(string name)
//        {
//        By locator = By.Name(name);
//        return GetElement(locator);
//        }
//public IWebElement GetElementBycssSelector(string cssSelector)
//        {
//        By locator = By.CssSelector(cssSelector);
//        return GetElement(locator);
//        }
//
//public IWebElement GetElementByLinktext(string linktext)
//        {
//        By locator = By.LinkText(linktext);
//        return GetElement(locator);
//        }
//public IWebElement GetElementByXPath(string xPath)
//        {
//        By locator = By.XPath(xPath);
//        return GetElement(locator);
//        }
//
//public IList<IWebElement> GetElements(By locator)
//        {
//        IList<IWebElement> element = null;
//        int tryCount = 0;
//
//        while (element == null)
//        {
//        try
//        {
//        element = commonMethodsDriver.FindElements(locator);
//        }
//        catch (Exception e)
//        {
//        if (tryCount == 3)
//        {
//        throw e;
//        }
//        var second = new TimeSpan(0, 0, 2);
//        Thread.Sleep(second);
//
//        tryCount++;
//        }
//        }
//        //Console.WriteLine(element.ToString() + "Now Retrieved");
//        return element;
//        }
//
//public IList<IWebElement> GetElementsById(string id)
//        {
//        By locator = By.Id(id);
//        return GetElements(locator);
//        }
//public IList<IWebElement> GetElementsByClassName(string className)
//        {
//        By locator = By.ClassName(className);
//        return GetElements(locator);
//        }
//public IList<IWebElement> GetElementsByName(string name)
//        {
//        By locator = By.Name(name);
//        return GetElements(locator);
//        }
//public IList<IWebElement> GetElementsBycssSelector(string cssSelector)
//        {
//        By locator = By.CssSelector(cssSelector);
//        return GetElements(locator);
//        }
//public IList<IWebElement> GetElementsByXPath(string xPath)
//        {
//        By locator = By.XPath(xPath);
//        return GetElements(locator);
//        }
//
//
//        }
//        }