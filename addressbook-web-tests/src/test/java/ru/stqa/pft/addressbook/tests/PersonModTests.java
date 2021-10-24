package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.fillAddnewEnum;

public class PersonModTests extends TestBase{
    @Test
    public void testModPerson(){
        app.getPersonHelper().selectPerson();
        app.getPersonHelper().editPerson();
        app.getPersonHelper().fillAddnew(new fillAddnewEnum("Common", "Join", "Mustroi", "nicknick", "+74959990055", "+71110099666", "createmyrules@com.tocom", "September", "2000", "Moscow"));
        app.getPersonHelper().updatePerson();
        app.getPersonHelper().returnHome();
    }
}
