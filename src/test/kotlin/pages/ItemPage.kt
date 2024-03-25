package pages

import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.FindBy

class ItemPage(driver: WebDriver):BasePage(driver) {


    @FindBy(xpath = "") lateinit var k: WebDriver


}