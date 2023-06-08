package hu.masterfield.steps;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import hu.masterfield.pages.MainPage;
import hu.masterfield.pages.SearchResultPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TescoShopSteps {
    @BeforeAll
    public static void setUp() {
        Configuration.reportsFolder = "target/reports";

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--incognito");
        Configuration.browserCapabilities = options;
    }

    MainPage mainPage = new MainPage();
    SearchResultPage searchResultPage = new SearchResultPage();
    @Given("open main page")
    public void openMainPage() {
        mainPage.openPage();
    }

    @And("accept cookies")
    public void acceptCookies() {
        mainPage.acceptCookies();
    }

    @When("change the language to {string}")
    public void changeTheLanguageTo(String lang) {
        mainPage.changeLanguage(lang);
    }

    @Then("it shows elements in {string}")
    public void itShowsElementsIn(String lang) {
        SelenideElement groceriesMenuItem = $(By.className("nav-item__link nav-item__link--right-aligned main-level-nav-item__link--open"));
        if(lang.equals("magyar")){
            assertEquals("Bevásárlás",groceriesMenuItem.getText());
        }
        if(lang.equals("english")){
            assertEquals("Groceries",groceriesMenuItem.getText());
        }

    }

    @When("fill the searchBar with {string}")
    public void fillTheSearchBarWith(String searchWord) {
        mainPage.addSearchWord(searchWord);
    }

    @And("click on the search icon")
    public SearchResultPage clickOnTheSearchIcon() {
        return mainPage.clickOnSearchIcon();
    }

    @Then("header contains the word {string}")
    public void headerContainsTheWord(String searchWord) {
        searchResultPage.headerContains(searchWord);
    }

    @And("a product's name contains the {string}")
    public void aProductSNameContainsThe(String searchWord) {
        searchResultPage.productNameContains(searchWord);
    }
}
