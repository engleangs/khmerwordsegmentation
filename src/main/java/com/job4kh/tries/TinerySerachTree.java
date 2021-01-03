package com.job4kh.tries;

public class TinerySerachTree<T> implements SymbolTable {
    private Node<T> root;

    @Override
    public boolean contains(String key) {
        return get(key) !=null;
    }

    private static class Node<T> {
        T val;
        Node<T> left, right, mid;
        char c;
    }

    public T get(String key) {
        Node<T> node = get(root, key, 0);
        if (node != null) {
            return node.val;
        }
        return null;
    }

    private Node<T> get(Node<T> node, String key, int d) {
        if (node == null) {
            return null;
        }
        char c = key.charAt(d);
        if (c < node.c) {
            return get(node.left, key, d);
        } else if (c > node.c) {
            return get(node.right, key, d);
        } else if (d < key.length() - 1) {
            return get(node.mid, key, d + 1);
        } else {
            return node;
        }
    }

    public void put(String key, T val) {
        root =put(root, key, val, 0);
    }

    private Node<T> put(Node node, String key, T val, int d) {
        char c = key.charAt(d);
        if (node == null) {
            node = new Node();
            node.c = c;
        }
        if (c < node.c) {
            node.left = put(node.left, key, val, d);
        } else if (c > node.c) {
            node.right = put(node.right, key, val, d);
        } else if (d < key.length() - 1) {
            node.mid = put(node.mid, key, val, d + 1);
        } else node.val = val;
        return node;
    }

}
