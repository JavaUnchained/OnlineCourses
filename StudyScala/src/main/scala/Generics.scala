object Generics {
   def ifThenElse [A] (cond: Boolean, t: => A, e: => A): A = if (cond) t else e
  ifThenElse[String](1 > 2, "one", "two")
  ifThenElse[Int](1 > 2, 1, 2)
//  иногда можно опустить тип


}
