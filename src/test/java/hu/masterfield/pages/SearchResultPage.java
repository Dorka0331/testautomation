package hu.masterfield.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static com.codeborne.selenide.Selenide.$;

public class SearchResultPage extends BasePage{
    SelenideElement header = $(By.xpath("//h1"));
    SelenideElement productName = $(By.xpath("//li[@class = 'product-list--list-item first']"));

    public void headerContains(String searchWord) {
        header.shouldHave(Condition.ownText(searchWord));
    }

    public void productNameContains(String searchWord) {
        String innerText = productName.getAttribute("innerText");
        assertTrue(innerText.contains(searchWord));
    }
}
