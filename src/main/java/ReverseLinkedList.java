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
        System.out.println(reverseList2(listNode));
    }

    private static void wrapNode(ListNode listNode, int val) {
        listNode.next = new ListNode(val);
        if (val != 5) {
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


    public static ListNode reverseList(ListNode head) {
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

    private static void wrapNodeList(ListNode node, List<Integer> valList, int index) {
        if (valList.size() > index) {
            node.next = new ListNode(valList.get(index));
            wrapNodeList(node.next, valList, index + 1);
        }
    }

    public static ListNode reverseList2(ListNode head) {
        ListNode temp = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = temp;
            temp = curr;
            curr = next;
        }
        return temp;
    }
}
