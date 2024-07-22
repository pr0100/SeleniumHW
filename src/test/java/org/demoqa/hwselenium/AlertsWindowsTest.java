package org.demoqa.hwselenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertsWindows {
  private WebDriver driver = new ChromeDriver();
  private WebDriverWait wait;
  private WebElement exampleElement;
  protected static final Logger LOGGER = LogManager.getLogger();

  @BeforeEach
  void setUp() {
    driver.get("https://demoqa.com/alertsWindows");
  }

  @Test
  @DisplayName("")
  void successfulOpenNewTab() {
    driver.findElement(By.id("item-")).click();
    driver.findElement(By.id("tabButton")).click();
    assertEquals("https://demoqa.com/sample", driver.getCurrentUrl());
  }

  @AfterEach
  void finish() {
    driver.quit();
  }
}

}
