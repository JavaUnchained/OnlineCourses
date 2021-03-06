public class Parralel {
    /*
    parallel and sequential streams;
    performance of parallel streams;
    some caveats.
     */

//    Я не буду писать о важности параллельной обработки данных,
//    потому что это очевидно, но я должен отметить, что довольно
//    просто написать параллельный код с Stream API. Нет потоков,
//    нет синхронизированных блоков, нет ожидания и уведомления вызова.
//
//    Существует несколько способов создания параллельных потоков:

//    1.для вызова метода parallelStream () коллекции вместо stream () :

//    List<String> languages = Arrays.asList("java", "scala", "kotlin", "clojure", "C#");
//    List<String> jvmLanguages = languages.parallelStream()
//            .filter(lang -> !lang.equals("C#"))
//            .collect(Collectors.toList());

//    преобразовать существующий поток в параллельный поток с помощью метода parallel () :
//long[] numbers = LongStream
//        .rangeClosed(0, 100_000)
//        .parallel()
//        .toArray();

    /*
    Также есть дополнительные удобные методы для работы с параллельными потоками:

    isParallel () позволяет проверить, параллелен поток или нет
    sequential () возвращает эквивалентный последовательный поток
     */

/********************************************Важное *********************************************************/
    /*
    1. Чем больше данных тем больше ускорение
    2. Бокс/Распаковка  Примитивные значения обрабатываются быстрее, чем упакованные значения.
    3. Стоимость обработки элемента. Чем дольше обрабатывается каждый элемент =>, тем эффективнее
    распараллеливание. Но не рекомендуется использовать параллельный поток для выполнения слишком длинных
    операций (например, сетевого подключения).
    4.Тип операций: без сохранения состояния или с сохранением состояния . Операции без сохранения состояния
    (примеры: фильтр , отображение ) лучше подходят для параллельной обработки, чем операции с состоянием
    (примеры: различные , отсортированные , ограниченные ).
    5. с параллельными потоками мы должны использовать операцию forEachOrdered, которая сохраняет порядок элементов.
     */



}
