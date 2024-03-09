package pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class ResultPage(private val driver: WebDriver) {

    init {
        PageFactory.initElements(driver, this)
    }

    @FindBy(xpath = "//*[@id=\"okmsg\"]") lateinit var messageForSuccessCreatedClient: WebElement
    @FindBy(xpath = "//*[@id=\"error\"]") lateinit var messageError: WebElement


}