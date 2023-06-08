package hu.masterfield.layoutTest;

import com.galenframework.api.Galen;
import com.galenframework.junit.GalenJUnitTestBase;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.TestReport;
import com.galenframework.reports.model.LayoutReport;
//import hu.masterfield.TestDevice;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;
import java.util.*;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LayoutTest extends GalenJUnitTestBase {

    static WebDriver driver;

    static WebDriverWait wait;

//    static TestDevice device = new TestDevice("desktop", new Dimension(1024, 800), Arrays.asList("desktop"));
    static TestDevice device = new TestDevice("mobile", new Dimension(360, 640), Arrays.asList("mobile"));

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();

        /* Nexus 5 */
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "Nexus 5");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        driver = new  ChromeDriver(chromeOptions); // for mobile

        // driver = new ChromeDriver(); // desktop
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().setSize(device.getScreenSize());

//        LayoutTest test = new LayoutTest();
//        test.createDriver();
    }

    @Test
    @DisplayName("Login page layout test")
    public void TC2() throws IOException {
        driver.get("https://www.saucedemo.com");

        WebElement loginButton = wait.until(driver -> driver.findElement(By.xpath("//span[text() = 'Bejelentkezés']")));
        loginButton.click();
        WebElement header = wait.until(driver -> driver.findElement(By.xpath("//h1")));
        assertEquals("Bejelentkezés", header.getText());

        // Galen layout checking
        layoutReport = Galen.checkLayout(driver, "/specs/loginPageLayout.gspec", device.getTags());
    }

    @AfterAll
    public static void cleanup() {
        reportUpdate();
        driver.quit();
    }

    @Override
    public WebDriver createDriver() {
        super.driver.set(driver);
        super.report.set(new TestReport());
        return driver;
    }

    static LayoutReport layoutReport;

    public static void reportUpdate() {
        try {
            List<GalenTestInfo> tests = new LinkedList<>();
            GalenTestInfo test = GalenTestInfo.fromString("TescoShop login page layout test");
            test.getReport().layout(layoutReport, "Verify login page layout");
            tests.add(test);
            new HtmlReportBuilder().build(tests, "target/galen-html-reports");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
