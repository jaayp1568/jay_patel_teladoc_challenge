package com.sample.test.demo.tests;

import org.testng.annotations.Test;
import com.sample.test.demo.TestBase;

public class WebtablesTest extends TestBase {

    @Test(description = "Add a user and validate the user has been added to the table")
    public void add_NewUser() {
    	WebtablesPage pizzaOrder = new WebtablesPage(driver);
        pizzaOrder.addUserNameInfo("ArsFN","ArsLN");
        pizzaOrder.addUserInfo("ArsUN","ArsPSWD");
        pizzaOrder.addCustomerInfo("Company AAA","Admin");
        pizzaOrder.addContactinfo("asadf@asd.cz","777888444");
        pizzaOrder.saveUserAndValidation();
    }

    @Test(description = "Delete user User Name: novak and validate user has been deleted")
    public void delete_User() {
    	WebtablesPage pizzaOrder = new WebtablesPage(driver);
        pizzaOrder.deleteUser();
    }
}
