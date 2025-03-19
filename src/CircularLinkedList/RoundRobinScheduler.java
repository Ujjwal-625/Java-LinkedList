package CircularLinkedList;

class Process {
    String processId;
    int burstTime;
    int priority;
    int remainingTime;
    int waitingTime;
    int turnAroundTime;

    Process(String processId, int burstTime, int priority) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.priority = priority;
        this.remainingTime = burstTime;
        this.waitingTime = 0;
        this.turnAroundTime = 0;
    }
}

class ProcessQueue { //Changed Class Name
    class Node {
        Process data;
        Node next;

        Node(Process data) {
            this.data = data;
            this.next = null;
        }
    }

    Node head, tail;

    ProcessQueue() {
        head = null;
        tail = null;
    }

    void addProcess(Process process) {
        Node newNode = new Node(process);
        if (head == null) {
            head = newNode;
            tail = newNode;
            newNode.next = head;
        } else {
            tail.next = newNode;
            newNode.next = head;
            tail = newNode;
        }
    }

    void removeProcess(String processId) {
        if (head == null) {
            return;
        }

        Node current = head;
        Node prev = tail;

        do {
            if (current.data.processId.equals(processId)) {
                if (current == head) {
                    if (head == tail) {
                        head = null;
                        tail = null;
                    } else {
                        head = head.next;
                        tail.next = head;
                    }
                } else if (current == tail) {
                    tail = prev;
                    tail.next = head;
                } else {
                    prev.next = current.next;
                }
                return;
            }
            prev = current;
            current = current.next;
        } while (current != head);
    }

    void printProcesses() {
        if (head == null) {
            System.out.println("No processes in the queue.");
            return;
        }

        Node current = head;
        do {
            System.out.println("Process ID: " + current.data.processId +
                    ", Remaining Time: " + current.data.remainingTime +
                    ", Priority: " + current.data.priority);
            current = current.next;
        } while (current != head);
    }
}

public class RoundRobinScheduler {
    public static void main(String[] args) {
        ProcessQueue processQueue = new ProcessQueue(); //Changed Class Name
        int timeQuantum = 2;
        int currentTime = 0;

        Process p1 = new Process("P1", 5, 1);
        Process p2 = new Process("P2", 4, 3);
        Process p3 = new Process("P3", 2, 2);
        Process p4 = new Process("P4", 3, 4);

        processQueue.addProcess(p1);
        processQueue.addProcess(p2);
        processQueue.addProcess(p3);
        processQueue.addProcess(p4);

        ProcessQueue.Node currentProcess = processQueue.head; //Changed Class Name

        while (processQueue.head != null) {
            System.out.println("\nTime: " + currentTime);
            processQueue.printProcesses();

            if (currentProcess.data.remainingTime <= timeQuantum && currentProcess.data.remainingTime > 0) {
                currentTime += currentProcess.data.remainingTime;
                currentProcess.data.remainingTime = 0;
            } else if (currentProcess.data.remainingTime > 0) {
                currentTime += timeQuantum;
                currentProcess.data.remainingTime -= timeQuantum;
            }

            if (currentProcess.data.remainingTime <= 0) {
                currentProcess.data.turnAroundTime = currentTime;
                processQueue.removeProcess(currentProcess.data.processId);

                if (processQueue.head == null) {
                    break;
                }
                currentProcess = processQueue.head;

            } else {
                currentProcess = currentProcess.next;
            }
        }

        calculateAndDisplayAverages(p1, p2, p3, p4);
    }

    static void calculateAndDisplayAverages(Process... processes) {
        int totalWaitingTime = 0;
        int totalTurnAroundTime = 0;

        for (Process process : processes) {
            process.waitingTime = process.turnAroundTime - process.burstTime;
            totalWaitingTime += process.waitingTime;
            totalTurnAroundTime += process.turnAroundTime;
            System.out.println("Process " + process.processId + " - Waiting Time: " + process.waitingTime + ", Turnaround Time: " + process.turnAroundTime);
        }

        double averageWaitingTime = (double) totalWaitingTime / processes.length;
        double averageTurnAroundTime = (double) totalTurnAroundTime / processes.length;

        System.out.println("\nAverage Waiting Time: " + averageWaitingTime);
        System.out.println("Average Turnaround Time: " + averageTurnAroundTime);
    }
}