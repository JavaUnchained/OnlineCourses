object PatternMatching {

//  пример сопоставления с образцом
  def numberName(x: Int) = x match {
    case 1 => "one"
    case 2 => "two"
    case 3 | 4 => "three or four" // можно несколько значений
    case x if x % 2 == 0 => s"$x and it even"
    case _ => "unknown" // значения для всех остальных
  }
// порядок очень важен особенно для _ - параметра


//  наиболее часто используемые для патерн матчинга - это case class
  case class Address(country: String, city: String)

  // имея кейс класс можно разворачивать его внуктри патерн матчина
  def adressInfo(address: Address):String = address match {
    case Address("Russia", _) => "russian"
    case Address ("Japan", _) => "japanese"
    case _ => "no info"
  }

//  def sum(1 xs:List[Int], start:Int = 0): Int = xs match {
//    case x :: rest => sum(rest, start+x)
//    case Nil       => start
//  }

  def sum2( xs: List[Int], start:Int = 0) = xs match {
    case List() => start
    case List(x) => start + x
    case List (x, y) => start + x + y
    case List(x,y,z) => start + x + y + z
    case _ => throw new Exception("too many elements")
  }

  def sum3( xs: List[Int], start: Int = 0): Int = xs match {
    case List() => start
    case List(x, rest@_*) => sum3(rest.toList, start+x) // @_* все остальные елементы справа от уже указанного
  }

  val address = "\\w+, \\w+".r // регулярка на 2 слова
  def isAdress(string: String):Boolean = string match {
    case address() => true
    case _ => false
  }

//  все эти способы можно комбингиолваьб
}
