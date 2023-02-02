package d31_01_2023;

import helper.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Zadatak3 {
    public static void main(String[] args) throws IOException, InterruptedException {
//        3. Zadatak
//        Napisati program koji:
//        Ucitava stranicu https://itbootcamp.rs/
//        Skrola do slajdera na dnu stranice (u kome su slike Java, Php, Selenium, …)
//        Cita sve linkove slika iz slajdera
//        Proverava url svake slike, i za sve slike koje imaju status veci
//        i jednak od 200 a manji od 300, skida i smesta u
//        folder itbootcamp_slider u okviru projekta
//        Azurirajte gitignore da ignorise itbootcamp_slider folder

        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://itbootcamp.rs/");

        new Actions(driver).moveToElement
                (driver.findElement(By.xpath("//div[contains(@class, 'slider_bkgd')]"))).perform();

        List<WebElement> elements = driver.findElements(By.xpath("//div[contains(@class, 'owl-item')]/div/img"));

        Helper helper = new Helper();

        for (int i = 0; i < elements.size(); i++) {
            if (helper.getHTTPResponseStatusCode(elements.get(i).getAttribute("src")) >= 200 &&
                    helper.getHTTPResponseStatusCode(elements.get(i).getAttribute("src")) < 300
            ) {
                helper.downloadUsingStream
                        (elements.get(i).getAttribute("src"), "itbootcamp_slider/slika"+i+".jpg");
            } else {
                System.out.println("Element HTTP Status Code is not valid!");
            }
        }
        Thread.sleep(5000);
        driver.quit();

    }
}
