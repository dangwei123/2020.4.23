硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上1000000007)
class Solution {
    public int waysToChange(int n) {
        int[] dp=new int[n+1];
        int[] arr={1,5,10,25};
        dp[0]=1;
        for(int i=0;i<4;i++){
            for(int j=arr[i];j<=n;j++){
                dp[j]=(dp[j]+dp[j-arr[i]])%1000000007;
            }
        }
        return dp[n];
    }
}

给定不同面额的硬币和一个总金额。写出函数来计算可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。 

class Solution {
    public int change(int amount, int[] coins) {
        int[] dp=new int[amount+1];
        dp[0]=1;
        for(int i=0;i<coins.length;i++){
            for(int j=coins[i];j<=amount;j++){
                dp[j]+=dp[j-coins[i]];
            }
        }
        return dp[amount];
    }
}

给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。

请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if(head==null) return null;
        ListNode beforeHead=head;
        ListNode before=head;
        ListNode afterHead=head.next;
        ListNode after=head.next;
        while(before.next!=null&&after.next!=null){
            before.next=after.next;
            before=before.next;
            if(before!=null)
            after.next=before.next;
            after=after.next;
        }
        if(before!=null)
        before.next=afterHead;
        return beforeHead;
    }
}

给定一个Excel表格中的列名称，返回其相应的列序号。
class Solution {
    public int titleToNumber(String s) {
        int res=0;
        for(int i=s.length()-1;i>=0;i--){
            char c=s.charAt(i);
            res+=(c-'A'+1)*Math.pow(26,s.length()-i-1);
        }
        return res;
    }
}

给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。

为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1
class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int n=A.length;
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int sum=A[i]+B[j];
                map.put(sum,map.getOrDefault(sum,0)+1);
            }
        }
        int res=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int sum=C[i]+D[j];
                if(map.containsKey(-sum)){
                    res+=map.get(-sum);
                }
            }
        }
        return res;
    }
}

设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构。

insert(val)：当元素 val 不存在时，向集合中插入该项。
remove(val)：元素 val 存在时，从集合中移除该项。
getRandom：随机返回现有集合中的一项。每个元素应该有相同的概率被返回。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/insert-delete-getrandom-o1
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class RandomizedSet {
    Map<Integer,Integer> map=new HashMap<>();
    List<Integer> list=new ArrayList<>();
    private int size;
    Random random=new Random();
    /** Initialize your data structure here. */
    public RandomizedSet() {

    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(map.containsKey(val)){
            return false;
        }
        list.add(size,val);
        map.put(val,size);
        size++;
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)){
            return false;
        }
        int oldIndex=map.get(val);
        int num=list.get(size-1);
        list.set(oldIndex,num);
        map.put(num,oldIndex);
        map.remove(val);
        size--;
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(random.nextInt(size));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
 
 