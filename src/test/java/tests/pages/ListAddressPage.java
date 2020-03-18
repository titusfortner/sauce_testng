package test.java.tests.pages;

import com.saucelabs.framework.elements.Element;
import com.saucelabs.framework.pages.OnPage;
import org.openqa.selenium.By;
import test.java.tests.data.AddressData;

@OnPage(path="/addresses")
public class ListAddressPage extends BasePage {

    private Element notice = browser.element(By.cssSelector("[data-test=notice]"));

    public boolean hasAddress(AddressData addressData) {
        return hasAddress(addressData.getId());
    }

    public boolean hasAddress(String id) {
        Element showID = browser.element(By.cssSelector("[data-test=show-" + id + "]"));
        return showID.doesExist();
    }

    public void delete(String id) {
        Element destroyID = browser.element(By.cssSelector("[data-test=destroy-" + id + "]"));
        destroyID.click();
        browser.switchTo().alert().accept();
    }

    public boolean hasSuccessMessage() {
        return isOnPage() && notice.isVisible();
    }
}
