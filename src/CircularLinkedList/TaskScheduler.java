package CircularLinkedList;

class Task {
    String TaskId, name;
    int priority;
    String dueDate;

    Task(String TaskId, String name, int priority, String dueDate) {
        this.TaskId = TaskId;
        this.name = name;
        this.priority = priority;
        this.dueDate = dueDate;
    }
}

class CircularLinkedList {
    class Node {
        Task data;
        Node next;

        Node(Task data) {
            this.data = data;
            this.next = null;
        }
    }

    Node head, tail;

    CircularLinkedList() {
        head = null;
        tail = null;
    }

    void insertAtbegining(Task data) {
        Node n = new Node(data);
        if (head == null) {
            head = n;
            tail = n;
            head.next = n;
            tail.next = head;
        } else {
            n.next = head;
            tail.next = n;
            head = n;
        }
    }

    void insertAtEnd(Task data) {
        Node n = new Node(data);
        if (head == null) {
            head = n;
            tail = n;
            head.next = n;
            tail.next = head;
        } else {
            tail.next = n;
            n.next = head;
            tail = n;
        }
    }

    int length() {
        Node temp = head;
        if (head == null) return 0;
        int length = 1;
        while (temp.next != head) {
            temp = temp.next;
            length++;
        }
        return length;
    }

    void insertAtIndex(Task data, int index) {
        int l = length();

        if (index <= 0) {
            insertAtbegining(data);
            return;
        }

        if (index >= l) {
            insertAtEnd(data);
            return;
        }

        Node temp = head;
        int counter = 1;

        while (counter < index) {
            temp = temp.next;
            counter++;
        }

        Node newNode = new Node(data);
        newNode.next = temp.next;
        temp.next = newNode;
    }

    void removeTask(String id) {
        Node temp = head;

        if (head == null) {
            System.out.println("Empty list");
            return;
        }

        if (head.data.TaskId.equals(id)) {
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                tail.next = head;
            }
            return;
        }

        Node prev = null;
        while (temp != null && temp != head) {
            if (temp.data.TaskId.equals(id)) {
                if (temp == tail) {
                    tail = prev;
                    tail.next = head;
                } else {
                    prev.next = temp.next;
                }
                return;
            }
            prev = temp;
            temp = temp.next;
        }

        System.out.println("Task with ID " + id + " not found.");
    }

    void printList() {
        if (head == null) {
            System.out.println("The list is empty.");
            return;
        }

        Node temp = head;
        do {
            printData(temp);
            temp = temp.next;
        } while (temp != head);
    }

    void printData(Node temp) {
        System.out.println("Name : " + temp.data.name + " Task Id : " + temp.data.TaskId + " Priority : " + temp.data.priority + " Due Date : " + temp.data.dueDate);
    }

    void searchUsingPriority(int priority) {
        Node temp = head;
        boolean found = false;
        do {
            if (temp.data.priority == priority) {
                System.out.println("Found task with priority " + priority);
                printData(temp);
                found = true;
                break;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found) {
            System.out.println("Task with priority " + priority + " not found.");
        }
    }
}

public class TaskScheduler {
    public static void main(String[] args) {
        CircularLinkedList list = new CircularLinkedList();

        // Create some sample tasks
        Task task1 = new Task("T001", "Task 1", 3, "2025-03-25");
        Task task2 = new Task("T002", "Task 2", 1, "2025-03-20");
        Task task3 = new Task("T003", "Task 3", 2, "2025-03-22");
        Task task4 = new Task("T004", "Task 4", 1, "2025-03-30");

        // Insert tasks at the beginning and end
        list.insertAtbegining(task1);
        list.insertAtEnd(task2);
        list.insertAtEnd(task3);
        list.insertAtbegining(task4);

        // Print the list
        System.out.println("Task List after insertion:");
        list.printList();

        // Insert a task at a specific index
        Task task5 = new Task("T005", "Task 5", 4, "2025-03-28");
        list.insertAtIndex(task5, 2);
        System.out.println("\nTask List after inserting Task 5 at index 2:");
        list.printList();

        // Search tasks by priority
        System.out.println("\nSearching for tasks with priority 1:");
        list.searchUsingPriority(1);

        // Remove a task
        System.out.println("\nRemoving Task with ID T003:");
        list.removeTask("T003");

        // Print the list after removal
        System.out.println("\nTask List after removal:");
        list.printList();
    }
}
