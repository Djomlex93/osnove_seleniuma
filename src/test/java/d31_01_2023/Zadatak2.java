package d31_01_2023;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
public class Zadatak2 {
    public static void main(String[] args) throws InterruptedException {
//        2. Zadatak
//        Napisati program koji:
//        Ucitava stranu https://itbootcamp.rs/
//        Misem prelazi preko Vesti iz navigacionog menija
//        Ceka da se prikaze padajuci meni za Vesti
//        Misem prelazi preko Kursevi iz navigacionog menija
//        Ceka da se prikaze padajuci meni za Kursevi
//        Misem prelazi preko Prijava i pravilnik iz navigacionog menija
//        Ceka da se prikaze padajuci meni za Prijava i pravilnik
//        Koristan link. Mouse over preko seleniuma https://www.selenium.dev/documentation/webdriver/actions_api/mouse/#move-to-element

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        driver.get("https://itbootcamp.rs/");


        List<WebElement> padajuciMeni = driver.findElements(By.xpath("//*[contains(@id, 'menu-main-menu')]/li[position()>1 and position()<5]"));

        Thread.sleep(3000);
        for (int i = 0; i < ((List<?>) padajuciMeni).size(); i++) {
            Thread.sleep(3000);
            new Actions(driver).moveToElement(padajuciMeni.get(i)).perform();
            wait.until(ExpectedConditions.visibilityOf(padajuciMeni.get(i)));
        }


        Thread.sleep(5000);
        driver.quit();
    }
}
