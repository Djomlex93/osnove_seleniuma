package d02_02_2023;

import helper.Helper;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;

public class BootStrapTableTests {
//    1.Zadatak
//    Kreirati BootstrapTableTests klasu koja ima:
//    Base url: https://s.bootsnipp.com
    //    Test #1: Edit Row
//    Podaci:
//    First Name: ime polaznika
//    Last Name: prezime polaznika
//    Middle Name: srednje ime polanzika

//
//    Test #2: Delete Row
//    Podaci:
//    First Name: ime polaznika
//    Last Name: prezime polaznika
//    Middle Name: srednje ime polanzika
//    Koraci:
//    Ucitati stranu"Table with Edit and Update Data - Bootsnipp.com");
//    Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
//    Klik na Delete dugme prvog reda
//    Sacekati da dijalog za brisanje bude vidljiv
//    Klik na Delete dugme iz dijaloga
//    Sacekati da dijalog za Editovanje postane nevidljiv
//    Verifikovati da je broj redova u tabeli za jedan manji
//    Za sve validacije ispisati odgovarajuce poruke u slucaju greske
//


    private WebDriver driver;
    private WebDriverWait wait;
    private String baseUrl = "https://s.bootsnipp.com";
    private String firstName = "Mladen";
    private String lastName = "Dimitrijevic";
    private String middleName = "Vojkan";
    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        this.driver = new ChromeDriver();

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();


    }
    @BeforeMethod
    public void beforeMethod(){
        driver.get(baseUrl + "/iframe/K5yrx");
    }


    @Test(priority = 1)
    @Description("Edit Row")
    public void editRow(){
        //    Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
        Assert.assertEquals(driver.getTitle(),
                "Table with Edit and Update Data - Bootsnipp.com",
                "Wrong title name ");
        //    Klik na Edit dugme prvog reda
        //    Sacekati da dijalog za Editovanje bude vidljiv
        driver.findElement(By.xpath("//*[@id='d1']/td[5]/button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.className("modal-content")));

//    Popuniti formu podacima.
//    Bice potrebno da pre unosa tekst pobrisete tekst koji vec postoji,
//    za to se koristi metoda clear. Koristan link
        driver.findElement(By.id("fn")).clear();
        driver.findElement(By.id("ln")).clear();
        driver.findElement(By.id("mn")).clear();

        driver.findElement(By.id("fn")).sendKeys(firstName);
        driver.findElement(By.id("ln")).sendKeys(lastName);
        driver.findElement(By.id("mn")).sendKeys(middleName);
//    Klik na Update dugme

        driver.findElement(By.id("up")).click();
//    Sacekati da dijalog za Editovanje postane nevidljiv
        wait.until(ExpectedConditions.invisibilityOfElementLocated
                (By.className("modal-content")));
//    Verifikovati da se u First Name celiji prvog reda tabele javlja uneto ime
        Assert.assertEquals(driver.findElement(By.id("f1")).getText(),
                firstName,
                "Wrong first name");
//    Verifikovati da se u Last Name celiji prvog reda tabele javlja uneto prezime
        Assert.assertEquals(driver.findElement(By.id("l1")).getText(),
                lastName,
                "Wrong last name");
//    Verifikovati da se u Middle Name celiji prvog reda tabele javlja uneto srednje ime
        Assert.assertEquals(driver.findElement(By.id("m1")).getText(),
                middleName,
                "Wrong middle name");
//    Za sve validacije ispisati odgovarajuce poruke u slucaju greske


    }
    @Test(priority = 2)
    @Description("Delete Row")
    public void deleteRow() throws InterruptedException {
        //    Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
        Assert.assertEquals(driver.getTitle(),
                "Table with Edit and Update Data - Bootsnipp.com",
                "Wrong title name ");
//    Klik na Delete dugme prvog reda
        driver.findElement(By.xpath("//*[@id='d1']/td[6]/button")).click();
//    Sacekati da dijalog za brisanje bude vidljiv
        wait.until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//*[@id='delete']//*[@class='modal-content']")));
//    Klik na Delete dugme iz dijaloga
        driver.findElement(By.id("del")).click();
//    Sacekati da dijalog za Editovanje postane nevidljiv
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By
                .xpath("//*[@id='delete']//*[@class='modal-content']")));
//    Verifikovati da je broj redova u tabeli za jedan manji
        WebElement row = driver.findElement(By.xpath("//tbody/tr[1]"));
        Assert.assertFalse(row.isDisplayed(),"Row is not deleted");
//    Za sve validacije ispisati odgovarajuce poruke u slucaju greske

    }
//        Test #3: Take a Screenshot
//    Koraci:
//    Ucitati stranu  /iframe/K5yrx

      @Test(priority = 3)
      @Description("Take a Screenshot")
      public void takeAScreenShot() throws IOException {
          //    Verifikovati naslov stranice Table with Edit and Update Data - Bootsnipp.com
          Assert.assertEquals(driver.getTitle(),
                  "Table with Edit and Update Data - Bootsnipp.com",
                  "Wrong title name ");
//    Kreirati screenshot stranice.
//    Fajl cuvajte na putanji gde su vam bile slike od proslog domaceg.
//    Na putanji: screenshots/slike.png
          new Helper().takeScreenshot(driver, "screenshots/BootStrapsc" + ".png");

      }
    @AfterMethod
    public void afterMethod(){
        System.out.println("mjauu");
    }
    @AfterClass
    public void afterClass() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }




    }
