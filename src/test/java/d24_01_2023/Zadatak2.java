package d24_01_2023;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

public class Zadatak2 {
    public static void main(String[] args) throws InterruptedException {
//        Zadatak
//        Niz todo-a (niz stringova) koje treba da uneti. Niz je:
//        Visit Paris
//        Visit Prague
//        Visit London
//        Visit New York
//        Visit Belgrade
//        Maksimizirati prozor
//        Ucitati stranicu https://example.cypress.io/todo
//        Program petljom prolazi kroz niz todo-a i svaki unosi na stranicu
//        Nakon svakog unosa todo-a, unosi se enter
//        Cekanje od 5s
//        Zatvorite pretrazivac
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        ArrayList<String>toDoList = new ArrayList<>();
        toDoList.add("Visit Paris");
        toDoList.add("Visit Prague");
        toDoList.add("Visit London");
        toDoList.add("Visit New York");
        toDoList.add("Visit Belgrade");

        driver.manage().window().maximize();
        driver.get("https://example.cypress.io/todo");

        for (int i = 0; i < toDoList.size(); i++) {

            driver.findElement(By.xpath("//input[@placeholder ='What needs to be done?']")).
                    sendKeys(toDoList.get(i));
            Thread.sleep(1000);
            driver.findElement(By.xpath("//input[@placeholder ='What needs to be done?']")).
                    sendKeys(Keys.ENTER);
            
        }
        Thread.sleep(5000);
        driver.quit();

    }

}
