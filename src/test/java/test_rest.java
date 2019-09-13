import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import org.seleniumhq.jetty9.io.WriterOutputStream;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;


public class test_rest {
    private static final String LOGIN = "nadenkee";
    private static final String PASS = "1q2w3e";
    private static final String MUSIC_NAME = "circles";
    private static final String ALBUM_NAME = "ajr";
    private static final String WEB_ADDR_LOGIN = "https://secure.last.fm/ru/login";
    private static final String WEB_ADDR_LOGOUT = "https://www.last.fm/ru/logout";

    private FileWriter report;

    @Test
    public void loginTest() throws IOException {
        RestAssured
                .given()
                    .auth().basic(LOGIN, PASS)
                .when()
                    .get(WEB_ADDR_LOGIN)
                .then()
                    .log().status();
    }

    @Test
    public void searchTest() throws IOException {
        RestAssured
                .given()
                    .expect().statusCode(200)
                .when()
                    .get("/tracks?q="+ MUSIC_NAME)
                .then()
                    .log().headers();
        RestAssured
                .given()
                    .expect().statusCode(200)
                .when()
                    .get( "/albums?q="+ALBUM_NAME)
                .then()
                    .log().headers();
    }

    @Test
    public void logoutTest() throws IOException {
        RestAssured
                .given()
                .when()
                .get(WEB_ADDR_LOGOUT)
                .then()
                .log().status();
    }


    @BeforeClass
    public void init() throws IOException {
        RestAssured.baseURI = "https://www.last.fm/ru/search/";
        report = new FileWriter("report.txt", true);
        PrintStream printStream = new PrintStream(new WriterOutputStream(report), true);
        RestAssured.config = RestAssured.config().logConfig(LogConfig.logConfig().defaultStream(printStream));
    }
}