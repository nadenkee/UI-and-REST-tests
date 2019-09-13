import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static java.lang.System.setProperty;


public class test_ui {
    private static final String LOGIN = "nadenkee";
    private static final String PASS = "1q2w3e";
    private static final String MUSIC_NAME = "circles";
    private static final String ALBUM_NAME = "ajr";
    private static final String WEB_ADDR = "https://www.last.fm/ru/";
    private static final String WEB_ADDR_LOGOUT = "https://www.last.fm/ru/logout";


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
        driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[3]/div[2]/div/div/form/div[3]/div/button")).click();
        // 3 - Perform searches
        //composition named MUSIC_NAME
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/nav/div/a[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"site-search\"]")).sendKeys(MUSIC_NAME);
        driver.findElement(By.xpath("//*[@id=\"mantle_skin\"]/div[4]/div/div[1]/form/button")).click();
        //only tracks, not albums nor artists
        driver.findElement(By.xpath("//*[@id=\"mantle_skin\"]/div[3]/div/div[3]/nav/ul/li[4]/a")).click();
        //albums named ALBUM_NAME
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]/nav/div/a[1]")).click();
        driver.findElement(By.xpath("//*[@id=\"site-search\"]")).sendKeys(ALBUM_NAME);
        driver.findElement(By.xpath("//*[@id=\"mantle_skin\"]/div[4]/div/div[1]/form/button")).click();
        //only albums, not compositions nor artists
        driver.findElement(By.xpath("//*[@id=\"mantle_skin\"]/div[3]/div/div[3]/nav/ul/li[3]")).click();
        //4 - Perfom logout
        driver.navigate().to(WEB_ADDR_LOGOUT);
        //sometimes it throws pop-up banner, but the user is logged out anyway
        // 5 - Close browser
        driver.quit();
    }
}