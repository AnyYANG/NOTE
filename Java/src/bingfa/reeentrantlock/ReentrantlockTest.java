package bingfa.reeentrantlock;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by  liuyang
 * 2018/12/6    20:27
 * bingfa
 * All Right Reserved by liuyang.
 **/

/**
 * reentrantlock 被await后自动阻塞线程，并放弃锁。等待condition的signal
 */
public class ReentrantlockTest {
    @Test
    public void ReenLockawait() {
      myrunable run =new myrunable();
      run.run();
        try {
         //   RLock.lock.lock();
            RLock.condition.signal();
        } finally {
            RLock.lock.unlock();
        }
    }

    class myrunable implements  Runnable{

        @Override
        public void run() {
            try {
                RLock.lock.lock();
                System.out.println("开始wait");
                RLock.condition.await();  //放弃的锁，等待其他线程酱condition条件的唤醒
                for (int i = 0; i < 5; i++) {
                    //唤醒后执行的循环
                    System.out.println("ThreadName=" + Thread.currentThread().getName()
                            + (" " + (i + 1)));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                RLock.lock.unlock();
            }
        }
    }
}

