package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePassTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testChangePass() throws IOException {
        long now = System.currentTimeMillis();
        String newPass = "NN_" + now;

        Users users = app.db().usersWithoutAdmin();
        UserData user = users.iterator().next();

        app.session().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        app.user().resetUserPassword(user.getId());

        List<MailMessage> mailMessage = app.mail().waitForMail(1, 10000);
        String confirmationLink = app.mail().findConfirmationLink(mailMessage, user.getEmail());
        app.reg().finish(confirmationLink, newPass);

        assertTrue(app.httpSession().login(user.getName(), newPass));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
