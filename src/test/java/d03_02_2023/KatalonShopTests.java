package d03_02_2023;

import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class KatalonShopTests {

//    Zadatak
//    Kreirati KatalonShopTests klasu:
//    baseUrl: https://cms.demo.katalon.com
//    Test #1:  Adding product with quantity to the cart
//            Prioritet = 10
//    Koraci:
//    Ucitati stranicu /product/flying-ninja/
//    Unesite kolicinu 3
//    Klik na Add to cart dugme
//    Verifikovati da poruka sadrzi tekst “Flying Ninja”.
//    Klik na Cart link iz navigacije
//    Verifikovati da u url-u stoji /cart ruta
//    Verifikovati da je broj proizvoda u korpi jednako 1

    private WebDriver driver;
    private WebDriverWait wait;
    private String baseUrl = "https://cms.demo.katalon.com";

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        this.driver = new ChromeDriver();

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().window().maximize();
    }

        @BeforeMethod
        public void beforeMethod(){
            driver.get(baseUrl);
        }

        @Test(priority = 10)
        @Description("Adding product with quantity to the cart")
        public void addingProductWithQuantityToTheCart() throws InterruptedException {

        driver.get(baseUrl + "/product/flying-ninja/");
        driver.findElement
                    (By.xpath("//div[@class='quantity-button quantity-up']")).click();
        Thread.sleep(3000);
        driver.findElement
                    (By.xpath("//div[@class='quantity-button quantity-up']")).click();
        driver.findElement(By.name("add-to-cart")).click();
        WebElement message = driver.findElement(By.className("woocommerce-message"));
        Assert.assertTrue(message.getText().contains("Flying Ninja"),
                "Message doesn't contain right message");

        driver.findElement
                (By.xpath("//a[@class = 'button wc-forward']")).click();
        Assert.assertEquals(driver.getCurrentUrl(),
                baseUrl+ "/cart/",
                "Error URL");
        int cartElements = driver.findElements
                (By.xpath("//*[@class = 'entry-content']/div/form")).size();
        Assert.assertEquals(cartElements,
                1,
                "No products in cart");


    }
//    Test #2:  Removing product from cart
//    Prioritet = 20
//    Koraci:
//    Klik na Cart link iz navigacije
//    Verifikovati da u url-u stoji /cart ruta
//    Verifikovati da je broj proizvoda u korpi jednako 1
//    Klik na remove dugme iz prvog reda
//    Verifikovati da je broj proizvoda u korpi jedako 0

        @Test(priority = 20)
        @Description("Removing product from cart")
        public void removingProductFromCart() throws InterruptedException {

            driver.findElement
                    (By.xpath("//a[@href = 'https://cms.demo.katalon.com/cart/']")).click();
            Assert.assertEquals(driver.getCurrentUrl(),
                    baseUrl+ "/cart/",
                    "Error URL");
            int cartElements = driver.findElements
                    (By.xpath("//*[@class = 'entry-content']/div/form")).size();
            Assert.assertEquals(cartElements,
                    1,
                    "No products in cart");
            driver.findElement(By.xpath("//a[@class = 'remove']")).click();
            Thread.sleep(3000);
            cartElements = driver.findElements
                    (By.xpath("//*[@class = 'entry-content']/div/form")).size();
            Assert.assertEquals(cartElements,
                    0,
                    "There are products in cart");
        }

//    Test #3:  Verify error is displayed when username is missing
//    Prioritet = 30
//    Koraci:
//    Kliknite na my account link
//    Klik na login dugme
//    Verifikovati da je prikazana poruka Error: Username is required.
      @Test(priority = 30)
      @Description("Verify error is displayed when username is missing")
      public void verifyErrorIsDisplayedWhenUsernameIsMissing(){
          driver.findElement
                  (By.xpath("//a[@href = 'https://cms.demo.katalon.com/my-account/']")).click();
          driver.findElement(By.name("login")).click();
          String errorMessage = driver.findElement
                  (By.xpath("//*[@class = 'woocommerce-error']")).getText();
          Assert.assertEquals(errorMessage,
                  "Error: Username is required.",
                  "Wrong error message");

      }

//    Test #4:  Verify error is displayed when password is missing
//    Prioritet = 40
//    Koraci:
//    Kliknite na my account link
//    Unesite username customer
//    Klik na login dugme
//    Verifikovati da je prikazana poruka ERROR: The password field is empty.
      @Test(priority = 40)
      @Description("Verify error is displayed when password is missing")
      public void verifyErrorIsDisplayedWhenPasswordIsMissing(){
          driver.findElement
                  (By.xpath("//a[@href = 'https://cms.demo.katalon.com/my-account/']")).click();
          driver.findElement(By.name("username")).
                  sendKeys("customer");
          driver.findElement(By.name("login")).click();
          String errorMessage = driver.findElement
                  (By.xpath("//*[@class = 'woocommerce-error']")).getText();
          Assert.assertEquals(errorMessage,
                  "ERROR: The password field is empty.",
                  "Wrong error message");

    }
//    Test #5:  Verify error is displayed when password is wrong
//    Prioritet = 50
//    Koraci:
//    Kliknite na my account link
//    Unesite username customer
//    Unesite nevalidan pass invalidpassword
//    Klik na login dugme
//    Verifikovati da je prikazana poruka ERROR:
//    The password you entered for the username customer is incorrect. Lost your password?
      @Test(priority = 50)
      @Description("Verify error is displayed when password is wrong")
      public void verifyErrorIsDisplayedWhenPasswordIsWrong(){

          driver.findElement
                  (By.xpath("//a[@href = 'https://cms.demo.katalon.com/my-account/']")).click();
          driver.findElement(By.name("username")).
                  sendKeys("customer");
          driver.findElement(By.name("password")).
                  sendKeys("invalidpassword");
          driver.findElement(By.name("login")).click();
          String errorMessage = driver.findElement
                  (By.xpath("//*[@class = 'woocommerce-error']")).getText();
          Assert.assertEquals(errorMessage,
                  "ERROR: The password you entered for the username customer is incorrect. Lost your password?",
                  "Wrong error message");
      }

//    Test #6:  Verify error is displayed when user does not exist
//            Prioritet = 60
//    Koraci:
//    Kliknite na my account link
//    Unesite username invaliduser
//    Unesite nevalidan pass (ex: pass1234)
//    Klik na login dugme
//    Verifikovati da je prikazana poruka ERROR: Invalid username. Lost your password?
      @Test(priority = 60)
      @Description("Verify error is displayed when user does not exist")
      public void verifyErrorIsDisplayedWhenUserDoesNotExist(){
          driver.findElement
                  (By.xpath("//a[@href = 'https://cms.demo.katalon.com/my-account/']")).click();
          driver.findElement(By.name("username")).
                  sendKeys("invaliduser");
          driver.findElement(By.name("password")).
                  sendKeys("mjaumjau");
          driver.findElement(By.name("login")).click();
          String errorMessage = driver.findElement
                  (By.xpath("//*[@class = 'woocommerce-error']")).getText();
          Assert.assertEquals(errorMessage,
                  "ERROR: Invalid username. Lost your password?",
                  "Wrong error message");
      }
//    Test #7:  Verify successful login
//            Prioritet = 70
//    Koraci:
//    Kliknite na my account link
//    Unesite username customer
//    Unesite validan pass crz7mrb.KNG3yxv1fbn
//    Klik na login dugme
//    Verifikovati na stranici pise Hello Katalon Parlitul_Changed
      @Test(priority = 70)
      @Description("Verify successful login")
      public void VerifySuccessfulLogin(){
          driver.findElement
                  (By.xpath("//a[@href = 'https://cms.demo.katalon.com/my-account/']")).click();
          driver.findElement(By.name("username")).
                  sendKeys("customer");
          driver.findElement(By.name("password")).
                  sendKeys("crz7mrb.KNG3yxv1fbn");
          driver.findElement(By.name("login")).click();

          Assert.assertTrue(driver.findElement(
                  By.xpath("//*[@id='post-10']"))
                  .getText().contains("Hello Katalon Parlitul_Changed"),
                  "Wrong message");
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
