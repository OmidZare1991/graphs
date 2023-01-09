package com.graphs.practice;

class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

public class BinarySearchTree {
    static Node root;

    public static void main(String[] args) {

        insert(2);
        insert(1);
        insert(4);
        insert(3);
        insert(6);
        insert(7);

        System.out.println(find(8));
        System.out.println(find(4));
    }

    private static void insert(int data) {
        Node node = new Node(data);
        if (root == null) {
            root = node;
            return;
        }
        Node current = root;
        Node parentOfCurrent = null;
        while (current != null) {
            parentOfCurrent = current;
            if (data < current.data) {
                current = current.left;
            } else if (data > current.data) {
                current = current.right;
            }
        }
        if (data < parentOfCurrent.data) {
            parentOfCurrent.left = node;
        } else {
            parentOfCurrent.right = node;
        }
    }

    private static boolean find(int data) {
        Node current = root;
        boolean found = false;

        while (current != null) {
            if (data == current.data) {
                found = true;
                break;
            }
            if (data < current.data) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return found;
    }

    private static void remove(int data) {
        // 1. the node is base root
        // 2. the node is leftChild
        // 3. the node is right child
        // 4. the node does not exist
        Node current = root;
        if (data == current.data) {

        }
    }
}
