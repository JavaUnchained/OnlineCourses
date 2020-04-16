object Optional {
//  Optional[A]
//  Имеет 2 подтипа
//  Some[A]
//  None - не содержит ничего
//  решает Null

//  def divide(x: Int, y: Int): Optional[Int] =
//    if(y == 0) None else Some(x/y)


//  def showDivide(x:Int, y:Int):String =
//    divide(x,y) match {
//      case Some(d) => s"$x = $d * $y"
//      case None => "null division"
//    }

//  divide(7,4).map(x => x + 6)
//  divide(17,3).flatMap(x => divide(x,3)) // тоже что и мап только вернет опшионал
//  .filter(x => x >2 )

// Either[A,B] -  одно из двухБ сключающее или , либо
//  Left[A,B]
//  Right[A,B]
}
