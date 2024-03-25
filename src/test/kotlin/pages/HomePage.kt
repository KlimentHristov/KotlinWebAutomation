package pages
import com.github.javafaker.Faker
import updater.FakerInfo
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration
import kotlin.random.Random

class HomePage(driver: WebDriver):BasePage(driver) {

    lateinit var cp: ClientPage
    lateinit var it: ItemPage
    lateinit var rp: ResultPage
    init {
        cp = ClientPage(driver)
        it = ItemPage(driver)
        rp = ResultPage(driver)
        PageFactory.initElements(driver, this)
    }

    // Titles
    @FindBy(xpath = "//*[@id=\"logo\"]/a/h1")  lateinit var logoTextForLoggedUser: WebElement

    // Pop up message
    @FindBy(xpath = "//*[@id=\"autocomplete_popup_nf\"]/div[4]") lateinit var popUpMessageAcceptBtn: WebElement
    @FindBy(className = "userpanel-header") lateinit var loggedEmail: WebElement
    @FindBy(id = "userpanel") lateinit var panelMenu: WebElement


    // Home page --> menus
    @FindBy(id = "tabs_home") lateinit var menuHome: WebElement
    @FindBy(id = "tabs_invoices/new") lateinit var menuNewInvoice: WebElement
    @FindBy(id = "tabs_invoices") lateinit var menuInvoices: WebElement
    @FindBy(id = "tabs_clients") lateinit var menuClients: WebElement
    @FindBy(id = "tabs_objects") lateinit var menuItems: WebElement
    @FindBy(id = "tabs_documents")lateinit var menuDocuments: WebElement
    @FindBy(id = "tabs_cashbox")lateinit var menuCashbox: WebElement
    @FindBy(id = "tabs_reports")lateinit var menuReports: WebElement
    @FindBy(id = "tabs_exports")lateinit var menuExports: WebElement


    // Home page --> Client section
    @FindBy(xpath = "//*[@id=\"headline2\"]/a") lateinit var newClientBtn: WebElement
    @FindBy(id = "handle_all") lateinit var handleAllBtn: WebElement

    @FindBy(id = "cl_invbtn") lateinit var generateInvoice: WebElement
    @FindBy(id = "cl_sendbtn") lateinit var sendEmailBtn: WebElement
    @FindBy(id = "cl_delbtn") lateinit var deleteBtn: WebElement
    @FindBy(id = "cl_expbtn") lateinit var exportBtn: WebElement
    @FindBy(xpath = "//*[@id=\"searchbtn\"]") lateinit var searchBtn: WebElement


    // Item section
    @FindBy(css = "[class=\"newbtn selenium-add-item\"]") lateinit var newItemBtn: WebElement


    fun handleDeletePopup(action: String){
        val mainWindowHandle = driver.windowHandle
        for (windowHandle in driver.windowHandles) {
            if (windowHandle != mainWindowHandle) {
                driver.switchTo().window(windowHandle)
                break
            }
        }
        // Perform actions in the popup window
        if (action == "Accept"){
            driver.findElement(By.id("submit-clients-delete")).click()
        }else{
            driver.findElement(By.id("cancel-clients-delete")).click()
        }
    }
    fun createClientCustomName(name: String){
        val wait = WebDriverWait(driver, Duration.ofSeconds(10))
        val newClientAddress = "zhk. Lozenets"
        val newClientCity = "Sofia city"
        wait.until(ExpectedConditions.visibilityOf(cp.radioBtnPerson))
        cp.radioBtnPerson.click()
        wait.until(ExpectedConditions.visibilityOf(cp.nameField))
        cp.nameField.sendKeys(name)
        cp.addressField.sendKeys(newClientAddress)
        cp.cityField.sendKeys(newClientCity)

        val actions = Actions(driver)
        actions.moveToElement(cp.addClientBtn).build().perform()

        cp.addClientBtn.click()
    }
    fun createDefaultClient(){
        val wait = WebDriverWait(driver, Duration.ofSeconds(10))
        val newClientAddress = "zhk. Lozenets"
        val newClientCity = "Sofia city"
        wait.until(ExpectedConditions.visibilityOf(cp.radioBtnPerson))
        cp.radioBtnPerson.click()
        wait.until(ExpectedConditions.visibilityOf(cp.nameField))
        cp.nameField.sendKeys(FakerInfo.randomFullName())
        cp.addressField.sendKeys(newClientAddress)
        cp.cityField.sendKeys(newClientCity)

        val actions = Actions(driver)
        actions.moveToElement(cp.addClientBtn).build().perform()

        cp.addClientBtn.click()
    }
    fun createExtendableClient(){
        val wait = WebDriverWait(driver, Duration.ofSeconds(10))
        val clientAddressExtend = "Extend-" + FakerInfo.randomADDRESS()
        val clientCityExtend = "Extend-" + FakerInfo.randomCITY()

        wait.until(ExpectedConditions.visibilityOf(cp.radioBtnPerson))
        cp.radioBtnPerson.click()
        wait.until(ExpectedConditions.visibilityOf(cp.nameField))

        cp.nameField.sendKeys(FakerInfo.randomFullName())
        cp.egnField.sendKeys(FakerInfo.randomEGN())
        cp.addressField.sendKeys(clientAddressExtend)
        cp.postcodeField.sendKeys(FakerInfo.randomPostCode())
        cp.cityField.sendKeys(clientCityExtend)
        cp.phoneNumberField.sendKeys(FakerInfo.randomPhoneNumber())

        cp.selectElementByValue(cp.phoneSelector,"mobile")

        cp.addClientBtn.click()

    }
    fun createItem(){
        val wait = WebDriverWait(driver, Duration.ofSeconds(10))
        wait.until(ExpectedConditions.visibilityOf(it.titleForNewItem))

        it.nameOfItem.sendKeys(FakerInfo.randomName())
        it.priceOfItem.sendKeys(FakerInfo.randomPrice())
        selectElementByValue(it.dropDownPriceCurrency,"EUR")
        it.quantityOfItem.click()
        it.quantityOfItem.clear()
        it.quantityOfItem.sendKeys("1")
        selectElementByValue(it.dropDownQuantityOfUnit,"6")
        selectElementByValue(it.dropDownVatRateTax,"0")
        it.accountOfItem.sendKeys(FakerInfo.randomIBAN())
        it.batchOfItem.sendKeys("1")
        it.addItemBtn.click()

    }








}