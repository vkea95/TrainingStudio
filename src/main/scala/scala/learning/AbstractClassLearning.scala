package scala.learning

class AbstractClassLearning {

}

abstract class Person {
  def speak

  val name: String
  var age: Int
}

class Student extends Person {
  override def speak: Unit = {
    println("Studen")
  }

  override val name = "Student name"
  override var age = 100
}

object Basic3 extends App {
  val s = new Student
  s.speak
  println(s.name + "  " + s.age)
}