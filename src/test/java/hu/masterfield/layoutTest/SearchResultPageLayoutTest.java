package hu.masterfield.layoutTest;

import com.codeborne.selenide.SelenideElement;
import com.galenframework.api.Galen;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.model.LayoutReport;
import hu.masterfield.pages.MainPage;
import hu.masterfield.pages.SearchResultPage;
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
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchResultPageLayoutTest {//} extends GalenJUnitTestBase {

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
    @DisplayName("SearchResult page layout test")
    public void searchResultPageLayoutTest() throws IOException {
        driver.get("https://bevasarlas.tesco.hu/groceries/hu-HU/");
        WebElement searchBar = wait.until(driver -> driver.findElement(By.id("search-input")));
        searchBar.sendKeys("alma");
        WebElement searchButton = wait.until(driver -> driver.findElement(By.xpath("//button[@class = 'styled__BaseButton-rsekm1-0 styled__PrimaryButton-rsekm1-2 hEjuKw geeWOF search-bar__submit beans-button__container']")));
        searchButton.click();
        WebElement header = wait.until(driver -> driver.findElement(By.xpath("//h1")));
        assertTrue(header.getText().contains("alma"));


        // Galen layout checking
        layoutReport = Galen.checkLayout(driver, "/specs/searchResultPageLayout.gspec", Arrays.asList(new String[]{"desktop"}));
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
            GalenTestInfo test = GalenTestInfo.fromString("TescoShop search result page layout test");
            test.getReport().layout(layoutReport, "Verify search result page layout");
            tests.add(test);
            new HtmlReportBuilder().build(tests, "target/galen-html-reports");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
