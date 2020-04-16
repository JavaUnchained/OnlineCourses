package MultiThreading;

public class JoinThread {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();

        try {
            myThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(myThread.getI());
    }

}

class MyThread extends Thread{
    private int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 1_000_000 ; j++) {
            i = j;
        }
    }

    public int getI() {
        return i;
    }
}