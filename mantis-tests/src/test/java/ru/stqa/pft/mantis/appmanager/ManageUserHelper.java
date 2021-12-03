package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class ManageUserHelper extends HelperBase  {

  public ManageUserHelper(ApplicationManager app) {
    super(app);
  }

  public void resetUserPassword(int userId) {
    openManageUserPage();
    chooseUser(userId);
    resetPassword();
  }

  private void openManageUserPage() {
    wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
  }

  private void chooseUser(int userId) {
    click(By.cssSelector(String.format("a[href='manage_user_edit_page.php?user_id=%s']", userId)));
  }

  private void resetPassword() {
    click(By.cssSelector("form[id='manage-user-reset-form']>fieldset>span>input[type='submit']"));
  }
}
