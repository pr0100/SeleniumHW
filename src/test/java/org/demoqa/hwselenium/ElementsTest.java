package org.demoqa.hwselenium;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElementsTest {

  private WebDriver driver = new ChromeDriver();
  private WebDriverWait wait;
  private WebElement exampleElement;
  protected static final Logger LOGGER = LogManager.getLogger();


  @BeforeEach
  void setUp() {
    driver.get("https://demoqa.com/elements");
  }

  @Test
  @DisplayName("Успешное нажатие элемента radio button")
  void successfulRadioBtnClick() {
    driver.findElement(By.id("item-2")).click();
    WebElement radBtn = driver.findElement(By.cssSelector("#yesRadio"));
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", radBtn);
    assertEquals("Yes", driver.findElement(By.xpath("//*[@class=\"text-success\"]")).getText());
    LOGGER.info("Radio button successfully verified");
  }

  static Stream<String[]> provideData() {
    return Stream.of(
        new String[]{"Strelkov Stas", "test@mail.ru", "new street, 111, 123"},
        new String[]{"Nikita", "test2@m.com", ""}
    );
  }

  @ParameterizedTest
  @MethodSource("provideData")
  @DisplayName("Заполнение текстовых полей")
  void fillingTextBox(String inputName, String inputMail, String inputAddress) {
    driver.findElement(By.id("item-0")).click();

    WebElement name = driver.findElement(By.id("userName"));
    WebElement email = driver.findElement(By.id("userEmail"));
    WebElement address = driver.findElement(By.id("currentAddress"));

    name.sendKeys(inputName);
    email.sendKeys(inputMail);
    address.sendKeys(inputAddress);

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);

    assertEquals("Name:" + inputName, driver.findElement(By.id("name")).getText());
    assertEquals("Email:" + inputMail, driver.findElement(By.id("email")).getText());

    List<WebElement> currentAddressElements = driver.findElements(
        By.cssSelector("p#currentAddress"));
    if (!currentAddressElements.isEmpty()) {
      assertEquals("Current Address :" + inputAddress,
          driver.findElement(By.cssSelector("p#currentAddress")).getText());
    }

    LOGGER.info("Text boxes successfully verified");
  }


  @AfterEach
  void finish() {
    driver.quit();
  }

}
