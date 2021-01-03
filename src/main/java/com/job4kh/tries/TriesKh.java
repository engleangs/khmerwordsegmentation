package com.job4kh.tries;



public class TriesKh implements SymbolTable {

    private TreeNode root;
    private int n;
    public void put(String key , Object val){
        if( key == null) throw new IllegalArgumentException("first argument to put is null");
        root = put(root, key, val, 0);
    }
    private TreeNode put(TreeNode node, String key , Object val, int d){
        if( node == null){
            node = new TreeNode();
        }
        if( d == key.length()) {
            if( node.getVal() == null) {
                n++;
            }
            node.setVal( val);
            return node;
        }
        int inx = TriesBuilder.charAt(d , key);
        node.getChild()[inx+1] = put( node.getChild()[inx+1], key,val, d+1);
        return node;
    }
    public int size(){
        return n;
    }
    private TreeNode get(TreeNode x, String key, int d){
        if( x ==null) return null;
        if( d == key.length()) {
            return x;
        }
        int inx = TriesBuilder.charAt( d, key);
        return get( x.getChild()[ inx + 1], key , d+1 );
    }

    public Object get(String key){
        if( key == null) throw new IllegalArgumentException("argument to get() is null");
        TreeNode x = get(root, key, 0);
        if( x== null) return null;
        return x.getVal();
    }
    @Override
    public boolean contains(String key){
        if( key ==null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

}
