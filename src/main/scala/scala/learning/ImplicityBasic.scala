package scala.learning

class ImplicityBasic extends App {
}

object ImplicityBasicextends extends App {

  //  Implicity class
  implicit def a2RichA(a: A) = new RichA(a)

  val a = new A
  a.rich

  //  implicity parameters
  def testParam(implicit name: String): Unit = {
    println(name)
  }

  implicit val namesssssssssss = "my name"
  implicit val namesssssssssss3 = 3

  testParam


  //  implicity class
  implicit class Calculator(x: Int) {
    def add(a: Int): Int = a + 1
  }
// importxxxx
  println(1.add(2))
}

class A {

}

class RichA(a: A) {
  def rich: Unit = {
    println("rich...")
  }
}