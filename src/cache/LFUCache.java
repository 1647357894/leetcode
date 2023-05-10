package cache;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

class LFUCache {
    //记录容量大小
    int cap = 0;
    //记录最小访问频率
    int minFreq = 0;
    //记录key val
    Map<Integer, Integer> keyToVal = new HashMap<Integer, Integer>();
    // 记录key访问频率
    Map<Integer, Integer> keyToFreq = new HashMap<Integer, Integer>();
    // 记录访问频率对应的key集合
    Map<Integer, LinkedHashSet<Integer>> freqToKeys = new HashMap<>();

    public LFUCache(int capacity) {
        this.cap = capacity;
    }

    public void put(int key, int val) {
        //判断容量大小
        if (this.cap < 1) return;
        //判断key是否存在  存在更新key对应的val，key访问频率加一
        if (keyToVal.containsKey(key)) {
            keyToVal.put(key, val);
            incr(key);
            return;
        }

        //判断容量已满 移除最少使用的key
        if (this.keyToVal.size() >= this.cap) {
            removeKey();
        }
        this.minFreq = 1;
        //更新 kv
        keyToVal.put(key, val);
        //更新 kf
        keyToFreq.put(key, minFreq);
        //更新 fk
        freqToKeys.putIfAbsent(minFreq, new LinkedHashSet<>());
        freqToKeys.get(minFreq).add(key);
    }

    public int get(int key) {
        if (!keyToVal.containsKey(key)) {
            return -1;
        }
        //增加访问频率
        incr(key);
        return keyToVal.get(key);
    }

    public void incr(int key) {
        //获取访问频率
        Integer freq = keyToFreq.get(key);
        // 加一
        Integer newFreq = freq + 1;

        //update KF
        keyToFreq.put(key, newFreq);
        //update FK
        LinkedHashSet<Integer> keyList = freqToKeys.get(freq);
        keyList.remove(key);
        freqToKeys.putIfAbsent(newFreq, new LinkedHashSet<>());
        freqToKeys.get(newFreq).add(key);

        if (keyList.isEmpty()) {
            freqToKeys.remove(freq);
            //访问频率与最小访问频率想等，最小访问频率加一
            if (freq == minFreq)
                minFreq++;
        }
    }

    public void removeKey() {
        //获取最小访问频率key集合
        LinkedHashSet<Integer> keyList = freqToKeys.get(minFreq);
        //获取最早加入的key
        Integer deleteKey = keyList.iterator().next();
        //udpate fk
        keyList.remove(deleteKey);
        if (keyList.isEmpty()) {
            freqToKeys.remove(minFreq);
        }
        //udpate kv
        keyToVal.remove(deleteKey);
        //udpate kf
        keyToFreq.remove(deleteKey);
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);
        cache.put(3, 3);
        cache.get(2);
        cache.get(3);

    }

}