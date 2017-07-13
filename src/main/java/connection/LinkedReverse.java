package connection;

/**
 * Created by liujs on 17-7-13.
 */
public class LinkedReverse {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    //循环方式,指针下移直到NULL
    public static Node reverseloop(Node head) {
        System.out.println("====reverseloop=====");
        if (head == null) return head;
        Node pre = head;
        Node cur = head.next;
        Node tmp;

        while (cur != null) {
            tmp = cur.next;
            cur.next = pre;
            //指针向下移动
            pre = cur;
            cur = tmp;
        }
        //初始的 head 没有next
        head.next = null;

        return pre;

    }


    //递归翻转，在反转当前节点之前先反转后续节点 ,直到没有下一个。
    public static Node reverseSelf(Node head) {
        System.out.println("====reverseSelf=====cur = " + head.data);
        if (head == null || head.next == null) {
            //递归的出口，最后一个节点，没有子节点。head == null 是过滤空链表。
            return head;
        }

        //第一次递归循环是为倒数第二节点
        Node pre = reverseSelf(head.next);
        head.next.next = head;
        head.next = null;
        System.out.println("返回 pre = " + pre.data);
        return pre;

    }


    public static void printLink(Node head) {
        Node h = head;
        //先输出正常顺序

        while (h != null) {
            System.out.println("node = " + h.data);
            h = h.next;
        }
    }

    public static void main(String args[]) {
        Node head = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;


        //检查翻转
        printLink(head);
        //Node t = reverseloop(head);
        Node t = reverseSelf(head);
        printLink(t);

        //System.out.println("final: " + mul(3));


    }

    static int mul(int n) {
        System.out.println("n = " + n);
        if (n == 1)
            return 1;
        return n * mul(n - 1);

    }
}
