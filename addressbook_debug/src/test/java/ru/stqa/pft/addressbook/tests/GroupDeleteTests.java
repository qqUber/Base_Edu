package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeleteTests extends TestBase {
    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData("i bought  one mdma", null, null));
        }
    }
    @Test
    public void testDeleteGroup() {
        List<GroupData> before = app.group().list();
        int index = before.size() - 1;
        app.group().delete(index);

        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
            Assert.assertEquals(before, after);
        }

}