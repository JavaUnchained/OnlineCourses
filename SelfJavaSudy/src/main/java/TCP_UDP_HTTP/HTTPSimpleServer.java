package TCP_UDP_HTTP;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HTTPSimpleServer {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Sever started");

            while (true){
//                для подобных вещей лучше всего использовать неблокирующую очередь nio
                // ожидаем подключения
                Socket socket = serverSocket.accept();
                System.out.println("Client connection");

                // для подключившегося клиента открываем потоки
                // чтения и записи
                try(BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                    PrintWriter output = new PrintWriter(socket.getOutputStream())
                ){
                    // ждем первой строки запроса
                    while (!input.ready()) ;

                    // считываем и печатаем все что было отправлено клиентом
                    while (input.ready()) {
                        System.out.println(input.readLine());
                    }
                    // отправляем ответ
                    output.println("HTTP/1.1 200 OK");
                    output.println("Content-Type: text/html; charset=utf-8");
                    output.println();
                    output.println("<p>Привет всем!</p>");
                    output.flush();
                    // по окончанию выполнения блока try-with-resources потоки,
                    // а вместе с ними и соединение будут закрыты
                    System.out.println("Client disconnected!");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
