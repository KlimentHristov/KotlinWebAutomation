package pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory

class ClientPage(driver: WebDriver):BasePage(driver) {

    init {
        PageFactory.initElements(driver, this)
    }

    //Radio Buttons
    @FindBy(id = "type_person") lateinit var radioBtnPerson: WebElement
    @FindBy(id = "type_firm") lateinit var radioBtnFirm: WebElement

    // Fields
    @FindBy(name = "person_name") lateinit var nameField: WebElement
    @FindBy(id = "person-egn") lateinit var egnField: WebElement
    @FindBy(name ="person_address") lateinit var addressField: WebElement
    @FindBy(id ="client-postcode") lateinit var postcodeField: WebElement
    @FindBy(name = "person_city") lateinit var cityField: WebElement
    @FindBy(name = "phone_numbers[0][number]") lateinit var phoneNumberField: WebElement
    @FindBy(xpath = "//*[@id=\"client-add-form\"]/table/tbody[4]/tr[2]/td[2]/a") lateinit var removePhoneBtn: WebElement
    // Selector
    @FindBy(xpath = "//*[@id=\"client-add-form\"]/table/tbody[4]/tr[2]/td[2]/select") lateinit var selectCollectionMobileType: WebElement

    // Buttons
    @FindBy(name = "do_submit") lateinit var addClientBtn: WebElement
    @FindBy(name = "do_submit_and_warr_create") lateinit var addClientAndWarrantyBtn: WebElement
    @FindBy(name = "do_submit_and_inv_create") lateinit var addClientAndGenerateInvoiceBtn: WebElement




}