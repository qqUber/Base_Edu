package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavHelper extends HelperBase {
    public NavHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {
        if (isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new"))) {
            return;
        }
        click(By.xpath("//a[contains(text(),'groups')]"));
    }

    public void gotoAddnew() {
        click(By.xpath("//a[contains(text(),'add new')]"));
    }

    public void gotoHome() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.xpath("//a[contains(text(),'home')]"));
    }
}