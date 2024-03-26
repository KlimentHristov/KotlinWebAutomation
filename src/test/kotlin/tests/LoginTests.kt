package tests
import constants.Constants.LOGOUT_MESSAGE
import org.testng.Assert
import org.testng.annotations.Test
import constants.Constants.MAIN_URL
import constants.Constants.MAIN_USER
import org.testng.ITestListener
import org.testng.annotations.AfterClass
import org.testng.annotations.Listeners
import pages.HomePage
import pages.LoginPage
import pages.ResultPage

class LoginTests: BaseTest() {

    lateinit var lp: LoginPage
    lateinit var hp: HomePage
    lateinit var rp: ResultPage
    init {
        lp = LoginPage(driver)
        hp = HomePage(driver)
        rp = ResultPage(driver)
    }
    @AfterClass
    fun quitDriver(){
        tearDown()
    }
    @Test(priority = 1)
    fun loginWithValidEmailAndPassword(){
        driver.get(MAIN_URL)
        lp.loginWithValidCredentials()
        hp.popUpMessageAcceptBtn.click()
        Assert.assertEquals(hp.loggedEmail.text,lp.myEmail)
        Assert.assertEquals(hp.logoTextForLoggedUser.text, MAIN_USER)
    }
    @Test(priority = 3)
    fun logoutFromWebsite(){
        hp.loggedEmail.isDisplayed
        hp.panelMenu.click()
        hp.clickElementContainsText("Изход",5)
        Assert.assertEquals(rp.messageSuccessOperation.text.trim(), LOGOUT_MESSAGE)
    }
}