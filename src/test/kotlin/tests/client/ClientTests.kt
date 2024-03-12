package tests.client

import org.testng.Assert
import org.testng.annotations.Test
import constants.Constants.HOME_PAGE_TITLE
import constants.Constants.HOME_PAGE_URL
import constants.Constants.MAIN_URL
import org.openqa.selenium.By
import pages.ClientPage
import pages.HomePage
import pages.LoginPage
import pages.ResultPage
import tests.BaseTest
import updater.Updater

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
        hp.createDefaultClient()
        Assert.assertEquals("Клиентът е добавен успешно.\n" +
                " ",rp.messageSuccessOperation.text)
    }
    @Test(priority = 2)
    fun deleteCreatedClient(){
        hp.menuClients.click()
        Updater.getUpdatedCheckboxList(driver)?.get(0)?.click()
        hp.deleteBtn.click()

        hp.handleDeletePopup("Accept")
        Assert.assertTrue(rp.messageSuccessOperation.text.
            contains("Избраните клиенти бяха изтрити успешно."))
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"emptylist\"]")).text.contains("Все още нямате добавени клиенти."))

        Assert.assertEquals(Updater.getUpdatedCheckboxList(driver)?.size,0)
    }
    @Test(priority = 3)
    fun createClientExtendable(){
        hp.createExtendableClient()
        Assert.assertEquals("Клиентът е добавен успешно.\n" +
                " ",rp.messageSuccessOperation.text)
    }
    @Test(priority = 4)
    fun verifyExtendableClientIsCreated(){
        hp.menuClients.click()
        Assert.assertEquals(hp.driver.findElements(By.className("faktura_id")).size,1)
    }




}