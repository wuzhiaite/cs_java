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
        if (r == null) {//第一个节点。插入为根节点
            x.parent = null;
            x.red = false;
            r = x;
        }
        else{
            r = balanceInsertion(r, x);
        }
        this.root = r;
    }

    /**
     *  插入数据
     * @param root
     * @param x
     * @return
     */
    private RedBlackNode<V> balanceInsertion(RedBlackNode<V> root, RedBlackNode<V> x) {
        x.red = true;
        for (RedBlackNode<V> xp, xpp, xppl, xppr;;) {
            if ((xp = x.parent) == null) {
                x.red = false;
                return x;
            }
            else if (!xp.red || (xpp = xp.parent) == null)
                return root;
            if (xp == (xppl = xpp.left)) {
                if ((xppr = xpp.right) != null && xppr.red) {
                    xppr.red = false;
                    xp.red = false;
                    xpp.red = true;
                    x = xpp;
                }
                else {
                    if (x == xp.right) {
                        root = rotateLeft(root, x = xp);
                        xpp = (xp = x.parent) == null ? null : xp.parent;
                    }
                    if (xp != null) {
                        xp.red = false;
                        if (xpp != null) {
                            xpp.red = true;
                            root = rotateRight(root, xpp);
                        }
                    }
                }
            }
            else {
                if (xppl != null && xppl.red) {
                    xppl.red = false;
                    xp.red = false;
                    xpp.red = true;
                    x = xpp;
                }
                else {
                    if (x == xp.left) {
                        root = rotateRight(root, x = xp);
                        xpp = (xp = x.parent) == null ? null : xp.parent;
                    }
                    if (xp != null) {
                        xp.red = false;
                        if (xpp != null) {
                            xpp.red = true;
                            root = rotateLeft(root, xpp);
                        }
                    }
                }
            }
        }
    }

    private RedBlackNode<V> rotateRight(RedBlackNode<V> root, RedBlackNode<V> vRedBlackNode) {
        return null;
    }

    private RedBlackNode<V> rotateLeft(RedBlackNode<V> root, RedBlackNode<V> vRedBlackNode) {
        return null;
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
