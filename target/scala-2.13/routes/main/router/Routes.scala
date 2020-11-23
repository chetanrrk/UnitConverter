// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/samikshyaadhikari/Documents/my_app/unit_converter/conf/routes
// @DATE:Sun Nov 22 22:16:07 CST 2020

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:5
  UnitConverter_0: controllers.UnitConverter,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:5
    UnitConverter_0: controllers.UnitConverter
  ) = this(errorHandler, UnitConverter_0, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, UnitConverter_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """units/si""", """controllers.UnitConverter.convertUnits(units:String)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:5
  private[this] lazy val controllers_UnitConverter_convertUnits0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("units/si")))
  )
  private[this] lazy val controllers_UnitConverter_convertUnits0_invoker = createInvoker(
    UnitConverter_0.convertUnits(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UnitConverter",
      "convertUnits",
      Seq(classOf[String]),
      "GET",
      this.prefix + """units/si""",
      """ An example controller showing a sample home page""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:5
    case controllers_UnitConverter_convertUnits0_route(params@_) =>
      call(params.fromQuery[String]("units", None)) { (units) =>
        controllers_UnitConverter_convertUnits0_invoker.call(UnitConverter_0.convertUnits(units))
      }
  }
}
