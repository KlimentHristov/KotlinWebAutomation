package pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class ResultPage(driver: WebDriver): BasePage(driver){

    init {
        PageFactory.initElements(driver, this)
    }

    @FindBy(xpath = "//*[@id=\"okmsg\"]") lateinit var messageSuccessOperation: WebElement
    @FindBy(xpath = "//*[@id=\"error\"]") lateinit var messageError: WebElement


}