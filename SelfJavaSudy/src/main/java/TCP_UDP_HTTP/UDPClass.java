package TCP_UDP_HTTP;

import java.io.IOException;
import java.net.*;

public class UDPClass implements Runnable{
    private DatagramSocket socket;
    private boolean running;
    private byte[]buf = new byte[256];

    public UDPClass() throws SocketException {
        socket = new DatagramSocket(4454);
    }


    @Override
    public void run() {
        running = true;

        while (running){
            DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);
            try {
                socket.receive(datagramPacket);

                InetAddress address = datagramPacket.getAddress();
                int port = datagramPacket.getPort();
                datagramPacket = new DatagramPacket(buf, buf.length, address, port);

                String received
                        = new String(datagramPacket.getData(), 0, datagramPacket.getLength());

                if (received.equals("end")) {
                    running = false;
                    continue;
                }
                socket.send(datagramPacket);

            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                socket.close();
            }
        }
    }
}

class EchoClient {
    private DatagramSocket socket;
    private InetAddress address;

    private byte[]buf;

    public EchoClient() throws SocketException, UnknownHostException {
        socket = new DatagramSocket();
        address = InetAddress.getByName("localhost");
    }

    public String sendEcho(String msg) throws IOException {
        buf = msg.getBytes();
        DatagramPacket packet
                = new DatagramPacket(buf, buf.length, address, 4445);
        socket.send(packet);
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String received = new String(
                packet.getData(), 0, packet.getLength());
        return received;
    }

    public void close() {
        socket.close();
    }
}
