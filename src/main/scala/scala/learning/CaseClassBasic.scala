package scala.learning

class CaseClassBasic {

}

case class Book(name: String, author: String)

object CaseClassBasic extends App {
  val macTalk = Book("MacTalk", "CJQ")
  macTalk match {
    case Book(name, author) => println("Book type")
    case _ => println("unknow ")
  }
}

