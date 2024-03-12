package pages

import kotlinx.coroutines.delay
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration


open class BasePage(val driver: WebDriver) {

    init {
        PageFactory.initElements(driver, this)
    }
    private val defaultWaitSeconds = 10L

    suspend fun pause(seconds: Long) {
        delay(seconds * 1000) // delay function expects milliseconds, so multiply seconds by 1000
    }

    //Wait elements
    fun waitToBeVisible(element: WebElement?, waitSeconds: Long = defaultWaitSeconds) {
        element ?: throw IllegalArgumentException("Element cannot be null")
        val wait = WebDriverWait(driver, Duration.ofSeconds(waitSeconds))
        wait.until(ExpectedConditions.visibilityOf(element))
    }
    fun waitToBeInvisible(element: WebElement?, waitSeconds: Long = defaultWaitSeconds) {
        element ?: throw IllegalArgumentException("Element cannot be null")
        val wait = WebDriverWait(driver, Duration.ofSeconds(waitSeconds))
        wait.until(ExpectedConditions.invisibilityOf(element))
    }
    fun waitToBeClickable(element: WebElement?, waitSeconds: Long = defaultWaitSeconds) {
        element ?: throw IllegalArgumentException("Element cannot be null")
        val wait = WebDriverWait(driver, Duration.ofSeconds(waitSeconds))
        wait.until(ExpectedConditions.elementToBeClickable(element))
    }
    fun waitForVisibilityOfText(textToBeDisplayed: String, timeOutInSecond: Long) {
        val wait = WebDriverWait(driver, Duration.ofSeconds(timeOutInSecond))
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated
            (By.xpath("//*[@text='$textToBeDisplayed'])"))))
    }
    // Click
    fun clickElementContainsText(textToClick: String, timeOutInSecond: Long) {
        val wait = WebDriverWait(driver, Duration.ofSeconds(timeOutInSecond))
        wait.until(ExpectedConditions.refreshed(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(), '$textToClick')]"))))
        driver.findElement(By.xpath("//*[contains(text(), '$textToClick')]")).click()
    }

    // Select element by
    fun selectElementByValue(element: WebElement?, option: String?){
        val select = Select(element)
        return select.selectByValue(option)
    }
    fun selectElementByIndex(element: WebElement, index: Int){
        val select = Select(element)
        return select.selectByIndex(index)
    }
    fun selectElementByText(element: WebElement, text: String){
        val select = Select(element)
        return select.selectByVisibleText(text)
    }

    // Scroll to element
    fun scrollToElement(element: WebElement?) {
        val action = Actions(driver)
        return action.scrollToElement(element).build().perform()
    }






}