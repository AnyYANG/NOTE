package MapCase;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * Created by  liuyang
 * 2018/12/22    17:51
 * MapCase
 * All Right Reserved by liuyang.
 **/

public class MyMap<K, V> implements Map {
     //transient 瞬态 瞬态变量 和静态变量一样不能被实例化（静态变量不属于对象，属于类）
    transient myentry<K, V> node;


    static class myentry<K, V> implements Map.Entry {
        K k;
        V v;


        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }

        @Override
        public Object setValue(Object value) {
            return null;
        }


    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Object get(Object key) {
        return null;
    }

    @Override
    public Object put(Object key, Object value) {
        return null;
    }

    @Override
    public Object remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public Collection values() {
        return null;
    }

    @Override
    public Set<Entry> entrySet() {
        return null;
    }
}
