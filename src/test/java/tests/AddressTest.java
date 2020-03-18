package test.java.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.java.tests.apis.AddressAPI;
import test.java.tests.apis.AuthenticationAPI;
import test.java.tests.data.AddressData;
import test.java.tests.data.UserData;
import test.java.tests.pages.*;

public class AddressTest extends BaseTest {
    @BeforeMethod
    public void authenticateUser() {
        AuthenticationAPI authenticationAPI = new AuthenticationAPI();
        UserData userData = authenticationAPI.createUser();

        HomePage homePage = new HomePage();
        homePage.visit();
        homePage.addCookie("remember_token", userData.getId());
    }

    @Test
    public void createsUIValidatesAPI() {
        NewAddressPage newAddressPage = new NewAddressPage();
        newAddressPage.visit();

        AddressData addressData = newAddressPage.createAddress();

        ShowAddressPage showAddressPage = new ShowAddressPage();
        String id = showAddressPage.getID();

        AddressAPI addressAPI = new AddressAPI();
        AddressData foundAddress = addressAPI.getAddress(id);

        Assert.assertEquals(addressData, foundAddress);
    }

    @Test
    public void createsAPIValidatesShowUI() {
        AddressAPI addressAPI = new AddressAPI();
        AddressData addressData = addressAPI.createAddress();

        ShowAddressPage showAddressPage = new ShowAddressPage();
        showAddressPage.visit(addressData);

        Assert.assertTrue(showAddressPage.isAddress(addressData));
    }

    @Test
    public void createsAPIValidatesListUI() {
        AddressAPI addressAPI = new AddressAPI();
        AddressData addressData = addressAPI.createAddress();

        ListAddressPage listAddressPage = new ListAddressPage();
        listAddressPage.visit();

        Assert.assertTrue(listAddressPage.hasAddress(addressData));
    }

    @Test
    public void createsAPIEditsUIValidatesAPI() {
        AddressAPI addressAPI = new AddressAPI();
        AddressData addressData = addressAPI.createAddress();

        EditAddressPage editAddressPage = new EditAddressPage();
        editAddressPage.visit(addressData);

        AddressData editedAddressData = new AddressData();
        editAddressPage.editAddress(editedAddressData);

        AddressData foundAddress = addressAPI.getAddress(addressData.getId());

        Assert.assertEquals(foundAddress, editedAddressData);
    }
}
