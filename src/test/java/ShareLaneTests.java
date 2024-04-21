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
        // 1.открыть браузер
        RemoteWebDriver driver = new ChromeDriver(); //для взаимодействия с окном браузера
        driver.manage().window().maximize(); //размер экрана
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); //время ожидания

        //  2.navigate (https://sharelane.com/cgi-bin/register.py
        driver.navigate().to("https://sharelane.com/cgi-bin/register.py");

        //3. ввести 5 символов в поле Zip code
        driver.findElement(By.name("zip_code")).sendKeys("12345"); //нашли через девтулы в браузере в коде поля name

        // либо WebElement zipCodeInput=driver.findElement(By.name("zip_code"));// нашли элемент и присвоили его переменной
        // zipCodeInput.sendKeys("12345");

        //4.нажать кпопку Continue
        driver.findElement(By.cssSelector("input[value=Continue]")).click(); //из тулов на сайте

        //5. проверить что мы перешли на след страницу (проверить, что есть уникальный
        // элемент на нужной нам след.странице firstname или register button)

        Assert.assertTrue(driver.findElement(By.name("first_name")).isDisplayed());
        //либо
        //boolean isFirstNameDisplayed=driver.findElement(By.name("first_name")).isDisplayed();
        //Assert.assertTrue(isFirstNameDisplayed); //проверка

        Thread.sleep(3000); //время для прогрузки сайта

        driver.quit();// close - закрывает текущую вкладку, а quit - весь браузер

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

        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void signUpNegativeTest2() throws InterruptedException
    {
        RemoteWebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.navigate().to("https://sharelane.com/cgi-bin/register.py");

        driver.findElement(By.name("zip_code")).sendKeys("");

        driver.findElement(By.cssSelector("input[value=Continue]")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("span[class=error_message]")).isDisplayed());

        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void firstNamePositiveTest() throws InterruptedException
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

        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void firstNameNegativeTest() throws InterruptedException
    {
        RemoteWebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.navigate().to("https://sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[value=Continue]")).click();

        driver.findElement(By.name("first_name")).sendKeys("12345");

        driver.findElement(By.name("email")).sendKeys("ivanov@gmail.com");
        driver.findElement(By.name("password1")).sendKeys("Qwerty1#");
        driver.findElement(By.name("password2")).sendKeys("Qwerty1#");
        driver.findElement(By.cssSelector("input[value=Register]")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Oops, error on page. Some of your fields have invalid data or email was previously used')]")).isDisplayed());

        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void firstNameNegativeTest2() throws InterruptedException
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

        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void lastNamePositiveTest() throws InterruptedException
    {
        RemoteWebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.navigate().to("https://sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[value=Continue]")).click();
        driver.findElement(By.name("first_name")).sendKeys("Ivan");

        //don't fill th field LastName because it isn't required

        driver.findElement(By.name("email")).sendKeys("ivanov@gmail.com");
        driver.findElement(By.name("password1")).sendKeys("Qwerty1#");
        driver.findElement(By.name("password2")).sendKeys("Qwerty1#");
        driver.findElement(By.cssSelector("input[value=Register]")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Account is created!')]")).isDisplayed());

        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void emailPositiveTest() throws InterruptedException
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

        Thread.sleep(3000);
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

        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void passwordPositiveTest() throws InterruptedException
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

        Thread.sleep(3000);
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
        driver.findElement(By.name("password2")).sendKeys("Q");
        driver.findElement(By.cssSelector("input[value=Register]")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Oops, error on page. Some of your fields have invalid data or email was previously used')]")).isDisplayed());

        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void passwordNegativeTest2() throws InterruptedException
    {
        RemoteWebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.navigate().to("https://sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[value=Continue]")).click();
        driver.findElement(By.name("first_name")).sendKeys("Ivan");

        driver.findElement(By.name("email")).sendKeys("ivanov@gmail.com");

        driver.findElement(By.name("password1")).sendKeys("");
        driver.findElement(By.name("password2")).sendKeys("");
        driver.findElement(By.cssSelector("input[value=Register]")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//*[contains(text(), 'Oops, error on page. Some of your fields have invalid data or email was previously used')]")).isDisplayed());

        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void confirmPasswordPositiveTest() throws InterruptedException
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

        Thread.sleep(3000);
        driver.quit();
    }


}
