package tests.login
import org.testng.Assert
import org.testng.annotations.Test
import pages.HomePage
import pages.LoginPage
import pages.ResultPage
import tests.BaseTest

class AuthenticateTest: BaseTest() {

    lateinit var lp: LoginPage
    lateinit var hp: HomePage
    lateinit var rp: ResultPage
    init {
        lp = LoginPage(driver)
        hp = HomePage(driver)
        rp = ResultPage(driver)
        loginWithEmailAndPassword()
    }

    private fun loginWithEmailAndPassword(){
        driver.get("http://kikotesting.inv.bg/login")
        lp.loginWithValidCredentials()
        hp.popUpMessageAcceptBtn.click()
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
                " ",rp.messageForSuccessCreatedClient.text)

    }


    @Test(priority = 2)
    fun deleteCreatedClient(){
        hp.menuClients.click()
        hp.checkboxClientByText(hp.savedRandomName)
        hp.deleteBtn.click()
        // driver.switchTo().activeElement().click()
        hp.handleDeletePopup("Accept")

        Assert.assertTrue(rp.messageError.text.contains("Избраните клиенти бяха изтрити успешно."))
    }








}