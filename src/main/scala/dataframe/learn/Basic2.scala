package dataframe.learn

class Basic2 {

}

class Person(name: String, val age: Int) {
  println("This is the primary constructor")
  var gender: String = _

  def this(name: String, age: Int, gender: String) {
    this(name, age)
    this.gender = gender
  }
}

class Student(name: String, age: Int, val major: String) extends Person(name, age) {
  println("this is in the Student, major is" + major)
}

object Basic2 {
  def main(args: Array[String]) {
//    val p = new Person("Jack", 10)
//    println(p)
    //    p.name = "jack"
    //
    //    println(p.name + ": " + p.age)

    val s = new Student("jack", 20, "Math")
  }
}