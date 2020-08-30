package com.wuzhiaite.javaweb.base.bitree;

import java.util.concurrent.ConcurrentHashMap;

public final class RedBlackTree<V> {

    RedBlackNode<V> root;
    volatile RedBlackNode first;
    
    public RedBlackTree(RedBlackNode<V> b) {
        this.first = b;
        RedBlackNode<V> r = null;
        RedBlackNode<V> x = b ;
        x.left = x.right = null;
        if (r == null) {
            x.parent = null;
            x.red = false;
            r = x;
        }
        else{




        }







    }





    class RedBlackNode<V> {
        RedBlackNode<V> parent;  // 红黑树的连接
        RedBlackNode<V> left;
        RedBlackNode<V> right;
        RedBlackNode<V> prev;    //删除操作时用于断开连接
        boolean red;
        volatile V val;

        RedBlackNode(V val,
                     RedBlackNode<V> parent) {
            this.val = val ;
            this.parent = parent ;
        }


    }


}
