// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/samikshyaadhikari/Documents/my_app/unit_converter/conf/routes
// @DATE:Sun Nov 22 22:16:07 CST 2020

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseUnitConverter UnitConverter = new controllers.ReverseUnitConverter(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseUnitConverter UnitConverter = new controllers.javascript.ReverseUnitConverter(RoutesPrefix.byNamePrefix());
  }

}
