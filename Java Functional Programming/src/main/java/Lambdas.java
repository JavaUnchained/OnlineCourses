import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;

public class Lambdas {
    public static void main(String[] args) {
        // 1. max integer
        BiFunction<Integer,Integer,Integer> maxInt =  (x,y)-> x > y ? x:y;
        System.out.println(maxInt.apply(3,5));

        //2. Write a lambda expression that accepts a long value and returns a next even number.
        Function<Integer, Integer> nextEven = x -> x % 2 == 0 ? x + 2 : x +1;

        System.out.println(nextEven.apply(234));
        System.out.println(nextEven.apply(235));


       // 3. Перемножение всех членов списка
        BiFunction<Integer, Integer, Long> multRange = (from, to) -> {
            long r = 1;
            while (from <= to){
                r *= from;
                from++;
            }
            return r;
        };

        BiFunction<Integer, Integer, Long> multRange1 = (from, to) -> from <= to ? from * multRange.apply(from+1, to) : from;

        System.out.println(multRange.apply(1,4));
        System.out.println(multRange1.apply(1,4));
        System.out.println(multRange.apply(5,15));
        System.out.println(multRange1.apply(5,15));

        //Write a lambda expression that accepts a list of strings and returns new list of distinct strings (without repeating)
        System.out.println(Arrays.toString(Arrays.asList("java scala java clojure clojure").stream().distinct().toArray()));

//        Using closure write a lambda expression that calculates --- where a, b, c are context final variables.
        final int a = 4;
        final int b = 6;
        final int c = 12;

        Function<Integer,Integer> calc = x -> a * (x*x) +  b * x + c;

        System.out.println( calc.apply(4));

        /*
        The lambda expression has to return true if all passed values are different otherwise false.
        The name of the instance is allValuesAreDifferentPredicate. It should be a static field.
         */
        final TernaryIntPredicate allValuesAreDifferentPredicate = (i,y,z) -> i != y && y != z && z != i;

        IntUnaryOperator mult2 = num -> num * 2;
        IntUnaryOperator add3 = num -> num + 3;

        IntUnaryOperator combinedOperator = add3.compose(mult2.andThen(add3)).andThen(mult2);
        int result = combinedOperator.applyAsInt(5);
        System.out.println(result);

        Consumer<Integer> printer = System.out::println;
        Consumer<Integer> devNull = (val) -> { int v = val * 2; };

        Consumer<Integer> combinedConsumer = devNull.andThen(devNull.andThen(printer));
        combinedConsumer.accept(100);
    }
}


