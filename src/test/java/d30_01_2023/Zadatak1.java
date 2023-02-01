package d30_01_2023;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Zadatak1 {
    public static void main(String[] args) throws InterruptedException {
//        Zadatak
//        Napisati program koji testira infinity scroll.
//        Ucitati stranu https://web.dev/patterns/web-vitals-patterns/infinite-scroll/infinite-scroll/demo.html
//        Selektujte delay od 2000ms, koristeci Select klasu.
//        Skrol do Show more dugmeta koje se nalazi na dnu stranice
//        Sacekajte da dugme bude klikljivo
//        Klik na Show more dugme
//        Sacekjate da broj elemenata bude X (X je koliko se kod vas ucitava)
//        Sacekajte da dugme vise ne bude klikljivo

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();

        Thread.sleep(3000);

        driver.get("https://web.dev/patterns/web-vitals-patterns/infinite-scroll/infinite-scroll/demo.html");

        driver.findElement(By.xpath("//select[@id='delay-select']/option[@value='2000']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("infinite-scroll-button")));

        Thread.sleep(3000);

        WebElement scroll = driver.findElement(By.id("infinite-scroll-button"));
        scroll.click();

        new Actions(driver).scrollToElement(scroll).perform();

        wait.until(ExpectedConditions.
                numberOfElementsToBe(By.xpath("//div[@class='item']"), 5));
        wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(By.id("infinite-scroll-button"))));

        Thread.sleep(3000);
        driver.quit();

    }
}
