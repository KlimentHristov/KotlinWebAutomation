package tests

import com.aventstack.extentreports.ExtentReports
import com.aventstack.extentreports.ExtentTest
import com.aventstack.extentreports.reporter.ExtentSparkReporter
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.testng.ITestListener
import org.testng.ITestResult
import org.testng.annotations.AfterClass
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeClass
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Listeners
import java.time.Duration



open class BaseTest{
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
