package tests

import constants.Constants
import org.openqa.selenium.By
import org.testng.Assert
import org.testng.ITestListener
import org.testng.ITestResult
import org.testng.annotations.AfterClass
import org.testng.annotations.Test
import pages.*
import updater.Updater


class ItemTests: BaseTest(), ITestListener {

    lateinit var lp: LoginPage
    lateinit var hp: HomePage
    lateinit var rp: ResultPage
    lateinit var it: ItemPage

    init {
        lp = LoginPage(driver)
        hp = HomePage(driver)
        rp = ResultPage(driver)
        it = ItemPage(driver)
    }
    private fun login(){
        driver.get(Constants.MAIN_URL)
        lp.loginWithValidCredentials()
        hp.popUpMessageAcceptBtn.click()
        Assert.assertTrue(driver.currentUrl.contains(Constants.HOME_PAGE_URL))
        Assert.assertTrue(driver.title.contains(Constants.HOME_PAGE_TITLE))
    }
        override fun onTestStart(result: ITestResult?) {
            println(message = "Test ${result?.name} started...")
        }
        override fun onTestFailure(result: ITestResult?) {
            println(message = "Test ${result?.name} failed: ${result?.throwable?.message}")
        }
    @AfterClass
    fun quitDriver(){
        tearDown()
    }

    @Test(priority = 1)
    fun createItemFromMenuItems(){
        login()
        hp.menuItems.click()
        hp.newItemBtn.click()
        hp.createItem()
        rp.waitToBeVisible(rp.messageSuccessOperation)
        Assert.assertTrue(rp.messageSuccessOperation.text.contains("Артикулът е добавен успешно."))
    }
    @Test(priority = 2)
    fun createItemFromFastBtnOnHomePage(){
        hp.menuHome.click()
        val kiko = Updater.getFasterStartMenu(driver)
        for (element in kiko!!){
            if (element.text.contains("Добавяне на нов Артикул")) {
                element.click()
                break  // Exit loop once the element is clicked
            }
        }
        hp.createItem()
        Assert.assertTrue(rp.messageSuccessOperation.text.contains("Артикулът е добавен успешно."))
    }
    @Test(priority = 3)
    fun validatesCreatedItems(){
        hp.menuItems.click()
        val actualCreatedItems = Updater.getUpdatedCreatedList(driver)
        Assert.assertEquals(actualCreatedItems?.size,2)
    }
    @Test(priority = 4)
    fun deleteAllItems(){
        hp.handleAllBtn.isDisplayed.and(hp.handleAllBtn.isEnabled)
        hp.handleAllBtn.click()
        hp.delItemBtn.click()

        hp.handlePopupDeleteItems("Да")
        hp.waitToBeVisible(rp.messageSuccessOperation,10)
        Assert.assertTrue(rp.messageSuccessOperation.text.
        contains("Артикулите бяха изтрити успешно."))
        hp.menuItems.click()
        Assert.assertTrue(driver.findElement(By.id("emptylist")).text
            .contains("Не са намерени артикули, отговарящи на зададените критерии."))
    }


}