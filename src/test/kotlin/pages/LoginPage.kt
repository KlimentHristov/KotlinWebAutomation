package pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class LoginPage(driver: WebDriver): BasePage(driver) {

    init {
        PageFactory.initElements(driver, this)
    }

    val myEmail: String = "kikotestsecure@gmail.com"
    private val myPass: String = "Kiko123@"

    @FindBy(xpath = "//*[@id=\"loginusername\"]") private lateinit var emailInput: WebElement
    @FindBy(id = "loginpassword") private lateinit var passwordInput: WebElement
    @FindBy(xpath = "//*[@id=\"loginsubmit\"]") private lateinit var submitButton: WebElement

    @FindBy(className = "selenium-forgotten-page")  lateinit var forgotPasswordBtn: WebElement



    private fun enterEmail(){
        waitToBeVisible(emailInput)
        emailInput.sendKeys(myEmail)
    }
    private fun enterPassword(){
        waitToBeVisible(passwordInput)
        passwordInput.sendKeys(myPass)
    }
    private fun clickSubmitBtn(){
        submitButton.isDisplayed
        submitButton.click()
    }

    fun loginWithValidCredentials(){
        enterEmail()
        enterPassword()
        clickSubmitBtn()
    }

}