// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/samikshyaadhikari/Documents/my_app/unit_converter/conf/routes
// @DATE:Sun Nov 22 22:16:07 CST 2020

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:5
package controllers.javascript {

  // @LINE:5
  class ReverseUnitConverter(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:5
    def convertUnits: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UnitConverter.convertUnits",
      """
        function(units0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "units/si" + _qS([(""" + implicitly[play.api.mvc.QueryStringBindable[String]].javascriptUnbind + """)("units", units0)])})
        }
      """
    )
  
  }


}
