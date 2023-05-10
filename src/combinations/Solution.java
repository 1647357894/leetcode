package combinations;

import java.util.*;
import java.util.concurrent.atomic.LongAdder;

class Solution {

    //存储所有子集
    List<List<Integer>>  res= new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        //记录走过的路径
        LinkedList<Integer> track = new LinkedList<>();
        backTrack(nums,0,track);
        return res;
    }
    void backTrack(int[] nums,int start,LinkedList<Integer> track){
        //前序遍历的位置
        res.add(new LinkedList(track));

        //从start开始，防止产生重复的子集
        for(int i =start;i<nums.length;i++){
            //做选择
            track.add(nums[i]);
            //递归回溯
            backTrack(nums,i+1,track);
            //取消选择
            track.removeLast();
        }
    }
    public List<String> letterCombinations(String digits) {
        //if(digits== null || digits.length()==0)return res;

        List<String> strs = Arrays.asList(digits.split(""));
        System.out.println(strs);
        return null;


    }

    public int mySqrt(int x) {
        long left = 0, right = x / 2 + 1;
        while (left < right) {
            //long mid = left + (right - left + 1) / 2;
            //取右中位数，取左位数会死循环
            long mid = (left + right + 1) >>> 1;
            if (mid * mid > x)
                right = mid - 1;
            else
                left = mid;
        }
        return (int) left;
    }
    public static void main(String[] args) {
        //new Solution(). letterCombinations("acbn");
        new Solution(). mySqrt(8);


    }

    //List<List<Integer>>  res= new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        //记录走过的路径
        LinkedList<Integer> track = new LinkedList<>();
        backTrack(n,k,1,track);
        return res;
    }

    // n树的宽度 k树的高度
    private void backTrack(int n, int k, int start, LinkedList<Integer> track) {
        //到达叶子节点，加入到结果集中
        if(track.size()==k){
            res.add(new LinkedList<>(track));
            return;
        }
        for(int i = start;i < n;i++){
            //选择
            track.add(i);
            //递归回溯
            backTrack(n,k,start+1,track);
            //取消选择
            track.removeLast();
        }
    }


}