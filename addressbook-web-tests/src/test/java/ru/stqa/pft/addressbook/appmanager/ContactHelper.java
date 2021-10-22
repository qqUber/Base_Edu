package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.fillAddnewEnum;

public class ContactHelper extends HelperBase {
    private boolean acceptNextAlert;

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillAddnew(fillAddnewEnum fillAddnewEnum) {
        type(By.name("firstname"), fillAddnewEnum.getFname());
        type(By.name("middlename"), fillAddnewEnum.getMname());
        type(By.name("lastname"), fillAddnewEnum.getLname());
        type(By.name("nickname"), fillAddnewEnum.getNick());
        click(By.name("theform"));
        type(By.name("home"), fillAddnewEnum.getHphone());
        type(By.name("mobile"), fillAddnewEnum.getMphone());
        type(By.name("email"), fillAddnewEnum.getEmail());
        click(By.name("bday"));
        new Select(wd.findElement(By.name("bday"))).selectByVisibleText("18");
        wd.findElement(By.name("bmonth")).click();
        new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(fillAddnewEnum.getBmonth());
        type(By.name("byear"), fillAddnewEnum.getYear());
        click(By.name("new_group"));
        // new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(fillAddnewEnum.getGetGroup());
        type(By.name("phone2"), fillAddnewEnum.getCity());
        type(By.name("address2"), "fullAddress");
        click(By.id("container"));
    }
    //wd.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

    public void returnHome() {
        wd.findElement(By.linkText("home page")).click();
    }

    public void DeleteAllPerson() {
        acceptNextAlert = true;
        wd.findElement(By.xpath("//input[@id='MassCB']")).click();
        wd.findElement(By.xpath("//input[@value='Delete']")).click();
    }

    public void submitAdd() {
        click(By.xpath("//input[@name='submit']"));
    }

    public boolean isAcceptNextAlert() {
        return acceptNextAlert;
    }
}