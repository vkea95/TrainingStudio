package scala.learning

class BasicTrait {

}


//trait Logger {
//  def log(msg: String): Unit = {
//    println("log = " + msg)
//  }
//}
//class TraitTest extends Logger {
//  def test: Unit = {
//    log("XXXX")
//  }
//}

trait Logger {
  def log (msg:String)
}

trait ConsoleLogger extends Logger {
   def log(msg: String): Unit ={
     println(msg)
   }
}
object basic extends App {
//  val t = new TraitTest
//  t.test
}