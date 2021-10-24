package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.fillAddnewEnum;

public class AddPersonTests extends TestBase {

    @Test
    public void testAddPerson() {
        app.getNavHelper().gotoAddnew();
        app.getPersonHelper().fillAddnew(new fillAddnewEnum("Trsu", "dddaaa", "wwwqq", "nicknick", "+74959990055", "+71110099666", "createmyrules@com.tocom", "September", "2000", "Moscow"));
        app.getPersonHelper().submitAdd();
        app.getPersonHelper().returnHome();
    }
}
