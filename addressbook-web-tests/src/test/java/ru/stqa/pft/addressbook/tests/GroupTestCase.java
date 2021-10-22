package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupTestCase extends TestBase {

    @Test
    public void testGroupAdd() {
        app.getNavHelper().gotoGroupPage();
        app.getGroupHelper().initGroupAdd();
        app.getGroupHelper().fillGroupForm(new GroupData("i bought  one mdma", "and so what", "they are know what you do"));
        app.getGroupHelper().returnToGroupPage();
    }

}
