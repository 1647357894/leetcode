package binarytree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wangjie
 * @date 2021/3/13 下午1:45
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {

    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> q = new LinkedList<>();
        combine(n,k,1,q,result);
        return result;
    }
    public void combine(int n,int k,int start,Deque<Integer> q,List<List<Integer>> result){
        if(q.size()==k){
            result.add(new ArrayList<>(q));
            return;
        }
        for(int i = start;i<n;i++){
            q.push(i);
            combine(n,k,i,q,result);
            q.pop();
        }
    }

    //单调栈，每次新元素入栈后，栈内的元素都保持单调(单调递增或单调递减)
    //只处理一种典型的问题，Next Greater Element 下一个更大的元素

    public static void main(String[] args) {

    }
    static int a = 1;

}
