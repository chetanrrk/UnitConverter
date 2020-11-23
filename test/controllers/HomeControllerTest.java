package controllers;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;
import play.libs.Json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.route;
import static play.test.Helpers.contentAsString;

/**
 * This is a test class to test various aspects of the application
 * For example return status, response type, correct results, and invalid inputs
 * @author Chetan Rupakheti
 *
 */

public class HomeControllerTest extends WithApplication {

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    public Result getResponse(String units) {
    	String uriString = "/units/si?units=".concat(units); 
        Http.RequestBuilder request = new Http.RequestBuilder()
                .method(GET)
                .uri(uriString);

        Result result = route(app, request);
        return result;
    }
    
    @Test
    public void testReturnStatus() {
    	Result result = getResponse("(degree/min)");
        assertEquals(OK, result.status());
    }
    
    @Test
    public void testReturnType() {
    	Result result = getResponse("(degree/min)");
        assertEquals(result.contentType().toString(), 
        		"Optional[application/json]");
    }
    
	@Test
    public void testResults() {
    	Result result = getResponse("(degree/min)");
    	JsonNode json = Json.parse(contentAsString(result));
    	ObjectNode resultJson = Json.newObject(); 
    	resultJson = Json.fromJson(json, resultJson.getClass());
    	double multFactor = resultJson.get("multiplication_factor").doubleValue();
    	assertEquals(resultJson.get("unit_name").toString(), "\"(rad/s)\"");
    	assertEquals(multFactor, 0.00029088820866572, 0.0);
    }
	
	@Test
	public void testException() {
		Result result = getResponse("degree/min)");
		assertEquals(contentAsString(result), 
				"please recheck and fix your parentheses!");
	}
    
}
