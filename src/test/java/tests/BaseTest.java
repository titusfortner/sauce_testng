package test.java.tests;

import com.saucelabs.framework.Browser;
import com.saucelabs.framework.pages.PageObject;
import com.saucelabs.saucebindings.SauceOptions;
import com.saucelabs.saucebindings.SauceSession;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class BaseTest {
    protected static ThreadLocal<SauceSession> session = new ThreadLocal<>();
    protected static ThreadLocal<SauceOptions> options = new ThreadLocal<>();

    public SauceSession getSession() {
        return session.get();
    }

    public WebDriver getDriver() {
        return getSession().getDriver();
    }

    @BeforeMethod
    public void setup (Method method) {
        options.set(new SauceOptions());
        options.get().setName(method.getName());
        session.set(new SauceSession(options.get()));

        RemoteWebDriver driver = getSession().start();

        Browser browser = new Browser(driver);
        PageObject.setBrowser(browser);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        getSession().stop(result.isSuccess());
    }
}