import scala.io.StdIn._ //  аналог io.* только для скалы

object ReadConsole extends App{
  val name = readLine()
  val numr = readInt()

  println(name + " " + numr)
}

// Стабильные
// object
// lazy val
// val
// параметры
// package

// Нестабильные, каждый раз может возвращать разные значения
// def

// import ru.some.Module.{name, name1}
// можно загрузить сразу несколько имен
// import ru.some.Module.{name => переменованое_значение}

// import ru.tinkoff.Module.{name2 => _, _} // импортировать всё кроме name



// A <: B ( А подтип типа B)
// всё является подтипом Any
// AnyRef - тип всех ссылочных значений
// AnyVal -  любой примитивный тип

// Любой тип является надтипом Nothing , этот тип используется для ошибок

// Примитивный тип:
// Целочисленные : Byte, Short, Int , Long
// Float, Double
// Char
// Boolean
// Unit - единичный тип , принимает ровно одно значение ( оно не интересно и всегда одинаково, использутеся для
// значения которое нам не интересно )


// val x: String = "Oleg"
// val y: AnyRef = x // это норм ведь стринг ссылочный
// val z: Any = y // всё что угодно ани




