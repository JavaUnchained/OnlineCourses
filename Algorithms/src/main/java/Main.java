import java.util.stream.LongStream;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        long finishTime = System.currentTimeMillis();
        System.out.println(finishTime - startTime + " ms");
    }

    public static long factorial(long n) {
        return LongStream.iterate(1, x -> x + 1)
                .limit(n)
                .reduce(1, (acc, elem) -> acc * elem);
    }
}
