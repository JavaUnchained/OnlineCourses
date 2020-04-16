import scala.math.BigDecimal.RoundingMode.HALF_UP
object Example extends App{
  val message = "hello"

  println(message)

  def normalDistribution(mu: Double, sigma: Double, x: Double): Double = {
    val xx = x-mu
    val p = (1 / (sigma * Math.sqrt(2 * Math.PI))) * Math.exp( - xx * xx / 2 * sigma * sigma)
    p
  }
  def crispsWeight(weight: BigDecimal, potatoWaterRatio: Double, crispsWaterRatio: Double): BigDecimal = {
    ((weight - weight * (1 - (potatoWaterRatio - crispsWaterRatio)))).setScale(5, HALF_UP)
  }

  def forCycle = {
    for (i <- 1 to 10) {
      println(i)
    }
  }

  def whileCycle = {
    var i = 0
    while (i <= 10){
      println(i)
      i += 1
    }
  }
  whileCycle
  println(crispsWeight(90,0.9,0.1))
  println(crispsWeight(100.0,0.99,0.98))

  println("for")
  forCycle

  val name = "Andrey" + " Vladimirovich" // конкатенация
  val greeting = s"Hello $name!!!" // интерполяция
  println(greeting)

  var str =
    """
      |Все
      |Переносы строк
      |Сохраняться такими
      |Какие они есть
      |""".stripMargin

  println(str)

  str.contains("Все")
  val b : Boolean = str contains "все"

//  str.startsWith()
//  str.endsWith()

//  str.matches() метод нужен для поиска с регулярками

  val regularExpression = "(.)\\1".r // именно .r делает выражение регуляркой
  //далее работаю все методы
//  regularExpression.findFirstIn(someString)

  //def some(u:Int , n:Int*) -- в таком случае  принимает список элементов последним аргументов
  // если метод не возвращает ничего то он  def som() : Unit - имеет тип юнит
  // метод может не иметь параметры
  // метод может иметь вложеные методы
  // можно опускать возвращаемое значение если оно очевидно

  // можно определить значение по умолчанию
  // но  без умолчания должны идти первые аргументы
  def piffagor (c : Int  , a:Int = 1, b : Int= 1): Int ={c * a * b}
  println(piffagor(5).toString)
  print(piffagor(b = 100,c = 2 ))// можно указывать конкретно для какой переменной какое значение

  // хвтостовая рекурсия
  def sumEven(n:Int):Long = {
    def go(i:Int = 2, acc:Long = 0) :Long = {
      if (i > n) 0
      else go(i + 2, acc + i) // i + go(i + 2) в таком случае будет переполнение стека, а с акамулятором acc не будет
    }
    go()
  }

  print(sumEven(100))
}


