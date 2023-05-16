package eu.senla.lab.api.actions;

import eu.senla.lab.utils.ConfigLoader;
import io.restassured.http.Cookie;

import java.util.HashMap;

import static eu.senla.lab.constants.Route.LOGIN;
import static eu.senla.lab.constants.Route.VALIDATE;
import static io.restassured.RestAssured.given;

public class ApiRequests extends SpecBuilder {


    private static String getTokenFromLoginPage(){

        return  given().
                spec(getRequestSpecification()).
                log().all().
        when().
                get(LOGIN).

                htmlPath().getString("html.body.div.auth-login.@:token");
                //"**.findAll { it.@name == 'woocommerce-register-nonce' }.@value"
    }

    private static Cookie getAuthCookie(){
        HashMap<String, String> formParams = new HashMap<>();
        formParams.put("_token", getTokenFromLoginPage());
        formParams.put("username", ConfigLoader.getInstance().getUsername());
        formParams.put("password", ConfigLoader.getInstance().getPassword());

        return given().
                spec(getRequestSpecification()).
                formParams(formParams).
                log().all().
        when().
                post(VALIDATE).
                getDetailedCookie("orangehrm");

    }


}
