二叉搜索树
public class BinarySearchTree {
    //定义节点类
    private static class Node{
        private int val;
        private Node left;
        private Node right;
        public Node(){

        }
        public Node(int val){
            this.val=val;
        }
    }

    private Node root;
    public BinarySearchTree(){
        root=new Node();
    }

    //查找是否有某个元素，找到返回true，找不到返回false
    public boolean contains(int key){
        Node cur=root;
        while(cur!=null){
            if(key==cur.val){
                return true;
            }else if(key<cur.val){
                cur=cur.left;
            }else{
                cur=cur.right;
            }
        }
        return false;
    }

    //插入某元素，如果已经包含该元素则返回false，插入成功就返回true
    public boolean insert(int key){
        if(root==null){
            Node node=new Node(key);
            root=node;
            return true;
        }
        Node cur=root;
        Node parent=null;
        while(cur!=null){
            //含有该元素返回false
            if(key==cur.val){
                return false;
            }else if(key<cur.val){
                parent=cur;
                cur=cur.left;
            }else{
                parent=cur;
                cur=cur.right;
            }
        }
        Node node=new Node(key);
        if(key<parent.val){
            parent.left=node;
        }else{
            parent.right=node;
        }
        return true;
    }

    //删除节点
    /*
     parent 为要删除的节点的双亲，cur为要删除的节点
     分4种情况：
     1.cur为叶子节点，既没有左孩子也没有右孩子
        i)cur为根节点
        ii)cur为parent的左孩子
        iii)cur为parent的右孩子
     2.cur只有左孩子没有右孩子
        i)cur为根节点
        ii)cur为parent的左孩子
        iii)cur为parent的右孩子
     3.cur只有右孩子没有左孩子
        i)cur为根节点
        ii)cur为parent的左孩子
        iii)cur为parent的右孩子
     4.cur左右孩子都有
        不能直接删除，需要找一个替代元素，但一定要满足搜索树的性质，
        可以找cur右子树中最小的（最左侧）的元素，
        也可以找cur左子树中最大的（最右侧）的元素。
     */

    private boolean remove(int key){
        Node cur=root;
        Node parent=null;
        while(cur!=null){
            if(key==cur.val){
                removeNode(parent,cur);
                return true;
            }else if(key<cur.val){
                parent=cur;
                cur=cur.left;
            }else{
                parent=cur;
                cur=cur.right;
            }
        }
        return false;
    }

    private void removeNode(Node parent, Node cur) {
        if(cur.left==null){
            //左孩子为空和叶子节点
            if(cur==root){
                root=cur.right;
            }else if(cur==parent.left){
                parent.left=cur.right;
            }else if(cur==parent.right){
                parent.right=cur.right;
            }
        }else if(cur.right==null){
            //右孩子为空
            if(cur==root){
                root=cur.left;
            }else if(cur==parent.left){
                parent.left=cur.left;
            }else if(cur==parent.right){
                parent.right=cur.left;
            }
        }else{
            //左右孩子都存在，需要找代替节点
            Node goatParent=cur;
            Node goat=cur.right;
            while(goat.left!=null){
                goatParent=cur;
                goat=goat.left;
            }
            cur.val=goat.val;
            //找的是cur右子树最左侧的节点，则该节点肯定没有左孩子
            if(goat==goatParent.left){
                goatParent.left=goat.right;
            }else if(goat==goatParent.right){
                goatParent.right=goat.right;
            }
        }
    }
}
