package updater

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy

object Updater {

    // Client page
    fun getUpdatedClientList(driver: WebDriver): MutableList<WebElement>? {
        val clientNamesList = driver.findElements(By.className("faktura_id"))
        return clientNamesList
    }
    fun getUpdatedCheckboxList(driver: WebDriver): MutableList<WebElement>? {
        val checkboxLists = driver.findElements(By.name("client[]"))
        return checkboxLists
    }
    // Home page
    fun getFasterStartMenu(driver: WebDriver): List<WebElement>? {
        val fastStartMenu = driver.findElements(By.xpath("/html/body/div[3]/div/div[2]/div[1]/a"))
        return fastStartMenu
    }
    fun checkboxClientByText(textToSearch: String, driver: WebDriver) {
        val clientNames: List<WebElement>? = driver.findElements(By.className("faktura_id"))
        val checkboxClients: List<WebElement> = driver.findElements(By.name("client[]"))
        val clientMap = mutableMapOf<String, WebElement>()

        // Maps the names toward checkboxes
        if (clientNames != null) {
            for (i in clientNames.indices) {
                val name = clientNames[i].text.trim()
                clientMap[name] = checkboxClients[i]
            }
            // Check for coincidence and click if the checkbox is founded
            if (clientMap.containsKey(textToSearch)) {
                val checkbox = clientMap[textToSearch]
                checkbox?.click()
            } else {
                println("Грешка: Не е намерен клиент с име $textToSearch.")
            }
        }
    }


}