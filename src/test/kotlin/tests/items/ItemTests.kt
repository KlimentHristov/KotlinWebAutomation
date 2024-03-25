package tests.items

import constants.Constants
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.Assert
import org.testng.annotations.Test
import pages.*
import tests.BaseTest
import java.time.Duration

class ItemTests: BaseTest() {

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

    @Test
    fun createItemFromMenuItems(){
        login()
        hp.menuItems.click()
        hp.newItemBtn.click()
        hp.createItem()
        rp.waitToBeVisible(rp.messageSuccessOperation)
        Assert.assertTrue(rp.messageSuccessOperation.text.contains("Артикулът е добавен успешно."))
    }


}