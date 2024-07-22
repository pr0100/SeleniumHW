package org.demoqa.hwselenium;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BooksTest {

  private WebDriver driver = new ChromeDriver();
  private WebDriverWait wait;
  private WebElement exampleElement;
  protected static final Logger LOGGER = LogManager.getLogger();

  private int numberRows() {
    return driver.findElement(By.className("rt-tbody")).findElements(By.className("rt-tr-group")).size();
  }

  @BeforeEach
  void setUp() {
    driver.get("https://demoqa.com/books");
  }

  @Test
  @DisplayName("Отображение количества книг на странице")
  void checkNumberBooksPage() {
    assertEquals(10, numberRows());
    LOGGER.info("Number of books on page corresponds expected");
  }

  @Test
  @DisplayName("Изменения количества книг на странице")
  void changeNumberBooksPage() {
    Select pageSize = new Select(driver.findElement(By.cssSelector("div.-center span select")));
    pageSize.selectByValue("5");
    assertEquals(5, numberRows());
    LOGGER.info("Number of books on page changed");
  }

  @Test
  @DisplayName("Переход на 2 страницу")
  void switchCurrentPageToNext() {
    //WebElement btnNext = driver.findElement(By.xpath("//button/text()[. =\"Next\"]"));
    WebElement btnNext = driver.findElement(By.cssSelector(".-next button"));
    Select pageSize = new Select(driver.findElement(By.cssSelector("div.-center span select")));
    pageSize.selectByValue("5");
    btnNext.click();
    String currentPage = driver.findElement(By.cssSelector("input[type=number]")).getAttribute("value");
    assertEquals("2", currentPage);
    LOGGER.info("Switch current page");
  }

  @Test
  @DisplayName("Переход на 2 страницу и обратно на 1")
  void changePageToNextAndPrev() {
    Select pageSize = new Select(driver.findElement(By.cssSelector("div.-center span select")));
    pageSize.selectByValue("5");
    WebElement btnNext = driver.findElement(By.cssSelector(".-next button"));
    btnNext.click();
    WebElement btnPrev = driver.findElement(By.cssSelector(".-previous button"));
    btnPrev.click();
    String currentPage = driver.findElement(By.cssSelector("input[type=number]")).getAttribute("value");
    assertEquals("1", currentPage);
    LOGGER.info("Switch current page to next and back");
  }

  @AfterEach
  void finish() {
    driver.quit();
  }
}
