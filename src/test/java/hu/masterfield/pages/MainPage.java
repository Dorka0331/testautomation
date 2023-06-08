package hu.masterfield.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPage extends BasePage{

    SelenideElement acceptCookieButton = $(By.xpath("//span[text() = 'Minden Cookie elfogadása']"));
    //SelenideElement currentLanguage = $(By.className("base-components__BaseElement-sc-1mosoyj-0 styled__TextSpan-rsekm1-4 oznwo cUpzQU beans-button__text"));
    SelenideElement currentLanguage = $(By.id("utility-header-language-switch-link"));

    SelenideElement searchBar = $(By.id("search-input"));

    SelenideElement searchButton = $(By.xpath("//button[@class = 'styled__BaseButton-rsekm1-0 styled__PrimaryButton-rsekm1-2 hEjuKw geeWOF search-bar__submit beans-button__container']"));
    public void changeLanguage(String lang){
            if(lang.equals("magyar")) {
                if(currentLanguage.getText().equals("English")){}
                else currentLanguage.click();
            }

            if(lang.equals("english")) {
                if(currentLanguage.getText().equals("Magyar")){}
                else currentLanguage.click();
            }
        }

    public void openPage(){
        open("https://bevasarlas.tesco.hu/groceries/hu-HU");
        isLoaded(acceptCookieButton);

    }
    public void acceptCookies() {
        acceptCookieButton.click();
    }

    public void addSearchWord(String searchWord) {
        searchBar.setValue(searchWord);
    }

    public SearchResultPage clickOnSearchIcon() {
        searchButton.click();
        return new SearchResultPage();
    }

}
