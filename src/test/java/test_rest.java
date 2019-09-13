import io.restassured.RestAssured;
import org.testng.annotations.Test;


public class test_rest {
    private static final String LOGIN = "nadenkee";
    private static final String PASS = "1q2w3e";
    private static final String MUSIC_NAME = "circles";
    private static final String ALBUM_NAME = "ajr";
    private static final String WEB_ADDR = "https://www.last.fm/ru/";
    private static final String WEB_ADDR_LOGIN = "https://secure.last.fm/ru/login";
    private static final String WEB_ADDR_LOGOUT = "https://www.last.fm/ru/logout";

    @Test
    public void loginTest() {
        RestAssured
                .given().auth().basic(LOGIN, PASS).expect().statusCode(200)
                .when().get(WEB_ADDR_LOGIN)
                .then().log().status();
    }

    @Test
    public void searchTest() {
        RestAssured
                .given()
                .expect().statusCode(200)
                .when()
                .get(WEB_ADDR+"search/tracks?q="+ MUSIC_NAME)
                .then()
                .log().status();

        RestAssured
                .given()
                .expect().statusCode(200)
                .when()
                .get( WEB_ADDR+"search/albums?q="+ALBUM_NAME)
                .then()
                .log().status();
    }

    @Test
    public void logoutTest() {
        RestAssured
                .given()
                .when()
                .get(WEB_ADDR_LOGOUT)
                .then()
                .log().status();
    }
}