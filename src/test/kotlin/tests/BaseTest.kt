package tests

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import java.time.Duration
import kotlin.io.path.createTempDirectory

open class BaseTest {
    lateinit var driver: WebDriver

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
        driver.quit()
    }
}
