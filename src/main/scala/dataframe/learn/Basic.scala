package dataframe.learn


class Basic {

}

object Basic {
  def hello(name: String = "China"): String = { // public static string hello(string name)={return xx}
    return "Hello :" + name
  }

  def helloScala() {
    println("hello")
  }

  //颗粒化
  def add2(x: Int)(y: Int) = x + y

  //  函数是一等公民，可以直接赋给变量
  val adds = (x: Int, y: Int) => {
    x + y
  }

  //*代表可变参数
  def printEveryChar(c: String*) {
    c.foreach(x => println(x))
  }

  def main(args: Array[String]): Unit = {
    print(hello())
    adds(1, 2)
    println(add2(1)(9))
    printEveryChar("a", "3")
    val x = 3
    val a = if (x > 0) 1 else 2

    var (n, r) = (10, 0)

    while (n > r) {
      r += n
      n -= 1
    }
    print("n :" + n)
    print("r :" + r)
  }

  println("for loop")
  for (i <- 1 to 10) {
    println("i: " + i)
  }
  println("for loop even")
  for (i <- 1 to 10 if i % 2 == 0) {
    println("even: " + i)
  }
}