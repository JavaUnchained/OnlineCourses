import scala.collection.mutable._ // изменяемые
import scala.collection.immutable._// неизменяемые


// общий подтип Seq[], Set, Map, Iterator
object Collection {
//  Array[A] -- эффективный но низкоуровневый массив фиксированного размера есть спец версии для примитивов
  val arr = Array(1,2,3,4)
  arr(2) // 3
  arr(2) = 6
  arr(2) // 6

//  интерполяция гораздо эффективней конкатенации
  val  language = "Scala"
  val platform = "Stepik"

  val concat = language + " " + platform
  val interp = s"$language $platform"

//  неизменяемые - состояние может меняться со временем, эффективны для большого количества операций
//  их копирование неэффективно
//  Buffer[A] - самодополняемый с конца
//  Set[A] - коллекция уникальных элеменнтов
//  Map[K,V] - ассоциативный массив ключа-значение
//  Builder[E, Coll] - промежзуточный накопитель нужный для создания коллекций

  val string = Buffer[String]()

  string += "scala"
  string += "+"
  string += "stepik"
  string += "="
  string += "love"

  string.mkString("")

//  неизменяемые наиболее удобные , состояние не измененно
//  эффективное создание копий при изменении
//  Hashable - могут хранится в сет или выступать ключами в мап
//  коварианты

//  List[A] - связанный конечный список, легко добавить элемент в начало
//  Stream[A] - Ленивый связный список, возможно бесконечный,, легко добавить элемент в начало
//  Vector[A] - индексированный список, легко получать элоементы по индексу, добавить элемент в начало или конец
//  Set[A] //  Map - но не изменяемые

//  :: подходит для списков, +: подходит для любых последовательностей

}
