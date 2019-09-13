import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static java.lang.System.setProperty;


public class test_ui {
    public static final String LOGIN = "nadenkee";
    public static final String PASS = "1q2w3e";
    public static final String MUSIC_NAME = "circles";
    public static final String ALBUM_NAME = "ajr";
    public static final String WEB_ADDR = "https://www.last.fm/ru/";
    public static final String WEB_ADDR_LOGOUT = "https://www.last.fm/ru/logout";


    @BeforeClass
    public void BeforeClass() {
        setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
    }

    @Test
    public void SimpleTest() {
        openPage();
    }

    public void openPage() {
        // 1 - Open site
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(WEB_ADDR);
        // 2 - Perfom login
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/nav/div/ul/li[2]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"id_username\"]")).sendKeys(LOGIN);
        driver.findElement(By.xpath("//*[@id=\"id_password\"]")).sendKeys(PASS);
        driver.findElement(By.xpath("//*[@id=\"login\"]/div[3]/div/button")).click();
        // 3 - Perform searches
        //composition named MUSIC_NAME
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/nav/div/a[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"masthead-search-field\"]")).sendKeys(MUSIC_NAME);
        driver.findElement(By.xpath("//*[@id=\"masthead-search\"]/div/button")).click();
        //only tracks, not albums nor artists
        driver.findElement(By.cssSelector("#mantle_skin > div.content-top > div > div.container.content-top-lower > nav > ul > li.navlist-item.secondary-nav-item.secondary-nav-item--tracks")).click();
        //albums named ALBUM_NAME
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/nav/div/a[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"masthead-search-field\"]")).sendKeys(ALBUM_NAME);
        driver.findElement(By.cssSelector("#mantle_skin > div.content-top > div > div.container.content-top-lower > nav > ul > li.navlist-item.secondary-nav-item.secondary-nav-item--albums")).click();
        //only tracks, not compositions nor artists
        driver.findElement(By.xpath("//*[@id=\"mantle_skin\"]/div[3]/div/div[3]/nav/ul/li[3]")).click();
        //4 - Perfom logout
        driver.navigate().to(WEB_ADDR_LOGOUT);
        //sometimes it throws pop-up banner, but the user is logged out anyway
        // 5 - Close browser
        driver.quit();
    }
}
