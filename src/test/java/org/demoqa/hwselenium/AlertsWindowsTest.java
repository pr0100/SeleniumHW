package org.demoqa.hwselenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertsWindowsTest {
  private WebDriver driver = new ChromeDriver();
  private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));;
  private WebElement exampleElement;
  protected static final Logger LOGGER = LogManager.getLogger();

  @BeforeEach
  void setUp() {
    driver.get("https://demoqa.com/alertsWindows");
  }

  @Test
  @DisplayName("Открытие и переход на новую вкладку")
  void successfulOpenNewTab() {;
    driver.findElement(By.xpath("//span/text()[. =\"Browser Windows\"]/ancestor::li")).click();
    driver.findElement(By.id("tabButton")).click();
    Object[] windowHandles=driver.getWindowHandles().toArray();
    driver.switchTo().window((String) windowHandles[1]);
    assertEquals("https://demoqa.com/sample", driver.getCurrentUrl());
    LOGGER.info("Switch current tab to new");
  }

  @Test
  @DisplayName("Взаимодействие с алертом")
  void successfulAlertDisplay() {
    driver.findElement(By.xpath("//span/text()[. =\"Alerts\"]/ancestor::li")).click();
    driver.findElement(By.id("timerAlertButton")).click();
    wait.until(ExpectedConditions.alertIsPresent());
    Alert alert = driver.switchTo().alert();
    String alertText = alert.getText();
    alert.accept();
    assertEquals("This alert appeared after 5 seconds", alertText);
    LOGGER.info("Alert displayed");
  }

  @AfterEach
  void finish() {
    driver.quit();
  }
}

