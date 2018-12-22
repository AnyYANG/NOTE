package MapCase;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * Created by  liuyang
 * 2018/12/22    17:42
 * MapCase
 * All Right Reserved by liuyang.
 **/

public class HashMapCase {
    @Test
    public void testone() {
        HashMap<String,String> hashmap = new HashMap<>();
        hashmap.entrySet();
    }
    @Test
    public void testmyone() {
        MyMap<String,String> hashmap = new MyMap<>();
        hashmap.entrySet();
    }
}
