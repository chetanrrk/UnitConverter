package controllers;

import play.mvc.*;
import play.libs.Json;
import com.fasterxml.jackson.databind.node.ObjectNode;


/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 * @author Chetan Raj Rupakheti
 */
public class UnitConverter extends Controller {
	/**
	 * Method to convert between arbitrary unit to SI
	 * @param units Input string 
	 * @return result SI unit in json format
	 */
    public Result convertUnits(String units) {
        ObjectNode result = Json.newObject(); // response from the web server

        try {
        	UnitConverterHelper converter = new UnitConverterHelper(units);
        	converter.checkParentheses();
        	converter.checkOperators();
        	converter.unitStringInSI = new StringBuffer();
            double num = converter.evaluate();
            String resultStr = converter.unitStringInSI.toString();
            
            result.put("unit_name", resultStr);
            result.put("multiplication_factor", num);

            //return ok(views.html.units.si.render(Json.stringify(result)));
            return ok(result); // json result
        }
        catch (IllegalArgumentException e) {
            return status(BAD_REQUEST, e.getMessage());
        }
    }
}


