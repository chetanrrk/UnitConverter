// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/samikshyaadhikari/Documents/my_app/unit_converter/conf/routes
// @DATE:Sun Nov 22 22:16:07 CST 2020


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
