package pages

import org.junit.jupiter.api.Assertions
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

class HomePage(private val driver: WebDriver) {


    init {
        PageFactory.initElements(driver, this)
    }
    // Home page
    @FindBy(xpath = "//*[@id=\"tabs_home\"]/a") private lateinit var menuHome: WebElement
    @FindBy(xpath = "//*[@id=\"tabs_invoices/new\"]/a") private lateinit var menuNewInvoice: WebElement
    @FindBy(xpath = "//*[@id=\"tabs_invoices\"]/a") private lateinit var menuInvoices: WebElement
    @FindBy(xpath = "//*[@id=\"tabs_clients\"]/a") lateinit var menuClients: WebElement
    @FindBy(xpath = "//*[@id=\"tabs_objects\"]/a") private lateinit var menuItems: WebElement
    @FindBy(xpath = "//*[@id=\"tabs_documents\"]/a") private lateinit var menuDocuments: WebElement

    // Client page
    @FindBy(xpath = "//*[@id=\"headline2\"]/a") private lateinit var newClientBtn: WebElement
    @FindBy(xpath = "//*[@id=\"type_person\"]") private lateinit var radioBtnPerson: WebElement
    // Create New Client page
    @FindBy(name = "person_name") private lateinit var nameField: WebElement
    @FindBy(name ="person_address") private lateinit var addressField: WebElement
    @FindBy(name = "person_city") private lateinit var cityField: WebElement
    @FindBy(name = "do_submit") private lateinit var addClientBtn: WebElement

    // Result page
    @FindBy(xpath = "//*[@id=\"okmsg\"]") lateinit var messageForSuccessCreatedClient: WebElement
    @FindBy(xpath = "//*[@id=\"error\"]") lateinit var messageForDublicatedClient: WebElement


    fun createNewClient(){
        val wait = WebDriverWait(driver, Duration.ofSeconds(10))
        val newClientName = "New Kiko"
        val newClientAddress = "zhk. Lozenets"
        val newClientCity = "Sofia city"
        newClientBtn.click()
        wait.until(ExpectedConditions.visibilityOf(radioBtnPerson))
        radioBtnPerson.click()
        wait.until(ExpectedConditions.visibilityOf(nameField))
        nameField.sendKeys(newClientName)
        addressField.sendKeys(newClientAddress)
        cityField.sendKeys(newClientCity)

        val actions = Actions(driver)
        actions.moveToElement(addClientBtn).build().perform()

        addClientBtn.click()

    }



}