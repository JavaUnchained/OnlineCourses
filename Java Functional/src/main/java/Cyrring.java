import java.util.Arrays;
import java.util.List;
import java.util.function.*;

public class Cyrring {
    public static void main(String[] args) {
        IntBinaryOperator notCurriedFun = (x, y) -> x + y; // not a curried function

        IntFunction<IntUnaryOperator> curriedFun = x -> y -> x + y; // a curried function

        //Мы можем определить карри функцию с тремя аргументами и затем применить аргументы один за другим.

        // curried function
        IntFunction<IntFunction<IntFunction<Integer>>> fff = x -> y -> z -> x * y + z;

        // fff returns a curried function y -> z -> 2 * y + z
        IntFunction<IntFunction<Integer>> ff = fff.apply(2);

        // ff returns a curried function z -> 2 * 3 + z
        IntFunction<Integer> f = ff.apply(3);

        // f returns 7
        int result = f.apply(1);

        // the another result is equal to 153
        int anotherResult = fff.apply(10).apply(15).apply(3);

        Function<IntUnaryOperator, IntBinaryOperator> sumF = (z) -> (a, b) -> z.applyAsInt(a) + z.applyAsInt(b);

        // build a new sumOfSquares operator in terms of sumF
        IntBinaryOperator sumOfSquares = sumF.apply(x -> x * x);

        // the sum is equal to 125 again
        long sum = sumOfSquares.applyAsInt(5, 10);



        Function<String, Consumer<String>> say = what -> whom -> System.out.println(what + ", " + whom);

        List<String> friends = Arrays.asList("John", "Neal", "Natasha");
        Consumer<String> sayHi = say.apply("Hi");
        friends.forEach(sayHi);


    }

    private static void someCyrring(){
//        Function<Integer, Function<Integer,Integer>> xyz = x -> y -> z -> x + y * y + z * z * z;
    }
}
