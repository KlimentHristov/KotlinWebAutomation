package tests.client

import org.testng.Assert
import org.testng.annotations.Test
import constants.Constants.HOME_PAGE_TITLE
import constants.Constants.HOME_PAGE_URL
import constants.Constants.MAIN_URL
import org.openqa.selenium.By
import org.testng.annotations.AfterClass
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
    lateinit var cp: ClientPage
    init {
        lp = LoginPage(driver)
        hp = HomePage(driver)
        rp = ResultPage(driver)
        cp = ClientPage(driver)

    }
    private fun login(){
        driver.get(MAIN_URL)
        lp.loginWithValidCredentials()
        hp.popUpMessageAcceptBtn.click()
        Assert.assertTrue(driver.currentUrl.contains(HOME_PAGE_URL))
        Assert.assertTrue(driver.title.contains(HOME_PAGE_TITLE))
    }
    @AfterClass
    fun quitDriver(){
        tearDown()
    }

    @Test(priority = 1)
    fun createClientFromMenuClients(){
        login()
        hp.menuClients.click()
        hp.newClientBtn.click()
        hp.createDefaultClient()
        Assert.assertEquals("Клиентът е добавен успешно.\n" +
                " ",rp.messageSuccessOperation.text)
    }
    @Test(priority = 2)
    fun createClientFromFastBtnOnHomePage(){
        hp.menuHome.click()
        val kiko = Updater.getFasterStartMenu(driver)
        for (element in kiko!!){
            if (element.text.contains("Добавяне на нов клиент")) {
                element.click()
                break  // Exit loop once the element is clicked
            }
        }
        hp.createDefaultClient()
        Assert.assertEquals("Клиентът е добавен успешно.\n" +
                " ",rp.messageSuccessOperation.text)
    }
    @Test(priority = 3)
    fun deleteOneClient(){
        hp.menuClients.click()
        Updater.getUpdatedCheckboxList(driver)?.get(0)?.click()
        hp.deleteBtn.click()

        hp.handleDeletePopup("Accept")
        Assert.assertTrue(rp.messageSuccessOperation.text.
            contains("Избраните клиенти бяха изтрити успешно."))
    }
    @Test(priority = 4)
    fun checkClientsListAfterDeleteOne(){
        Assert.assertEquals(Updater.getUpdatedCheckboxList(driver)?.size,1)
    }
    @Test(priority = 5)
    fun createClientExtendable(){
        hp.newClientBtn.click()
        hp.createExtendableClient()
        Assert.assertEquals("Клиентът е добавен успешно.\n" +
                " ",rp.messageSuccessOperation.text)
    }
    @Test(priority = 6)
    fun verifyClientListIncreased(){
        hp.menuClients.click()
        Assert.assertEquals(hp.driver.findElements(By.className("faktura_id")).size,2)
    }
    @Test(priority = 7)
    fun deleteAllClients(){
        hp.handleAllBtn.isDisplayed.and(hp.handleAllBtn.isEnabled)
        hp.handleAllBtn.click()
        hp.deleteBtn.click()

        hp.handleDeletePopup("Accept")
        Assert.assertTrue(rp.messageSuccessOperation.text.
        contains("Избраните клиенти бяха изтрити успешно."))
        hp.menuClients.click()
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"emptylist\"]")).text.contains("Все още нямате добавени клиенти."))
    }




}