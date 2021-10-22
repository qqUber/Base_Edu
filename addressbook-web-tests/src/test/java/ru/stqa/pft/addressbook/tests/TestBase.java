package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.appmanager.AppManager;

public class TestBase {

    protected final AppManager app = new AppManager();

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        app.initFirefox();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }
}