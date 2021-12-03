package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class AppManager {
    private final Properties properties;
    private WebDriver wd;

    private final String browser;
    private RegHelper regHelper;
    private FtpHelper ftp;
    public MailHelper mailHelper;
    private JamesHelper jamesHelper;
    private ManageHelper userHelper;
    private DbHelper dbHelper;
    private SessionHelper sessionHelper;

    public AppManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(String.format("src/test/resources/%s.properties", target)));
        dbHelper = new DbHelper();
    }

    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }

    public HttpSession httpSession() {
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public RegHelper reg() {
        if (regHelper == null) {
            regHelper = new RegHelper(this);
        }
        return regHelper;
    }

    public FtpHelper ftp() {
        if (ftp == null) {
            ftp = new FtpHelper(this);
        }
        return ftp;
    }

    public WebDriver getDriver() {
        if (wd == null) {
            switch (browser) {
                case BrowserType.FIREFOX:
                    wd = new FirefoxDriver();
                    break;
                case BrowserType.CHROME:
                    wd = new ChromeDriver();
                    break;
                case BrowserType.EDGE:
                    wd = new EdgeDriver();
                    break;
            }
            Objects.requireNonNull(wd).manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            wd.get(properties.getProperty("web.baseUrl"));
        }
        return wd;
    }

    public MailHelper mail() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public JamesHelper james() {
        if (jamesHelper == null) {
            jamesHelper = new JamesHelper(this);
        }
        return jamesHelper;
    }

    public ManageHelper user() {
        if (userHelper == null) {
            userHelper = new ManageHelper(this);
        }
        return userHelper;
    }

    public DbHelper db() {
        return dbHelper;
    }

    public SessionHelper session() {
        if (sessionHelper == null) {
            sessionHelper = new SessionHelper(this);
        }
        return sessionHelper;
    }

}
