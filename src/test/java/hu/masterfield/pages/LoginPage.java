package hu.masterfield.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends BasePage{
    SelenideElement header = $(By.xpath("//h1"));
    SelenideElement emailField = $(By.xpath("//input[@type = 'email']"));
    SelenideElement passwordField = $(By.xpath("//input[@type = 'password']"));
    SelenideElement loginButton = $(By.xpath("//button[@type = 'submit']"));


    public void checkHeader(String text){
        assertEquals(text, header.getText());
    }

    public void addEmail(String email){
        emailField.setValue(email);
    }

    public void addPassword(String password){
        passwordField.setValue(password);
    }

    public MainPage login(){
        loginButton.click();
        return new MainPage();
    }
}
