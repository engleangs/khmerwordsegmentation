package com.job4kh.tries;
public class TreeNode {
    private Object val;
    private TreeNode child[] = new TreeNode[ TriesBuilder.R +1];
    public TreeNode(){

    }

    public Object getVal() {
        return val;
    }

    public void setVal(Object val) {
        this.val = val;
    }

    public TreeNode[] getChild() {
        return child;
    }
}
