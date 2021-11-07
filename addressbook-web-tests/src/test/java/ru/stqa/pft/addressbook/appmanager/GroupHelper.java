package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupHelper extends HelperBase {
    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    private void submitAdd() {
        click(By.name("submit"));
    }

    public void initGroupAdd() {
        click(By.name("new"));
    }

    public void deleteSelGroup() {
        click(By.name("delete"));
    }

    public void selectGroup() {
        click(By.name("selected[]"));
    }

    public void initGroupMod() {
        click(By.xpath("//input[@name='edit']"));
    }

    public void submitGroupMod() {
        click(By.xpath("//input[@name='submit']"));
    }

    public void updateGroup() {
        click(By.xpath("//input[@name='update']"));
    }

    public void createGroup(GroupData group) {
        initGroupAdd();
        fillGroupForm(group);
        submitGroupMod();
        returnToGroupPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }
}
