package updater

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

object Updater {

    // Client page
    fun getUpdatedClientList(driver: WebDriver): MutableList<WebElement>? {
        return driver.findElements(By.className("faktura_id"))
    }
    fun getUpdatedCheckboxList(driver: WebDriver): MutableList<WebElement>? {
        return driver.findElements(By.name("client[]"))
    }
    fun getInvoiceTableList(driver: WebDriver): MutableList<WebElement>? {
        return driver.findElements(By.id("fakturi_table"))
    }
    fun checkboxClientByText(textToSearch: String, driver: WebDriver) {
        val clientNames: List<WebElement>? = getUpdatedClientList(driver)
        val checkboxClients: List<WebElement>? = getUpdatedCheckboxList(driver)
        if (clientNames != null && checkboxClients != null) {
            // Find the checkbox associated with the specified client name and clicks on it if found.
            val clientMap = clientNames.mapIndexed {
                   index, element -> element.text.trim() to checkboxClients[index] }.toMap()
            if (clientMap.containsKey(textToSearch)) {
                val checkbox = clientMap[textToSearch]
                checkbox?.click()
            } else {
                println("Грешка: Не е намерен клиент с име $textToSearch.")
            }
        }
    }
    fun checkboxTableClientByText(textToSearch: String, driver: WebDriver) {
        val clientNames: List<WebElement>? = getInvoiceTableList(driver)
        val checkboxClients: List<WebElement>? = getUpdatedCheckboxList(driver)
        if (clientNames != null && checkboxClients != null) {
            // Find the checkbox associated with the specified client name and clicks on it if found.
            val clientMap = clientNames.mapIndexed {
                    index, element -> element.text.trim() to checkboxClients[index] }.toMap()
            if (clientMap.containsKey(textToSearch)) {
                val checkbox = clientMap[textToSearch]
                checkbox?.click()
            } else {
                println("Грешка: Не е намерен клиент с име $textToSearch.")
            }
        }
    }

    // Home page
    fun getFasterStartMenu(driver: WebDriver): List<WebElement>? {
        return driver.findElements(By.xpath("/html/body/div[3]/div/div[2]/div[1]/a"))
    }



}