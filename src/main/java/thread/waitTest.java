package thread;

/**
 * Created by liujs on 17-7-13.
 */
public class waitTest implements Runnable {



    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " run ");
        // 死循环，不断运行。
        while(true){;}  //  这个线程与主线程无关，无 synchronized
    }

    public static void main(String args[]){
        waitTest t1 = new waitTest();

        try {
            t1.wait(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
