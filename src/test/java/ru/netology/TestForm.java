package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestForm {

        private WebDriver driver;

        @BeforeAll
        public static void setUpAll() {
            WebDriverManager.chromedriver().setup();
        }

        @BeforeEach
        public void setUp() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--no-sandbox");
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        }

        @AfterEach
        public void tearDown() {
            driver.quit();
            driver = null;
        }

    @Test
            public void shouldSendForm()  {
            driver.get("http://localhost:9999");
            driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Алексей М");
            driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79211234567");
            driver.findElement(By.cssSelector(".checkbox__box")).click();
            driver.findElement(By.cssSelector("button")).click();
            String actualText = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText().trim();
            String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
            assertEquals (expected,actualText);

        }

        @Test
    public void shouldNotSendNoName() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79211234567");
        driver.findElement(By.cssSelector(".checkbox__box")).click();
        driver.findElement(By.cssSelector("button")).click();
        String actualText = driver.findElement(By.cssSelector("[class=\"input__sub\"]")).getText().trim();
        String expected = "Поле обязательно для заполнения";
        assertEquals (expected,actualText);

    }
}
