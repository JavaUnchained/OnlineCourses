import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
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
}
