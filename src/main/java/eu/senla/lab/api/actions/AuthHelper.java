package eu.senla.lab.api.actions;

import eu.senla.lab.utils.ConfigLoader;


import io.restassured.http.Cookie;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.HashMap;

import static eu.senla.lab.constants.Route.LOGIN;
import static eu.senla.lab.constants.Route.VALIDATE;
import static io.restassured.RestAssured.*;


public class AuthHelper extends SpecBuilder {

    static Cookie cookie;
    static String token;

    public static Cookie getAuthCookie(){
        getTokenFromLoginPage();
        HashMap<String, String> formParams = new HashMap<>();
        formParams.put("_token", token);
        formParams.put("username", ConfigLoader.getInstance().getUsername());
        formParams.put("password", ConfigLoader.getInstance().getPassword());
        return given().
                spec(getRequestSpecification()).
                cookie(cookie).
                formParams(formParams).
        when().
                post(VALIDATE).
        then().spec(getResponseSpecification()).
                statusCode(302).
                extract().
                response().detailedCookie("orangehrm");
    }

    private static void getTokenFromLoginPage(){
        Response response =  given().
                spec(getRequestSpecification()).
        when().
                get(LOGIN).
        then().spec(getResponseSpecification()).
                extract().
                response();
        cookie = response.detailedCookie("orangehrm");
        Document doc = Jsoup.parse(
                response.body().prettyPrint()
        );
        Element element  = doc.selectFirst("auth-login");
        token = element.attr("token");
    }
}
