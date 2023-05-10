package cache;

import java.util.LinkedHashMap;

/**
 * @author wangjie
 * @date 2021/3/21 上午10:59
 */
public class LRUCache {
    int cap = 0;

    LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<Integer, Integer>();

    public LRUCache(int cap) {
        this.cap = cap;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        return makeRecently(key);
    }

    private Integer makeRecently(int key) {
        Integer val = cache.remove(key);
        cache.put(key, val);
        return val;
    }

    public void put(int key, int val) {
        if (cache.containsKey(key)) {
            cache.put(key, val);
            makeRecently(key);
            return;
        }
        //缓存满了 移除最近未使用的
        if (cache.size() >= cap) {
            Integer oldKey = cache.keySet().iterator().next();
            cache.remove(oldKey);
        }

        cache.put(key, val);
    }

}
