import java.util.ArrayList;
import java.util.List;

/**
 * @author jiaheming
 * @date 2018-11-30 08:54
 * contact jiaheming2006@126.com
 */
public class ReverseLinkedList {


    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        wrapNode(listNode, 2);
        System.out.println("main head:" + listNode.hashCode());
        System.out.println("main head next:" + listNode.next.hashCode());
        System.out.println("main head next next :" + listNode.next.next.hashCode());
        System.out.println(reverseList4(listNode));
    }

    private static void wrapNode(ListNode listNode, int val) {
        listNode.next = new ListNode(val);
        if (val != 3) {
            wrapNode(listNode.next, val + 1);
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    /**
     * 通过{@link List#add(int, Object)}，遍历head，将元素加入到list中
     * 然后遍历list封装ListNode输出
     *
     * @param head
     * @return 反转后的链表
     */
    public static ListNode reverseList(ListNode head) {
        if (null == head) {
            return null;
        }
        List<Integer> valList = new ArrayList<>();
        //O(n)
        while (head != null) {
            valList.add(0, head.val);
            head = head.next;
        }
        //O(n)
        ListNode listNode = new ListNode(valList.get(0));
        wrapNodeList(listNode, valList, 1);
        return listNode;
    }

    /**
     * @param node
     * @param valList
     * @param index
     */
    private static void wrapNodeList(ListNode node, List<Integer> valList, int index) {
        if (valList.size() > index) {
            node.next = new ListNode(valList.get(index));
            wrapNodeList(node.next, valList, index + 1);
        }
    }

    /**
     * 官方推荐的迭代解决思路
     * 1->2->3->null
     * while第一次执行:
     * head = 1,2,3
     * step 1 prev = null
     * step 2 prev = null; curr = 1,2,3
     * step 3 prev = null; curr = 1,2,3
     * step 4 prev = null; curr = 1,2,3; nextTemp = 2,3
     * step 5 prev = null; curr = 1; nextTemp = 2,3
     * step 6 prev = 1; curr = 1; nextTemp = 2,3
     * step 7 prev = 1; curr = 2,3;
     * while第二次执行:
     * step 4 prev = 1; curr = 2,3;nextTemp =3
     * step 5 prev = 1; curr = 2,1;nextTemp =3
     * step 6 prev = 2,1;curr = 2,1;nextTemp =3
     * step 7 prev = 2,1;curr = 3;
     * while第三次执行:
     * step 4 prev = 2,1;curr = 3;nextTemp =null
     * step 5 prev = 2,1;curr = 3,2,1;nextTemp=null
     * step 6 prev = 3,2,1;curr=3,2,1;nextTemp=null;
     * step 7 prev = 3,2,1;curr=null
     * 跳出循环
     * return prev
     *
     * @param head
     * @return
     */
    public static ListNode reverseList2(ListNode head) {
        ListNode prev = null; // step 1
        ListNode curr = head; // step 2
        while (curr != null) {// step 3
            ListNode nextTemp = curr.next;// step 4
            curr.next = prev; // step 5
            prev = curr;// step 6
            curr = nextTemp; //step 7
        }
        return prev;
    }

    /**
     * 官方推荐的递归解决思路
     * 1->2->3->null
     * 递归第一次
     * step 3 head.next = 2,3
     * 进入递归第二次
     * step 3 head.next = 3
     * 进入递归第三次
     * step 2 return 3;
     * 进入递归第二次
     * step 3 head = 2,3;p = 3;
     * step 4 head = 2->3->2(一个圈 2指向3 3指向2);p=head.next = 3->2->3 一个圈
     * step 5 head = 2; p = 3,2
     * 这里暂时未理解
     *
     * @param head
     * @return
     */
    public static ListNode reverseList3(ListNode head) {
        if (head == null || head.next == null) {//step 1
            return head;//step 2
        }
        ListNode p = reverseList3(head.next);//step 3
        head.next.next = head;//step 4
        head.next = null;//step 5
        return p;//step 6
    }


    public static ListNode reverseList4(ListNode head) {
        return reverseListInt(head, null);
    }

    private static ListNode reverseListInt(ListNode head, ListNode newHead) {
        if (head == null) {
            return newHead;
        }
        ListNode next = head.next;
        head.next = newHead;
        return reverseListInt(next, head);
    }
}
