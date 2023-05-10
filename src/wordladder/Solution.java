package wordladder;

import java.util.*;

class Solution {
    int sum = 0;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return sum;
        bfs(beginWord, endWord, wordList, new LinkedList<String>(), 0);
        return sum;

        //记录走过的
        //做选择
        //进入下一层
        //撤销选择
    }

    void bfs(String beginWord, String endWord, List<String> wordList, LinkedList<String> q, int num) {
        if (beginWord.equals(endWord)) {
            sum = num;
            return;
        }

        for (int i = 0; i < wordList.size(); i++) {
            String curStr = wordList.get(i);
            if (!(!q.contains(curStr) && helper(beginWord, curStr))) continue;
            q.offerLast(curStr);
            bfs(curStr, endWord, wordList, q, num + 1);
            q.pollLast();
        }

    }

    public boolean helper(String from, String to) {
        int num = 0;
        for (int n = 0; n < to.length(); n++)
            if (from.charAt(n) != to.charAt(n))
                num += 1;
        return num == 1;
    }



    public static void main(String[] args) {

        new Solution().lengthOfLongestSubstring("abcabcbb");
        ArrayList<String> wordList = new ArrayList<>();
        int[] a = {1,2,3,4};

        reverse(a);
        System.out.println(Arrays.toString(a));

        wordList.addAll(Arrays.asList(new String[]{"hot","dot","dog","lot","log","cog"}));
        int r = new Solution().ladderLength("hit", "cog", wordList);
        System.out.println("r = " + r);
    }
   static void reverse(int[] arr){
        int left = 0,right = arr.length-1;
        while(left<right){
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> window = new HashMap<>();
        int res = 0;
        int left = 0, right = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            window.put(c, window.getOrDefault(c, 0) + 1);
            while (window.get(c) > 1) {
                char d = s.charAt(left);
                left++;
                window.put(d, window.getOrDefault(d, 0) - 1);
            }
            System.out.println("right = " + right);
            System.out.println("left = " + left);
            res = Math.max(res, right - left);
        }
        return res;
    }



}