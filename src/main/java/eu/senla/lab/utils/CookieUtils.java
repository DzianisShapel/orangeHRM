package eu.senla.lab.utils;

import org.openqa.selenium.Cookie;

public class CookieUtils {

    public static Cookie convertRestAssuredToSeleniumCookie(io.restassured.http.Cookie cookie){
         Cookie seleniumCookie = new Cookie(cookie.getName(), cookie.getValue(), cookie.getDomain(),
                cookie.getPath(), cookie.getExpiryDate(),cookie.isSecured(),
                cookie.isHttpOnly(),cookie.getSameSite());
         return seleniumCookie;
    }
}
