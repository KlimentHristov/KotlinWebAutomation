package tests

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.interactions.Actions
import java.time.Duration
import kotlin.io.path.createTempDirectory

open class BaseTest {
    lateinit var driver: WebDriver
    init {
        initializeDriver()
    }

    fun initializeDriver() {
        val chromeOptions = ChromeOptions()
        chromeOptions.addArguments("--disable-infobars")
        chromeOptions.addArguments("--disable-extensions")
        chromeOptions.addArguments("--disable-popup-blocking")
        chromeOptions.addArguments("--disable-dev-shm-usage")
        chromeOptions.addArguments("--disable-gpu")
        chromeOptions.addArguments("--disable-software-rasterizer")
        chromeOptions.addArguments("--no-sandbox")

        driver = ChromeDriver(chromeOptions)
        driver.manage().window().maximize()
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10))
    }

    fun tearDown() {
        driver.manage().deleteAllCookies()
        driver.quit()
    }
}
