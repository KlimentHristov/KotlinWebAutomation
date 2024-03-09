//import tests.BaseTest
//import dev.selenium.support.SelectListTest
//import org.junit.jupiter.api.Assertions
//import org.junit.jupiter.api.BeforeAll
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import org.openqa.selenium.By
//import org.openqa.selenium.support.ui.ExpectedConditions
//import org.openqa.selenium.support.ui.WebDriverWait
//import java.time.Duration
//import kotlin.test.Asserter
//
//class LoginTest: BaseTest() {
//
//    @Test
//    fun navigate() {
//        driver.get("https://www.selenium.dev/selenium/web/formPage.html")
//        val emailField = driver.findElement(By.id("email"))
//        val ageField = driver.findElement(By.id("age"))
//        val submitButton = driver.findElement(By.id("submitButton"))
//
//
//        emailField.sendKeys("Kiiiiiv@bolshikkkkk.com")
//        ageField.sendKeys("23")
//        submitButton.isDisplayed
//        submitButton.click()
//
//
//
//
//        val resultPageTitle = driver.findElement(By.id("greeting"))
//        Assertions.assertTrue(resultPageTitle.isDisplayed)
//        Assertions.assertTrue(resultPageTitle.text.toString() == "Success!")
//
//
//
//    }
//}