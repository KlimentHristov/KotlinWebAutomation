package tests.login
import org.testng.Assert
import org.testng.annotations.Test
import constants.Constants.MAIN_URL
import constants.Constants.MAIN_USER
import pages.HomePage
import pages.LoginPage
import tests.BaseTest

class LoginTests: BaseTest() {

    lateinit var lp: LoginPage
    lateinit var hp: HomePage
    init {
        lp = LoginPage(driver)
        hp = HomePage(driver)
    }

    @Test(priority = 1)
    fun loginWithValidEmailAndPassword(){
        driver.get(MAIN_URL)
        lp.loginWithValidCredentials()
        hp.popUpMessageAcceptBtn.click()
        Assert.assertEquals(hp.loggedEmail.text,lp.myEmail)
        Assert.assertEquals(hp.logoTextForLoggedUser.text, MAIN_USER)
        tearDown()
    }
}