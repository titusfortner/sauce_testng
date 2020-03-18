package test.java.tests;

import com.saucelabs.framework.Browser;
import com.saucelabs.framework.pages.PageObject;
import com.saucelabs.saucebindings.SauceOptions;
import com.saucelabs.saucebindings.SauceSession;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    public SauceSession session;

    @BeforeMethod
    public void setup() {
        SauceOptions options = new SauceOptions();
        session = new SauceSession(options);
        RemoteWebDriver driver = session.start();
        Browser browser = new Browser(driver);
        PageObject.setBrowser(browser);
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws Exception {
        session.stop(result.isSuccess());
    }
}