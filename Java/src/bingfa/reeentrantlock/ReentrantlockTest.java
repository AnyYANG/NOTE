package bingfa.reeentrantlock;

import org.junit.jupiter.api.Test;
/**
 * Created by  liuyang
 * 2018/12/6    20:27
 *  并发编程
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

