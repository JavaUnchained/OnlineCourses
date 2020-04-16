object PatrialFunctions {
//  Кортежи - передопределённые типы, представляющие последовательности значений фиксировнной длины
  val tuple = (2,4)

  def divMod(x: Int , y: Int): (Int, Int) = (x/y, x%y)

  def  firstLastAndCount(line:String):(Char,Char,Int) = (line(0), line.last, line.length)

  val (first, last,count) = firstLastAndCount("Scala")

  val i:Int = firstLastAndCount("Scala")._3 // _1 , _2  - номер эелемента в кортеже
}
