package hu.masterfield.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;

import io.cucumber.java.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.WebDriverRunner.url;

public class BasePage {

    public void isLoaded(SelenideElement element) {
        element.shouldBe(visible);
    }

    public void isInteractable(SelenideElement element) {
        element.shouldBe(interactable);
    }

    public String getURL() {
        return url();
    }


}
