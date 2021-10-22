package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavHelper extends HelperBase {
    public NavHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {
        click(By.xpath("//a[contains(text(),'groups')]"));
    }

    public void gotoAddnew() {
        click(By.xpath("//a[contains(text(),'add new')]"));
    }

}
