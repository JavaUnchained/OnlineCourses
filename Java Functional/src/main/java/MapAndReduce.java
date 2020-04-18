import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class MapAndReduce {
    public static void main(String[] args) {
        int sum = Arrays.asList(2, 1, 3, 4, 5, 6, 7).stream().reduce(0, (acc, elem) -> acc + elem);

        // the collection of java books
        final List<Book> javaBooks = Stream.of(
                new Book("Java EE 7 Essentials", 2013, Arrays.asList("Arun Gupta")),
                new Book("Algorithms", 2011, Arrays.asList("Robert Sedgewick", "Kevin Wayne")),
                new Book("Clean code", 2014, Arrays.asList("Robert Martin"))
        ).collect(Collectors.toList());

        // list of authors
        final List<String> authors = javaBooks.stream()
                .flatMap(book -> book.getAuthor().stream())
                .distinct()
                .collect(Collectors.toList());

        boolean result = LongStream
                .rangeClosed(1, 100_000)
                .anyMatch(val -> val == 100_000);

        System.out.println(result);

        boolean result1 = !IntStream
                .generate(() -> 100)
                .limit(101)
                .allMatch(val -> val >= 100);

        System.out.println(result1);

        boolean result2 = IntStream
                .iterate(0, n -> n + 1)
                .limit(100)
                .filter(n -> n % 2 != 0)
                .noneMatch(n -> n % 2 == 0);

        System.out.println(result2);
    }
    /*
    В этом примере map отрицает каждый предикат в потоке, а затем сводит все предикаты в один.
     Начальное значение (начальное) снижения является предикат ,
     который всегда верно, потому что это нейтральное значение для соединения.
     */
    public static IntPredicate negateEachAndConjunctAll(Collection<IntPredicate> predicates) {
        return predicates.stream()
                .map(IntPredicate::negate)
                .reduce(n -> true, IntPredicate::and);
    }

    /*
    Напишите метод, используя Stream API, чтобы проверить, является ли введенное число простым или нет .
    Давайте согласимся, что входное значение всегда больше 1 (то есть 2, 3, 4, 5, ....).
    Используйте предоставленный шаблон для вашего метода.

    Простое число - это значение больше 1, которое не имеет положительных делителей,
    кроме 1 и самого себя. Смотрите https://en.wikipedia.org/wiki/Prime_number1
     */
//    public static boolean isPrime(final long number) {
//        // write your code here
////        return Stream.of(number).allMatch(n -> number % n == 0 && n != number;
//    }


    /*
    Create a stream that will detect bad words in a text according to a bad words list.
     All words in the text are divided by whitespaces (always only one whitespace between two words).

    After calling collect(Collectors.toList()) the stream must return the list of bad words which
     were found in the text. The result should be dictionary ordered (in lexicographical order, i.e. sorted)
     and bad words shouldn't repeat.

    Important. You need return a prepared stream from the template method, not a list of bad words.
    Pay attention to the method template. Do not change it!
     */
    public static Stream<String> createBadWordsDetectingStream(String text, List<String> badWords) {
        return badWords.stream().filter(text::contains).distinct().sorted();
    }


    public static long factorial(long n) {
        return LongStream.iterate(1, x -> x + 1)
                .limit(n)
                .reduce(1, (acc, elem) -> acc * elem);
    }


    public static long sumOfOddNumbersInRange(long start, long end) {
        return LongStream.rangeClosed(start,end).filter(x -> x % 2 != 0).sum();
    }
}
