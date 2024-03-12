package pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.testng.annotations.BeforeClass
import org.testng.annotations.BeforeMethod
import java.time.Duration
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt
import kotlin.random.nextInt

class HomePage(driver: WebDriver):BasePage(driver) {

    lateinit var cp: ClientPage
    init {
        cp = ClientPage(driver)
        PageFactory.initElements(driver, this)
    }


    var randomName = generateString(10)
    var randomEgn = generateRandomNumberInRange(1,10,10)
    var randomPostCode = generateRandomNumberInRange(1,4,10)
    var randomPhoneNumber = generateRandomNumberInRange(0,10,10)

    // Titles
    @FindBy(xpath = "//*[@id=\"logo\"]/a/h1")  lateinit var logoTextForLoggedUser: WebElement

    // Pop up message
    @FindBy(xpath = "//*[@id=\"autocomplete_popup_nf\"]/div[4]") lateinit var popUpMessageAcceptBtn: WebElement
    @FindBy(className = "userpanel-header") lateinit var loggedEmail: WebElement


    // Home page --> menus
    @FindBy(xpath = "//*[@id=\"tabs_home\"]/a") lateinit var menuHome: WebElement
    @FindBy(xpath = "//*[@id=\"tabs_invoices/new\"]/a") lateinit var menuNewInvoice: WebElement
    @FindBy(xpath = "//*[@id=\"tabs_invoices\"]/a") lateinit var menuInvoices: WebElement
    @FindBy(xpath = "//*[@id=\"tabs_clients\"]/a") lateinit var menuClients: WebElement
    @FindBy(xpath = "//*[@id=\"tabs_objects\"]/a") lateinit var menuItems: WebElement
    @FindBy(xpath = "//*[@id=\"tabs_documents\"]/a")lateinit var menuDocuments: WebElement


    // Home page --> Client section
    @FindBy(xpath = "//*[@id=\"headline2\"]/a") lateinit var newClientBtn: WebElement

    @FindBy(id = "cl_invbtn") lateinit var generateInvoice: WebElement
    @FindBy(id = "cl_sendbtn") lateinit var sendEmailBtn: WebElement
    @FindBy(id = "cl_delbtn") lateinit var deleteBtn: WebElement
    @FindBy(id = "cl_expbtn") lateinit var exportBtn: WebElement
    @FindBy(xpath = "//*[@id=\"searchbtn\"]") lateinit var searchBtn: WebElement

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
    fun generateString(length: Int): String {
        val allowedChars = ('A'..'Z') + ('a'..'z')  // Allowed characters for the random string
        return (1..length)
            .map { allowedChars.random() }  // Randomly select characters from allowedChars
            .joinToString("")  // Convert the list of characters to a string
    }
    fun generateRandomNumberInRange(min: Int, max: Int, count:Int): String {
        require(min < max) { "min must be less than max" }
        require(count >= 0) { "count must be non-negative" }

        val randomNumbers = List(count) { Random.nextInt(min..max) }
        return randomNumbers.joinToString("")
    }

    fun createDefaultClient(){
        val wait = WebDriverWait(driver, Duration.ofSeconds(10))
        val newClientAddress = "zhk. Lozenets"
        val newClientCity = "Sofia city"
        val savedRandomName = randomName
        newClientBtn.click()
        wait.until(ExpectedConditions.visibilityOf(cp.radioBtnPerson))
        cp.radioBtnPerson.click()
        wait.until(ExpectedConditions.visibilityOf(cp.nameField))
        cp.nameField.sendKeys(savedRandomName)
        cp.addressField.sendKeys(newClientAddress)
        cp.cityField.sendKeys(newClientCity)

        val actions = Actions(driver)
        actions.moveToElement(cp.addClientBtn).build().perform()

        cp.addClientBtn.click()
    }
    fun createExtendableClient(){
        val wait = WebDriverWait(driver, Duration.ofSeconds(10))
        val clientAddressExtend = "zhk. Extends 100"
        val clientCityExtend = "Extend" + generateString(5)
        val savedRandomName = randomName
        val savedRandomEgn = randomEgn
        val savednRandomPostcode = randomPostCode
        val savedPhoneNumber = randomPhoneNumber

        newClientBtn.click()
        wait.until(ExpectedConditions.visibilityOf(cp.radioBtnPerson))
        cp.radioBtnPerson.click()
        wait.until(ExpectedConditions.visibilityOf(cp.nameField))

        cp.nameField.sendKeys(savedRandomName)
        cp.egnField.sendKeys(savedRandomEgn)
        cp.addressField.sendKeys(clientAddressExtend)
        cp.postcodeField.sendKeys(savednRandomPostcode)
        cp.cityField.sendKeys(clientCityExtend)
        cp.phoneNumberField.sendKeys(savedPhoneNumber)

        cp.selectElementByValue(cp.phoneSelector,"mobile")

        cp.addClientBtn.click()

    }






}