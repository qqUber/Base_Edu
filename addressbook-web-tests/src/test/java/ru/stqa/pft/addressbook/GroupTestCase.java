package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class GroupTestCase extends TestBase {

    @Test
    public void testGroupAdd() {
      gotoGroupPage();
      initGroupAdd();
      wd.findElement(By.xpath("//div[@id='content']/form/label")).click();
      fillGroupForm(new GroupData("i bought  one mdma", "and so what", "they are know what you do"));
      returnToGroupPage();
    }

}
