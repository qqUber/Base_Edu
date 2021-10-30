package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {

    public SessionHelper(WebDriver wd) {
        super(wd);
    }

    public void Login(String username, String password) {
        //Login
        type(By.xpath("//input[@name='user']"), username);
        type(By.xpath("//input[@name='pass']"), password);
        click(By.xpath("//input[@value='Login']"));
    }
}

