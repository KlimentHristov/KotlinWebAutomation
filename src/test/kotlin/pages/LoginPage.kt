package pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class LoginPage(private val driver: WebDriver) {

    init {
        PageFactory.initElements(driver, this)
    }

    private val myEmail: String = "kliment.hristov@gmail.com"
    private val myPass: String = "Kiko123@"

    @FindBy(xpath = "//*[@id=\"loginusername\"]") private lateinit var emailInput: WebElement
    @FindBy(id = "loginpassword") private lateinit var passwordInput: WebElement
    @FindBy(xpath = "//*[@id=\"loginsubmit\"]") private lateinit var submitButton: WebElement

    @FindBy(xpath = "//*[@id=\"okmsg\"]")  lateinit var successLogoutMessage: WebElement
    @FindBy(className = "selenium-forgotten-page")  lateinit var forgotPasswordBtn: WebElement

    private fun enterEmail(){
        emailInput.isDisplayed
        emailInput.sendKeys(myEmail)
    }
    private fun enterPassword(){
        passwordInput.isDisplayed
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