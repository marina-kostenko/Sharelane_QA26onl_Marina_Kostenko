import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;


public class ShareLaneTests {
    @Test
    public void signUpPositiveTest() throws InterruptedException
    {
        RemoteWebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.navigate().to("https://sharelane.com/cgi-bin/register.py");

        driver.findElement(By.name("zip_code")).sendKeys("12345");

        driver.findElement(By.cssSelector("input[value=Continue]")).click();

        Assert.assertTrue(driver.findElement(By.name("first_name")).isDisplayed());

        driver.quit();
    }

    @Test
    public void signUpNegativeTest() throws InterruptedException
    {
        RemoteWebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.navigate().to("https://sharelane.com/cgi-bin/register.py");

        driver.findElement(By.name("zip_code")).sendKeys("1234");

        driver.findElement(By.cssSelector("input[value=Continue]")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("span[class=error_message]")).isDisplayed());

        driver.quit();
    }

    @Test
    public void signUpNegativeTestEmptyField() throws InterruptedException
    {
        RemoteWebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.navigate().to("https://sharelane.com/cgi-bin/register.py");

        driver.findElement(By.name("zip_code")).sendKeys("");

        driver.findElement(By.cssSelector("input[value=Continue]")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("span[class=error_message]")).isDisplayed());

        driver.quit();
    }

    @Test
    public void allFieldsPositiveTest() throws InterruptedException
    {
        RemoteWebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.navigate().to("https://sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[value=Continue]")).click();
        driver.findElement(By.name("first_name")).sendKeys("Ivan");
        driver.findElement(By.name("email")).sendKeys("ivanov@gmail.com");
        driver.findElement(By.name("password1")).sendKeys("Qwerty1#");
        driver.findElement(By.name("password2")).sendKeys("Qwerty1#");
        driver.findElement(By.cssSelector("input[value=Register]")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Account is created!')]")).isDisplayed());

        driver.quit();
    }

    @Test
    public void firstNameNegativeTestEmptyField() throws InterruptedException
    {
        RemoteWebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.navigate().to("https://sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[value=Continue]")).click();

        driver.findElement(By.name("first_name")).sendKeys("");

        driver.findElement(By.name("email")).sendKeys("ivanov@gmail.com");
        driver.findElement(By.name("password1")).sendKeys("Qwerty1#");
        driver.findElement(By.name("password2")).sendKeys("Qwerty1#");
        driver.findElement(By.cssSelector("input[value=Register]")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Oops, error on page. Some of your fields have invalid data or email was previously used')]")).isDisplayed());

        driver.quit();
    }

    @Test
    public void emailNegativeTest() throws InterruptedException
    {
        RemoteWebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.navigate().to("https://sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[value=Continue]")).click();
        driver.findElement(By.name("first_name")).sendKeys("Ivan");

        driver.findElement(By.name("email")).sendKeys("ivanov@");

        driver.findElement(By.name("password1")).sendKeys("Qwerty1#");
        driver.findElement(By.name("password2")).sendKeys("Qwerty1#");
        driver.findElement(By.cssSelector("input[value=Register]")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Oops, error on page. Some of your fields have invalid data or email was previously used')]")).isDisplayed());

        driver.quit();
    }

    @Test
    public void passwordNegativeTest() throws InterruptedException
    {
        RemoteWebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.navigate().to("https://sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[value=Continue]")).click();
        driver.findElement(By.name("first_name")).sendKeys("Ivan");

        driver.findElement(By.name("email")).sendKeys("ivanov@gmail.com");

        driver.findElement(By.name("password1")).sendKeys("Q");
        driver.findElement(By.name("password2")).sendKeys("Qwerty1#");
        driver.findElement(By.cssSelector("input[value=Register]")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Oops, error on page. Some of your fields have invalid data or email was previously used')]")).isDisplayed());

        driver.quit();
    }

}
