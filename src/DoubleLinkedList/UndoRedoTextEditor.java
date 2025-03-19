package DoubleLinkedList;

class TextEditor {
    class Node {
        String text;
        Node prev;
        Node next;

        Node(String text) {
            this.text = text;
            this.prev = null;
            this.next = null;
        }
    }

    Node currentState;
    Node head;
    Node tail;
    int historyLimit;
    int historyCount;

    TextEditor(int historyLimit) {
        this.historyLimit = historyLimit;
        this.currentState = null;
        this.head = null;
        this.tail = null;
        this.historyCount = 0;
    }

    void type(String text) {
        Node newNode = new Node(text);
        if (head == null) {
            head = newNode;
            tail = newNode;
            currentState = newNode;
        } else {
            currentState.next = newNode;
            newNode.prev = currentState;
            currentState = newNode;
            tail = newNode;
        }
        historyCount++;
        if (historyCount > historyLimit) {
            head = head.next;
            head.prev = null;
            historyCount--;
        }
    }

    void undo() {
        if (currentState != null && currentState.prev != null) {
            currentState = currentState.prev;
        } else {
            System.out.println("Cannot undo further.");
        }
    }

    void redo() {
        if (currentState != null && currentState.next != null) {
            currentState = currentState.next;
        } else {
            System.out.println("Cannot redo further.");
        }
    }

    void displayCurrentText() {
        if (currentState != null) {
            System.out.println("Current Text: " + currentState.text);
        } else {
            System.out.println("No text available.");
        }
    }
}

public class UndoRedoTextEditor {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor(10);

        editor.type("Hello, ");
        editor.displayCurrentText();

        editor.type("world!");
        editor.displayCurrentText();

        editor.type(" How are you?");
        editor.displayCurrentText();

        editor.undo();
        editor.displayCurrentText();

        editor.undo();
        editor.displayCurrentText();

        editor.redo();
        editor.displayCurrentText();

        editor.type(" Good bye!");
        editor.displayCurrentText();

        editor.undo();
        editor.undo();
        editor.undo();
        editor.undo();
        editor.displayCurrentText();

        editor.redo();
        editor.redo();
        editor.redo();
        editor.displayCurrentText();
    }
}