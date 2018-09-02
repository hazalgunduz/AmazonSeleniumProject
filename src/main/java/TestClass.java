import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class TestClass {

    private WebDriver driver;
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();
    private String eMailAddress;
    private String password;


    /*
    Opens the driver and goes to the url given.  Checks whether it is loaded.
    throws Assertion Error if not
     */
    @BeforeClass
    public void setUp() {
        driver = new FirefoxDriver();
        baseUrl = new String("https://www.amazon.com/");
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        try {
            Assert.assertTrue(driver.findElement(By.linkText("Back to top")).isDisplayed());
        } catch (AssertionError assertionError) {
            System.out.println("An error Occurred, page cannot be loaded");
            System.exit(1);
        }

    }

    @Before
    public void testAmazonLogIn() {

        eMailAddress = new String("hazalgunduz@yahoo.com");
        password = new String("123456");

        driver.findElement(By.cssSelector("#nav-link-accountList > span:nth-child(1)")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("#ap_email")).clear();
        driver.findElement(By.cssSelector("#ap_email")).sendKeys(eMailAddress);
        driver.findElement(By.cssSelector(".a-button-input")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("#ap_password")).clear();
        driver.findElement(By.cssSelector("#ap_password")).sendKeys(password);
        driver.findElement(By.cssSelector("#signInSubmit")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void itemSearch() {

        driver.findElement(By.id("twotabsearchtextbox")).clear();
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Samsung");
        driver.findElement(By.cssSelector(".nav-search-submit > input:nth-child(2)")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        int count = driver.findElements(By.linkText("Samsung")).size();

        try {
            Assert.assertTrue(count >= 1);
        } catch (AssertionError assertionError) {
            System.exit(1);
        }

        driver.findElement(By.cssSelector("span.pagnLink:nth-child(3) > a:nth-child(1)")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        try {
            Assert.assertTrue(driver.getCurrentUrl().contains("sr_pg_2"));
        } catch (AssertionError assertionError) {
            System.out.println("Page 2 cannot be reached");
            System.exit(1);
        }

        driver.findElement(By.linkText("Click to see price")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        String urlCheck = new String(driver.findElement(By.className("a-size-large")).getText().toString());

        driver.findElement(By.id("add-to-wishlist-button-submit")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("#WLHUC_viewlist > span:nth-child(1) > span:nth-child(1)")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        try {
            driver.findElement(By.linkText(urlCheck)).isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("Element not found in Wish List");
            System.exit(1);
        }

        driver.findElement(By.name("submit.deleteItem")).click();

        try {
            driver.findElement(By.cssSelector("div.a-alert-inline:nth-child(1) > div:nth-child(1) > div:nth-child(2)")).isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("Element cannot be removed");
            System.exit(1);
        }

    }

    @After
    public void tearDown() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }


}
