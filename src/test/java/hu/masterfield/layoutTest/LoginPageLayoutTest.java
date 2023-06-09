package hu.masterfield.layoutTest;

import com.galenframework.api.Galen;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.model.LayoutReport;
//import hu.masterfield.TestDevice;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
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

public class LoginPageLayoutTest {//} extends GalenJUnitTestBase {

     static WebDriver driver;
     static WebDriverWait wait;

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        driver = new  ChromeDriver(options); // for mobile*/

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    @Test
    @DisplayName("Login page layout test")
    public void loginPageLayoutTest() throws IOException {
        driver.get("https://bevasarlas.tesco.hu/groceries/hu-HU/");

        WebElement loginButton = wait.until(driver -> driver.findElement(By.xpath("//span[text() = 'Bejelentkezés']")));
        loginButton.click();
        WebElement header = wait.until(driver -> driver.findElement(By.xpath("//h1")));
        assertEquals("Bejelentkezés", header.getText());

        // Galen layout checking
        layoutReport = Galen.checkLayout(driver, "/specs/loginPageLayout.gspec", Arrays.asList(new String[]{"desktop"}));
    }

    @AfterAll
    public static void cleanup() {
        reportUpdate();
        driver.quit();
    }

    /*@Override
    public WebDriver createDriver() {
        super.driver.set(driver);
        super.report.set(new TestReport());
        return driver;
    }
*/
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
