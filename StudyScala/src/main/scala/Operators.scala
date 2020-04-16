object Operators extends App {

//  цикл форм
  for(i <- 1 to 10) {
    println(i)
  }

  for(i <- 1 to 10; j <- i to 10){
    println(s"$i $j")
  }

//  другая форма записи
  for{
    i <- 1 to 10
    j <- i to 10
  }println(s"$i $j")

//   дополнительные условия
  for{
    i <- 1 to 10
    j <- i to 10 if i > j
  }println(s"$i $j")

  
}
