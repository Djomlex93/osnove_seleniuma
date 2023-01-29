package d26_01_2023;



import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


import java.util.ArrayList;
import java.util.List;

public class Zadatak1 {


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
//        Validira da li je novi todo dodat na stranici
//        Na kraju programa proci petljom i izbrisati svaki todo sa stranice (klikom na x dugme svakog todo-a)
//        Validirati da je na kraju programa broj todo-a na stranici 0.
//        Cekanje od 5s
//        Zatvorite pretrazivac
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        ArrayList<String> niz = new ArrayList<>();
        niz.add("Visit Paris");
        niz.add("Visit Prague");
        niz.add("Visit London");
        niz.add("Visit New York");
        niz.add("Visit Belgrade");
        driver.manage().window().maximize();

        driver.get("https://example.cypress.io/todo");

        List<WebElement> lista = driver.findElements(By.xpath("//ul[@class='todo-list']/li"));
        int pocetnoStanje = lista.size();
        int novoStanje = 0;
        for (int i = 0; i < niz.size(); i++) {
            driver.findElement(By.className("new-todo")).sendKeys(niz.get(i));
            driver.findElement(By.className("new-todo")).sendKeys(Keys.ENTER);
            novoStanje = novoStanje + 1;
        }
        int zbir = novoStanje + pocetnoStanje;
        System.out.println("Novo stanje je: " + zbir);
        Thread.sleep(5000);
        Actions actions = new Actions(driver);


        for (int i = 0; i < niz.size() + pocetnoStanje; i++) {
            WebElement dugme = driver.findElement(By.xpath("//ul[@class='todo-list']/li/div"));
            actions.moveToElement(dugme).perform();
            driver.findElement(By.xpath("//ul[@class='todo-list']/li/div/button")).click();
            zbir = zbir - 1;
        }
        if (zbir == 0){
            System.out.println("Stanje u listi je 0");
        }
        driver.quit();

    }
}
