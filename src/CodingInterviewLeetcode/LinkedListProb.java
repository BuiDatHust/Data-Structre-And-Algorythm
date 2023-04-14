package CodingInterviewLeetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LinkedListProb {
    public static void showLinkedList(SingleLinkedList root) {
        System.out.println("Linked list:");
        SingleLinkedList temp = root;
        while (temp != null) {
            System.out.println(temp.val);
            temp = temp.next;
        }
    }

    public static void addTail(SingleLinkedList root, int x) {
        SingleLinkedList newNode = new SingleLinkedList(x);
        SingleLinkedList temp = root;
        if (temp == null) {
            root = newNode;
            root.next = null;
            return;
        }
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
        temp.next.next = null;
    }

    public static void deleteNode(SingleLinkedList node) {
        node.val = node.next.val;
        SingleLinkedList newNode = node.next.next;
        node.next.next = null;
        node.next = newNode;
    }

    public static SingleLinkedList removeNthFromEnd(SingleLinkedList head, int n) {
        int size = 1;
        SingleLinkedList temp = head;
        int i = 1;

        while (temp.next != null) {
            temp = temp.next;
            size++;
        }

        temp = head;
        if (size == n) {
            SingleLinkedList newHead = head.next;
            temp.next = null;
            head = newHead;
            return head;
        }
        while (i < size - n && temp.next != null) {
            temp = temp.next;
            i++;
        }
        System.out.println(size);
        SingleLinkedList newNode = temp.next.next;
        temp.next.next = null;
        temp.next = newNode;

        return head;
    }

    public static SingleLinkedList reverseList(SingleLinkedList head) {
        SingleLinkedList temp = head;
        SingleLinkedList newList = new SingleLinkedList(0);
        SingleLinkedList newTemp = newList;
        while (temp != null) {
            newTemp.next = new SingleLinkedList(temp.val);
            newTemp = newTemp.next;
            newTemp.next = null;
            temp = temp.next;
        }
        newList = newList.next;

        SingleLinkedList current = newList;
        SingleLinkedList prev = null;
        SingleLinkedList next = null;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    public static SingleLinkedList mergeTwoLists(SingleLinkedList list1, SingleLinkedList list2) {
        SingleLinkedList mergedLinkedList = new SingleLinkedList(0);
        SingleLinkedList dummyList = mergedLinkedList;

        while (true) {
            if (list1 == null) {
                dummyList.next = list2;
                break;
            }
            if (list2 == null) {
                dummyList.next = list1;
                break;
            }

            if (list1.val <= list2.val) {
                dummyList.next = list1;
                list1 = list1.next;
            } else {
                dummyList.next = list2;
                list2 = list2.next;
            }
            dummyList = dummyList.next;
        }

        return mergedLinkedList.next;
    }

    public static boolean isPalindrome(SingleLinkedList head) {
        SingleLinkedList temp = head;
        SingleLinkedList reversedList = reverseList(temp);
        System.out.println(reversedList == temp);

        while (temp != null) {
            if (temp.val != reversedList.val) {
                return false;
            }
            temp = temp.next;
            reversedList = reversedList.next;
        }

        return true;
    }

    //    Middle of the Linked List, time: O(n), space: O(n);
    public static SingleLinkedList middleNode(SingleLinkedList head) {
        int len = 0;
        List<SingleLinkedList> arr = new ArrayList<>();

        while (head != null) {
            len++;
            arr.add(head);
            head = head.next;
        }
        System.out.println(len);

        return arr.get(len / 2);
    }

    // space O(1), time: O(n/2)
    public static SingleLinkedList middleNode1(SingleLinkedList head) {
        SingleLinkedList mid = head, end = head;

        while (end != null && end.next != null) {
            mid = mid.next;
            end = end.next.next;
        }

        return mid;
    }

    //    Linked List Cycle II, time: O(n), space: O(1)
    public static SingleLinkedList detectCycle(SingleLinkedList head) {
        Map<SingleLinkedList, Boolean> dict = new HashMap<>();

        while (head != null) {
            if (dict.containsKey(head)) {
                return head;
            }
            dict.put(head, true);
            head = head.next;
        }

        return null;
    }

//    Merge k Sorted Lists, time: O(n*k*logk), space:  O(n) because we use merge sort algo to solve and we need call n recursive
//    original approach is loop through list and merge step by step element to result, it has complexity: O(n*k^2), space:O(1)
    public static SingleLinkedList mergeKLists(SingleLinkedList[] lists) {
        if (lists.length == 0) {
            return null;
        }

        return mergeKListDevideAndConquer(lists, 0, lists.length - 1);
    }

    public static SingleLinkedList mergeKListDevideAndConquer(SingleLinkedList[] lists, int low, int high) {
        if (low == high) {
            return lists[low];
        }

        int mid = low + (high - low) / 2;
        SingleLinkedList left = mergeKListDevideAndConquer(lists, low, mid);
        SingleLinkedList right = mergeKListDevideAndConquer(lists, mid + 1, high);

        return mergeTwoList(left, right);
    }

    public static SingleLinkedList mergeTwoList(SingleLinkedList l1, SingleLinkedList l2) {
        SingleLinkedList mergedList = new SingleLinkedList(0);
        SingleLinkedList temp = mergedList;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                temp.next = l1;
                l1 = l1.next;
            } else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }

        if (l1 != null) {
            temp.next = l1;
        }
        if (l2 != null) {
            temp.next = l2;
        }

        return mergedList.next;
    }

    public static void main(String[] args) {
        SingleLinkedList root1 = new SingleLinkedList(1);
        root1.next = null;
        addTail(root1, 4);
        addTail(root1, 5);
        SingleLinkedList root2 = new SingleLinkedList(1);
        root2.next = null;
        addTail(root2, 3);
        addTail(root2, 4);
        SingleLinkedList root3 = new SingleLinkedList(2);
        root3.next = null;
        addTail(root3, 6);
        SingleLinkedList[] lists = {root2, root3, root1};
        SingleLinkedList l = mergeKLists(lists);
        showLinkedList(l);
    }
}
