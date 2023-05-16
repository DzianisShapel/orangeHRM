package eu.senla.lab.api.actions;


import eu.senla.lab.utils.ConfigLoader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {

    public static RequestSpecification getRequestSpecification(){

        return new RequestSpecBuilder().
                setBaseUri(ConfigLoader.getInstance().getBaseUri()).
                log(LogDetail.ALL).
                build();
    }

    public static ResponseSpecification getResponseSpecification(){
        return  new ResponseSpecBuilder().
                log(LogDetail.ALL).
                build();
    }
}
