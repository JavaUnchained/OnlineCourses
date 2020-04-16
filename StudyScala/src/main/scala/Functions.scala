object Functions extends App {
//  сигнатура объявления функций
//  val x: Int => Int = ...
//  val y: (Int,Int) => = ...
//  максимально 22 параметра у функции

//    Лямбда абстракция
  val addOne: Int => Int = x => x + 1
  val addOne2 = (x:Int) => x + 1 // мы можем объявлять тип в самом выражении
  println(addOne(4) )

  val plus: (Int,Int) => Int = (x,y) => x + y
  val plus1 = (x:Int, y:Int) => x + y

  println(plus1(10,12))

//  Сахар. Если параметр используется 1 раз, и соблюдается порядок переменных то их можно опустить
  val addOne3: Int => Int = _ + 1
  val plus3 = (_:Int) + (_:Int)

//  Эта-конверсия
  def addOne5(x:Int) = x + 1
  val add1 = addOne5 _

  def plus(x: Int, y: Int) = x + y
  val pl: (Int,Int) => Int = plus


//  функции применимы везде где преминимы обычные значения
//  они могут быть подставлены в методы и получены из них


//  Каррирование
  def plus4: Int => Int => Int = x => y => x + y
  println(plus4(1)(2))

  val plus5 = (x: Int, y: Int, z:Int) => x + y + z
  val plus5c : Int => Int => Int => Int = plus5.curried // метод карирования


//  Композиция
  val p = (_:Int) + 1
  val m = (_:Int) * 3

  val plusThenMul = p andThen m // вычисляет аргумент слева и подставляет его в значение справа
  val plusBeforMul = p compose m // вычисляет аргумент справа и подставляет его в функцию слева

  val mul3 = 3*(_: Double)
  val pow2 = (x: Double) => x*x
  println((pow2.andThen[Double] _ )(mul3)(5))
}
