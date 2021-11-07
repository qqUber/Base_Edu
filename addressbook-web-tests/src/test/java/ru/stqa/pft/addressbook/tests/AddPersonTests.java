package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class AddPersonTests extends TestBase {

    @Test
    public void testAddPerson() {
        app.getNavHelper().gotoAddnew();
        app.getPersonHelper().fillAddnew(new ContactData("Trsu", "dddaaa", "wwwqq", "nicknick", "+74959990055", "+71110099666", "createmyrules@com.tocom", "September", "2000", "Moscow", "3"), true);
        app.getPersonHelper().submitAdd();
        app.getPersonHelper().returnHome();
    }
}
