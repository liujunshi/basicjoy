package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liujs on 17-7-11 19:28:00
 * 一个线程安全的Queue,当队列为空的时候进行阻塞。
 */
public class MySyncQueueCondition {

    List<Integer> list;


    ReentrantLock lock = new ReentrantLock();

    Condition condition = lock.newCondition();


    private boolean isEmpty;

    //单例模式
    private static MySyncQueueCondition mySyncQueue;

    public MySyncQueueCondition(List<Integer> list) {
        this.list = list;

    }

    //饿汉式doucheck 线程安全
    public static MySyncQueueCondition getInstance() {

        if (mySyncQueue == null) {
            synchronized (MySyncQueueCondition.class) {
                if (mySyncQueue == null) {
                    mySyncQueue = new MySyncQueueCondition(new ArrayList<Integer>());
                }
            }
        }
        return mySyncQueue;
    }

    //从左侧推,返回队列长度
    public int lpush(Integer v) {

        int size =0 ;
        try {
            lock.lock();
            list.add(v);
            System.out.println("队列长度" + list.size() + " toString: " + list.toString());
            size = list.size();
            if(size>0){
                condition.signal();
            }

//            isEmpty = size > 0 ? false : true;
        } finally {
            lock.unlock();
        }

        return size;
    }

    //从右侧拉出一个
    public Integer rpull() {
        //如果是空自旋锁阻塞,两层为了日志

        Integer value = null;
        lock.lock();
        try {
            if (list.size() == 0) {
                System.out.println(Thread.currentThread().getName() + "  condition.await() 队列是空 开始阻塞");
                condition.await();
                System.out.println(Thread.currentThread().getName() + "  醒了！！！ 开始阻塞");
            }
            //value = list.size()>0?list.remove(0):null;
            System.out.println("准备取出一个： " + value + "剩余队列长度 " + list.size() + " toString： " + list.toString());
            value = list.remove(0);
            System.out.println("返回： " + value + "剩余队列长度 " + list.size() + " toString： " + list.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return value;
    }

}
