package tests.login
import org.testng.Assert
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import pages.HomePage
import pages.LoginPage
import tests.BaseTest

class AuthenticateTest: BaseTest() {

    private lateinit var lp: LoginPage
    private lateinit var hp: HomePage


    @BeforeMethod
    fun config(){
        initializeDriver()
        lp = LoginPage(driver)
        hp = HomePage(driver)
        loginWithEmailAndPassword()
    }

    private fun loginWithEmailAndPassword(){
        driver.get("http://kikotesting.inv.bg/login")

        lp.loginWithValidCredentials()
        lp.popUpMessageAcceptBtn.click()
    }

    @Test(priority = 1)
    fun createClient(){
        val currentTitle = "Система за фактуриране - Kikotesting"
        val currentURL = "https://kikotesting.inv.bg/home"

        Assert.assertTrue(driver.title.contains(currentTitle))
        Assert.assertTrue(driver.currentUrl.contains(currentURL))

        hp.menuClients.click()
        hp.createNewClient()
        Assert.assertEquals("Клиентът е добавен успешно.\n" +
                " ",hp.messageForSuccessCreatedClient.text)
        tearDown()
    }

    @Test(priority = 2)
    fun tryToCreateDublicate(){
        hp.menuClients.click()
        hp.createNewClient()
        Assert.assertTrue(hp.messageForDublicatedClient.text.contains("Вече съществува клиент с въведеното име и идентификатор"))
    }






}