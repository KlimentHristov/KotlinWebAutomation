package pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import updater.FakerInfo
import java.time.Duration

class ItemPage(driver: WebDriver):BasePage(driver) {

        init {
            PageFactory.initElements(driver,this)
        }

    @FindBy(xpath = "") lateinit var k: WebDriver

        @FindBy(xpath = "//*[@id=\"headline2\"]/h2") lateinit var titleForNewItem: WebElement

        @FindBy(name = "name") lateinit var nameOfItem: WebElement

        @FindBy(name = "price") lateinit var priceOfItem: WebElement
        @FindBy(name = "price_currency") lateinit var dropDownPriceCurrency: WebElement // option value

        @FindBy(name = "price_quantity") lateinit var quantityOfItem: WebElement
        @FindBy(name = "price_quantity_unit") lateinit var dropDownQuantityOfUnit: WebElement // option value

        @FindBy(name = "vat_percent") lateinit var dropDownVatRateTax: WebElement // option value

        @FindBy(name = "account") lateinit var accountOfItem: WebElement
        @FindBy(name = "accounting_batch") lateinit var batchOfItem: WebElement

        @FindBy(xpath = "//input[@value='Добави артикулa' and @type='submit']") lateinit var addItemBtn: WebElement



}