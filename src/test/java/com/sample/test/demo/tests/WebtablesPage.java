package com.sample.test.demo.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

import com.sample.test.demo.Services;

public class WebtablesPage {
	
	WebDriver driver;
	//WebElements Initialization
    String hyperlink_AddUser = "//button[text()=' Add User']";
    String textField_Firstname = "//input[@name='FirstName']";
    String textField_LastName = "//input[@name='LastName']";
    String textField_UserName = "//input[@name='UserName']";
    String textField_Password = "//input[@name='Password']";
    String radioButton_Customer = "//label[text()='Company AAA']/input";
    String dropdown_Role = "//select[@name='RoleId']";
    String textField_Email = "//input[@name='Email']";
    String textField_Phone = "//input[@name='Mobilephone']";
    String button_Save = "//button[text()='Save']";
    String webElements_SaveedRecord = "//table/tbody/tr[1]/td";
    String webElements_ListUserNames = "//table/tbody/tr/td[3]";
    String button_OkDelete = "//button[text()='OK']";
  
  
    //Instance Variables
    String array_FirstName;
    String array_LastName;
    String array_UserName;
    String array_Password;
    String array_Customer;
    String array_Role;
    String array_Email;
    String array_Phone;   
    
    public WebtablesPage(WebDriver driver) {
        this.driver = driver;
    }
   
    public void addUserNameInfo(String firstName,String lastName) {
    	Services service = new Services(driver);
    	service.click(hyperlink_AddUser);	
    	service.type(By::xpath, textField_Firstname, firstName);
    	service.type(By::xpath, textField_LastName, lastName);	
    	array_FirstName=firstName;
    	array_LastName=lastName;
    }
    
    public void addUserInfo(String UserName,String Password) {
    	Services service = new Services(driver);
    	service.type(By::xpath, textField_UserName, UserName);
    	service.type(By::xpath, textField_Password, Password);
    	array_UserName=UserName;
    	array_Password=Password;
    }
    
    public void addCustomerInfo(String customer,String role) {
    	Services service = new Services(driver);
    	service.click(radioButton_Customer);
    	service.selectOptionByText(role,dropdown_Role);
    	array_Customer=customer;
    	array_Role=role;
    	
    }
    
    public void addContactinfo(String email,String phone) {
    	Services service = new Services(driver);
    	service.type(By::xpath, textField_Email, email);
    	service.type(By::xpath, textField_Phone, phone);
    	array_Email=email;
    	array_Phone=phone;
    	
    }
    
    public void saveUserAndValidation() {
    	Services service = new Services(driver);
    	service.click(button_Save);
    	//To validate that the saved record available in the table
    	List<WebElement> elements = service.getWebElements(webElements_SaveedRecord);
    	String[] userData ={array_FirstName,array_LastName,array_UserName,"",array_Customer,array_Role,array_Email,array_Phone};
    	
    	for(int i=0;i<8;i++) {
    		if(i==4) {
    			continue;
    		}
    		Assert.assertEquals(elements.get(i).getText(), userData[i]);
//    		System.out.println(elements.get(i).getText());
//    		System.out.println(userData[i]);
    	}
    	
    }
    
    public void deleteUser() {
    	Services service = new Services(driver);
    	
    	List<WebElement> elements = service.getWebElements(webElements_ListUserNames);
    	    	
    	for(int i=0;i<elements.size();i++) {
//    		System.out.println(elements.get(i).getText());
    		if(elements.get(i).getText().equals("novak")) {
    			i++;
    			driver.findElement(By.xpath("//table/tbody/tr["+i+"]/td[11]/button")).click();
    			service.click(button_OkDelete);
    			break;
    			
    		}
    		
    	}
    	
    	List<WebElement> elementsPostDeleting = service.getWebElements(webElements_ListUserNames);
    	
    	boolean recordAvailable = false;
    	
    	for(int i=0;i<elementsPostDeleting.size();i++) {
//    		System.out.println(elementsPostDeleting.get(i).getText());
    		if(elementsPostDeleting.get(i).getText().equals("novak")) {
    			recordAvailable = true;
    			break;
    		}
    	}
    	
		Assert.assertFalse(recordAvailable);
    }
}
