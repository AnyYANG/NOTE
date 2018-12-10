package bingfa.reeentrantlock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by  liuyang
 * 2018/12/6    20:27
 * bingfa
 * All Right Reserved by liuyang.
 **/

public class ReentrantlockTest2 {

    @Test
    public void signal() {
        try {
            RLock.lock.lock();
            RLock.condition.signal();
        } finally {
            RLock.lock.unlock();
        }
    }
}
