import java.util.Optional;

public class Monads {
    public static void main(String[] args) {
        Optional<Integer> optionalInteger = Optional.of(25);
        System.out.println(optionalInteger);
        System.out.println(optionalInteger.get());
        System.out.println(optionalInteger.isPresent());
        System.out.println(optionalInteger.map(x -> x * x).get());

    }
}
