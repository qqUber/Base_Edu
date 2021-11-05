package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class DeletePersonTests extends TestBase{
    @Test
    public void testDelPersons() {
        app.getNavHelper().gotoHome();
        app.getPersonHelper().selectPerson();
        app.getPersonHelper().deletePersons();
        app.getPersonHelper().acceptAlert();
    }
}
