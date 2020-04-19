import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class ComplitableFuture {
    public CompletableFuture<String> ask(){
        final CompletableFuture<String> future = new CompletableFuture<>();
        //...

        return future;
        /*
        Если теперь клиентский код вызывает, ask().get()
        он будет заблокирован навсегда. Если он регистрирует
        некоторые обратные вызовы завершения, они никогда не сработают.
         */
    }

    public static void main(String[] args) {
        ComplitableFuture complitableFuture = new ComplitableFuture();

        System.out.println(complitableFuture.ask().complete("42"));


        /*
        CompletableFuture.complete()может быть вызван только один раз, последующие вызовы игнорируются.
         Но есть задняя дверь, CompletableFuture.obtrudeValue(...)которая заменяет предыдущую ценность Future
         новой. Используйте с осторожностью.
         */
    }

}
