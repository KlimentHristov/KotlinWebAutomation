package tests.login
import constants.Constants.LOGOUT_MESSAGE
import org.testng.Assert
import org.testng.annotations.Test
import constants.Constants.MAIN_URL
import constants.Constants.MAIN_USER
import org.testng.annotations.AfterClass
import org.testng.annotations.AfterMethod
import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeMethod
import org.testng.annotations.BeforeTest
import pages.HomePage
import pages.LoginPage
import pages.ResultPage
import tests.BaseTest
import updater.Updater

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
    @Test(priority = 2)
    fun logoutFromWebsite(){
        hp.loggedEmail.isDisplayed
        hp.panelMenu.click()
        hp.clickElementContainsText("Изход",5)
        Assert.assertEquals(rp.messageSuccessOperation.text.trim(), LOGOUT_MESSAGE)
    }
}