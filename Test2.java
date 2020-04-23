给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null){
            return null;
        }
        if(root==p||root==q){
            return root;
        }
        TreeNode left=lowestCommonAncestor(root.left,p,q);
        TreeNode right=lowestCommonAncestor(root.right,p,q);
        if(left!=null&&right!=null){
            return root;
        }
        if(left!=null) return left;
        if(right!=null) return right;
        return null;
    }
}

序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。

请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb=new StringBuilder();
        ser(root,sb);
        return new String(sb);
    }
    private void ser(TreeNode root,StringBuilder sb){
        if(root==null){
            sb.append("null ");
            return;
        }
        sb.append(root.val+" ");
        ser(root.left,sb);
        ser(root.right,sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] str=data.split(" ");
        LinkedList<String> list=new LinkedList<>();
        for(String s:str){
            list.add(s);
        }
        return des(list);
    }
    private TreeNode des(LinkedList<String> list){
        if(list.getFirst().equals("null")){
            list.removeFirst();
            return null;
        }
        TreeNode root=new TreeNode(Integer.valueOf(list.removeFirst()));
        root.left=des(list);
        root.right=des(list);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

天际线问题
class Solution {
    private static class Node{
        private int pos;
        private int height;
        public Node(int pos,int height){
            this.pos=pos;
            this.height=height;
        }
    }
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> res=new ArrayList<>();
        PriorityQueue<Node> q=new PriorityQueue<>((a,b)->{
            return a.pos==b.pos?a.height-b.height:a.pos-b.pos;
        });
        for(int[] build:buildings){
            q.offer(new Node(build[0],-build[2]));
            q.offer(new Node(build[1],build[2]));
        }
        int pre=0;
        PriorityQueue<Integer> h=new PriorityQueue<>((a,b)->(b-a));
        h.offer(0);
        while(!q.isEmpty()){
            Node node=q.poll();
            if(node.height<0){
                h.offer(-node.height);
            }else{
                h.remove(node.height);
            }
            int cur=h.peek();
            if(pre!=cur){
                res.add(Arrays.asList(node.pos,cur));
                pre=cur;
            }
        }
        return res;
    }
}

给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。
class Solution {
    public String largestNumber(int[] nums) {
        String[] str=new String[nums.length];
        for(int i=0;i<nums.length;i++){
            str[i]=String.valueOf(nums[i]);
        }
        Arrays.sort(str,(o1,o2)->{
            return (o2+o1).compareTo(o1+o2);
        });
        StringBuilder sb=new StringBuilder();
        for(String s:str){
            sb.append(s);
        }
        return sb.charAt(0)=='0'?"0":new String(sb);
    }
}

给定一个无序的数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
class Solution {
    public void wiggleSort(int[] nums) {
        int[] arr=nums.clone();
        Arrays.sort(arr);
        int n=nums.length;
        int mid=(n-1)/2;
        int i=mid;
        int j=n-1;
        int k=0;
        while(i>=0&&j>mid){
            nums[k++]=arr[i--];
            nums[k++]=arr[j--];
        }
        if(i>=0){
            nums[k++]=arr[i--];
        }
    }
}

给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
class Solution {
    public int findDuplicate(int[] nums) {
        int fast=nums[0];
        int slow=nums[0];
        do{
            fast=nums[fast];
            fast=nums[fast];
            slow=nums[slow];
        }while(fast!=slow);
        fast=nums[0];
        while(fast!=slow){
            fast=nums[fast];
            slow=nums[slow];
        }
        return fast;
    }
}

计算右侧小于当前元素的个数
class Solution {
    private int[] res;
    public List<Integer> countSmaller(int[] nums) {
        int n=nums.length;
        res=new int[n];
        int[] index=new int[n];
        for(int i=0;i<n;i++){
            index[i]=i;
        }
        mergeSort(nums,index,0,n-1);
        List<Integer> list=new ArrayList<>();
        for(int num:res){
            list.add(num);
        }
        return list;
    }
    private void mergeSort(int[] nums,int[] index,int left,int right){
        if(left>=right){
            return ;
        }
        int mid=left+(right-left)/2;
        mergeSort(nums,index,left,mid);
        mergeSort(nums,index,mid+1,right);
        merge(nums,index,left,mid,right);
    }
    private void merge(int[] nums,int[] index,int left,int mid,int right){
        int i=left;
        int j=mid+1;
        int len=right-left+1;
        int[] tmp=new int[len];
        int k=0;
        while(i<=mid&&j<=right){
            if(nums[index[i]]<=nums[index[j]]){
                res[index[i]]+=(j-mid-1);
                tmp[k++]=index[i++];
            }else{
                tmp[k++]=index[j++];
            }
        }
        while(i<=mid){
            res[index[i]]+=right-mid;
            tmp[k++]=index[i++];  
        }
        while(j<=right){
            tmp[k++]=index[j++];
        }
        System.arraycopy(tmp,0,index,left,len);
    }
}

二叉树中的最大路径和
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    private int max=Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxPath(root);
        return max;
    }
    private int maxPath(TreeNode root){
        if(root==null) return 0;
        int left=Math.max(maxPath(root.left),0);
        int right=Math.max(maxPath(root.right),0);
        max=Math.max(max,left+right+root.val);
        return Math.max(left,right)+root.val;
    }
}

给定一个整数矩阵，找出最长递增路径的长度。

对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。
class Solution {
    private int res;
    private int row;
    private int col;
    private int[] dx={0,0,1,-1};
    private int[] dy={1,-1,0,0};
    public int longestIncreasingPath(int[][] matrix) {
        row=matrix.length;
        if(row==0) return 0;
        col=matrix[0].length;
        int[][] visit=new int[row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                visit[i][j]=back(matrix,i,j,-1,visit);
                res=Math.max(res,visit[i][j]);
            }
        }
        return res;
    }
    private int back(int[][] matrix,int i,int j,int num,int[][] visit){
        if(i<0||j<0||i>=row||j>=col||matrix[i][j]<=num){
            return 0;
        }
        if(visit[i][j]>0){
            return visit[i][j];
        }
        int r=0;
        r=Math.max(r,back(matrix,i+1,j,matrix[i][j],visit));
        r=Math.max(r,back(matrix,i-1,j,matrix[i][j],visit));
        r=Math.max(r,back(matrix,i,j+1,matrix[i][j],visit));
        r=Math.max(r,back(matrix,i,j-1,matrix[i][j],visit));
        visit[i][j]=1+r;
        return 1+r;
    }
}