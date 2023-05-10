package minWindow;

import java.util.HashMap;

class Solution {
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> windows = new HashMap<>();
        for (char c : t.toCharArray()){
            need.put(c, need.getOrDefault(c,0)+1);
        }

        int left = 0, right = 0;
        int valid = 0;
        //记录最小覆盖子串的起始索引及长度
        int start = 0, len = Integer.MAX_VALUE;
        while (right < s.length()) {
            // c是将移入窗口的字符
            char c = s.charAt(right);
            //右移窗口
            right++;
            //进行窗口内数据的一系列更新
            if (need.containsKey(c)) {
                windows.put(c, windows.getOrDefault(c,0) + 1);
                if (windows.get(c).equals(need.get(c)))
                    valid++;
            }

            //判断左侧窗口是否要收缩
            while (valid == need.size()) {
                // 在这里更新最小覆盖子串
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                // d是将要移出窗口的字符
                char d = s.charAt(left);
                //左移窗口
                left++;
                //进行窗口内数据的一系列更新
                if (need.containsKey(d)) {
                    if (windows.get(d).equals(need.get(d)))
                        valid--;
                    windows.put(d, windows.getOrDefault(d,0) - 1);
                }

            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }

   
}