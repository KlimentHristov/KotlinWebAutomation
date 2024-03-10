package tests.client

import org.testng.Assert
import org.testng.annotations.Test
import constants.Constants.HOME_PAGE_TITLE
import constants.Constants.HOME_PAGE_URL
import constants.Constants.MAIN_URL
import pages.HomePage
import pages.LoginPage
import pages.ResultPage
import tests.BaseTest

class ClientTests: BaseTest() {
    lateinit var lp: LoginPage
    lateinit var hp: HomePage
    lateinit var rp: ResultPage
    init {
        lp = LoginPage(driver)
        hp = HomePage(driver)
        rp = ResultPage(driver)
    }
    @Test(priority = 1)
    fun createClient(){
        driver.get(MAIN_URL)
        lp.loginWithValidCredentials()

        Assert.assertTrue(driver.currentUrl.contains(HOME_PAGE_URL))
        Assert.assertTrue(driver.title.contains(HOME_PAGE_TITLE))

        hp.menuClients.click()
        hp.createNewClient()
        Assert.assertEquals("Клиентът е добавен успешно.\n" +
                " ",rp.messageSuccessOperation.text)
    }

    @Test(priority = 2)
    fun deleteCreatedClient(){
        hp.menuClients.click()
        hp.checkboxClientByText(hp.savedRandomName)
        hp.deleteBtn.click()
        // driver.switchTo().activeElement().click()
        hp.handleDeletePopup("Accept")
        Assert.assertTrue(rp.messageSuccessOperation.text.contains("Избраните клиенти бяха изтрити успешно."))
        tearDown()
    }
}