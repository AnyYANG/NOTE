package MapCase;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 *     我们知道在Java中，对象的序列化可以通过实现两种接口来实现，
 *     若实现的是Serializable接口，则所有的序列化将会自动进行，
 *     若实现的是Externalizable接口，则没有任何东西可以自动序列化，
 *     需要在writeExternal方法中进行手工指定所要序列化的变量，
 *     这与是否被transient修饰无关。因此第二个例子输出的是变量content初始化的内容，而不是null。
 * Created by  liuyang
 * 2018/12/22    17:51
 * MapCase
 * All Right Reserved by liuyang.
 **/

public class MyMap<K, V> implements Map {
     //transient 瞬态 瞬态变量 和静态变量一样不能被实例化（静态变量不属于对象，属于类）
    transient MyEntry<K, V> node;


    static class MyEntry<K, V> implements Map.Entry {
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
