package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liujs on 17-7-11 19:28:00
 * 一个线程安全的Queue,当队列为空的时候进行阻塞。
 */
public class MySyncQueueSync {

    List<Integer> list;


    ReentrantLock lock;



    volatile boolean isEmpty;

    //单例模式
    private static MySyncQueueSync mySyncQueue;

    public MySyncQueueSync(List<Integer> list) {
        this.list = list;
        lock = new ReentrantLock();
    }

    //饿汉式doucheck 线程安全
    public static MySyncQueueSync getInstance() {

        if (mySyncQueue == null) {
            synchronized (MySyncQueueSync.class) {
                if (mySyncQueue == null) {
                    mySyncQueue = new MySyncQueueSync(new ArrayList<Integer>());
                }
            }
        }
        return mySyncQueue;
    }

    //从左侧推,返回队列长度
    public int lpush(Integer v) {
        final ReentrantLock lock = this.lock;
        int size;
        try {
            lock.lock();
            list.add(v);
            System.out.println("队列长度" + list.size() + " toString: " + list.toString());
            size = list.size();
            isEmpty = size > 0 ? false : true;
        } finally {
            lock.unlock();
        }

        return size;
    }

    //从右侧拉出一个
    public Integer rpull() {
        //如果是空自旋锁阻塞,两层为了日志

        Integer value = null;
        try {
            lock.lockInterruptibly();
            if (isEmpty) {
                System.out.println(Thread.currentThread().getName() + " 队列是空 开始自旋阻塞");
            }
            while (isEmpty) {
                if (!isEmpty) {
                    System.out.println("不是空继续获取");
                    break;
                }
            }

            value = list.remove(0);
            isEmpty = list.isEmpty();
            System.out.println("返回： " + value + "剩余队列长度 " + list.size() + " toString： " + list.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return value;
    }

}
