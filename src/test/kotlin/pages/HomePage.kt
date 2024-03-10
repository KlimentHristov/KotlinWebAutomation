package pages

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration
class HomePage(driver: WebDriver):BasePage(driver) {

    lateinit var cp: ClientPage
    init {
        cp = ClientPage(driver)
        PageFactory.initElements(driver, this)
    }
    var randomName = generateString(10)
    val savedRandomName = randomName

    // Titles
    @FindBy(xpath = "//*[@id=\"logo\"]/a/h1")  lateinit var logoTextForLoggedUser: WebElement

    // Pop up message
    @FindBy(xpath = "//*[@id=\"autocomplete_popup_nf\"]/div[4]") lateinit var popUpMessageAcceptBtn: WebElement
    @FindBy(className = "userpanel-header") lateinit var loggedEmail: WebElement


    // Home page --> menus
    @FindBy(xpath = "//*[@id=\"tabs_home\"]/a") private lateinit var menuHome: WebElement
    @FindBy(xpath = "//*[@id=\"tabs_invoices/new\"]/a") private lateinit var menuNewInvoice: WebElement
    @FindBy(xpath = "//*[@id=\"tabs_invoices\"]/a") private lateinit var menuInvoices: WebElement
    @FindBy(xpath = "//*[@id=\"tabs_clients\"]/a") lateinit var menuClients: WebElement
    @FindBy(xpath = "//*[@id=\"tabs_objects\"]/a") private lateinit var menuItems: WebElement
    @FindBy(xpath = "//*[@id=\"tabs_documents\"]/a") private lateinit var menuDocuments: WebElement


    // Home page --> Client section
    @FindBy(xpath = "//*[@id=\"headline2\"]/a") private lateinit var newClientBtn: WebElement
    @FindBy(xpath = "//*[@id=\"searchbtn\"]") private lateinit var searchBtn: WebElement
    @FindBy(id = "cl_sendbtn") lateinit var sendEmailBtn: WebElement
    @FindBy(id = "cl_delbtn") lateinit var deleteBtn: WebElement
    @FindBy(id = "cl_invbtn") lateinit var generateInvoice: WebElement
    @FindBy(id = "cl_expbtn") lateinit var exportBtn: WebElement
//    val subClientMenu = driver.findElements(By.id("sublinks2"))
//    val tableClientMenu = driver.findElements(By.id("tablecontrolz"))

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
    fun checkboxClientByText(textToSearch: String) {
        val clientNames: MutableList<WebElement>? = driver.findElements(By.className("faktura_id"))
        val checkboxClients: List<WebElement> = driver.findElements(By.name("client[]"))
        val clientMap = mutableMapOf<String, WebElement>()

        // Мапирай имената на клиентите към съответните чекбоксове
        if (clientNames != null) {
            for (i in clientNames.indices) {
                val name = clientNames[i].text.trim() // Извлечи текста на клиента и премахни излишните интервали
                clientMap[name] = checkboxClients[i]
            }
            // Провери за съответствие и кликни на чекбокса, ако е намерен
            if (clientMap.containsKey(textToSearch)) {
                val checkbox = clientMap[textToSearch]
                checkbox?.click()
            } else {
                println("Грешка: Не е намерен клиент с име $textToSearch.")
            }
        }
    }
    fun generateString(length: Int): String {
        val allowedChars = ('A'..'Z') + ('a'..'z')  // Allowed characters for the random string
        return (1..length)
            .map { allowedChars.random() }  // Randomly select characters from allowedChars
            .joinToString("")  // Convert the list of characters to a string
    }
    fun createNewClient(){
        val wait = WebDriverWait(driver, Duration.ofSeconds(10))

        val newClientName = savedRandomName
        val newClientAddress = "zhk. Lozenets"
        val newClientCity = "Sofia city"
        newClientBtn.click()
        wait.until(ExpectedConditions.visibilityOf(cp.radioBtnPerson))
        cp.radioBtnPerson.click()
        wait.until(ExpectedConditions.visibilityOf(cp.nameField))
        cp.nameField.sendKeys(newClientName)
        cp.addressField.sendKeys(newClientAddress)
        cp.cityField.sendKeys(newClientCity)

        val actions = Actions(driver)
        actions.moveToElement(cp.addClientBtn).build().perform()

        cp.addClientBtn.click()

    }






}