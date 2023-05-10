package nextGreaterElement;

import java.util.*;

/**
 * @author wangjie
 * @date 2021/3/23 下午10:48
 */
public class Solution {
    List<Integer> newGreaterElement(List<Integer> nums){
        List<Integer> ans = new ArrayList<>(nums.size()+1);
        Deque<Integer> s = new LinkedList<>();
        //
        for (int i = nums.size()-1; i >= 0; i--) {
            while(!s.isEmpty() && s.peek() <= nums.get(i))
                s.pop();
            ans.add(i,s.isEmpty()?-1:s.peek());
            s.push(nums.get(i));
        }
        return  ans;
    }

    public static void main(String[] args) {
        List<Integer> r = new Solution().newGreaterElement(Arrays.asList(2, 1, 2, 4, 3));
        System.out.println("r = " + r);
    }
}
