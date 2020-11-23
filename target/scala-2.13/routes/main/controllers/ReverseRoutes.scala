// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/samikshyaadhikari/Documents/my_app/unit_converter/conf/routes
// @DATE:Sun Nov 22 22:16:07 CST 2020

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:5
package controllers {

  // @LINE:5
  class ReverseUnitConverter(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:5
    def convertUnits(units:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "units/si" + play.core.routing.queryString(List(Some(implicitly[play.api.mvc.QueryStringBindable[String]].unbind("units", units)))))
    }
  
  }


}
