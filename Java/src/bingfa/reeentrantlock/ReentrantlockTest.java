package bingfa.reeentrantlock;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.ReentrantLock;
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
    public void demo1(){
        ReentrantLock reentrantLock=new ReentrantLock();
        reentrantLock.lock();
        reentrantLock.unlock();
    }
}

