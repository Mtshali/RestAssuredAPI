package stepDefinitions;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.util.JSONPObject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestLogSpecification;
import io.restassured.specification.RequestSpecification;

import org.json.simple.JSONObject;

import static org.junit.Assert.*;

public class Products {

    public int StatusCode;
    public RequestSpecification httpRequest;
    public Response response;
    public ResponseBody body;
    public JSONObject requestParams;
    public int responseCode;

    @Given("I hit the url of get products api endpoint")
    public void I_hit_the_url_of_get_products_api_endpoint() {

        RestAssured.baseURI = "https://fakestoreapi.com/";

    }

    @When("I pass the url in the products in the request")
    public void i_pass_the_url_in_the_products_in_the_request() {
        httpRequest = RestAssured.given();
        response = httpRequest.get("products");

    }

    @Then("I receive the response code as {int}")
    public void i_receive_the_response_code_as(Integer int1) {
        responseCode = response.getStatusCode();
        assertEquals(responseCode, 200);
    }

    @Then("I verify that the rate of the first products is {}")
    public void i_verify_that_the_rate_of_the_first_products_is_first_product_rate(String rate) {

        body = response.getBody();

        // Convert Response body to string
//        String responseBody = body.asString();

        //Json Representation from Response body
        JsonPath jsnPath = response.jsonPath();
        String s = jsnPath.getJsonObject("rating[0].rate").toString();

        assertEquals(rate, s);

//        System.out.println(response.getStatusLine());
//        System.out.println(body.asString());
    }

    @Given("I hit the url of POST products api endpoint")
    public void iHitTheUrlOfPOSTProductsApiEndpoint() {

        RestAssured.baseURI = "https://fakestoreapi.com/";
        httpRequest = RestAssured.given();
        requestParams = new JSONObject();

    }

    @And("I pass the request body of product title {}")
    public void iPassTheRequestBodyOfProductTitleProductsTitle(String title) {
        requestParams.put("title", title);
        requestParams.put("price", 168);
        requestParams.put("description", "Satisfaction Guaranteed. Return sold by Sikhumbuzo Center in the United States.");
        requestParams.put("image", "https://fakestoreapi.com/img/61sbMiUnoGL._AC_UL640_QL65_ML3_.jpg");
        requestParams.put("category", "jewelery");

        httpRequest.body(requestParams.toJSONString());
        Response response = httpRequest.post("products");
        ResponseBody body = response.getBody();


        System.out.println(response.getStatusLine());
        System.out.println(body.asString());
    }

    @Then("I receive the response bady with id as {}")
    public void iReceiveTheResponseBadyWithIdAsID(String strId) {

        JsonPath jsnPath = response.jsonPath();
        String s = jsnPath.getJsonObject("id").toString();

        assertEquals("21", s);
    }
}
