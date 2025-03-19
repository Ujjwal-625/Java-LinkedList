package DoubleLinkedList;

class Book {
    String title;
    String author;
    String genre;
    String bookId;
    boolean available;

    Book(String title, String author, String genre, String bookId, boolean available) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookId = bookId;
        this.available = available;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Genre: " + genre + ", ID: " + bookId + ", Available: " + available;
    }
}

class Library {
    class Node {
        Book data;
        Node next;
        Node prev;

        Node(Book data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    Node head, tail;
    int count;

    Library() {
        head = null;
        tail = null;
        count = 0;
    }

    void insertAtHead(Book data) {
        Node n = new Node(data);
        if (head == null) {
            head = n;
            tail = n;
        } else {
            n.next = head;
            head.prev = n;
            head = n;
        }
        count++;
    }

    void insertAtTail(Book data) {
        Node n = new Node(data);
        if (head == null) {
            head = n;
            tail = n;
        } else {
            tail.next = n;
            n.prev = tail;
            tail = n;
        }
        count++;
    }

    void insertAtIndex(Book data, int index) {
        if (index < 0 || index > count) {
            System.out.println("Invalid index.");
            return;
        }

        if (index == 0) {
            insertAtHead(data);
            return;
        }

        if (index == count) {
            insertAtTail(data);
            return;
        }

        Node n = new Node(data);
        Node temp = head;
        int counter = 0;

        while (counter < index - 1) {
            temp = temp.next;
            counter++;
        }

        n.next = temp.next;
        n.prev = temp;
        temp.next.prev = n;
        temp.next = n;
        count++;
    }

    void removeBook(String bookId) {
        Node temp = head;
        while (temp != null) {
            if (temp.data.bookId.equals(bookId)) {
                if (temp.prev == null) {
                    head = temp.next;
                    if (head != null) {
                        head.prev = null;
                    } else {
                        tail = null;
                    }
                } else if (temp.next == null) {
                    tail = temp.prev;
                    tail.next = null;
                } else {
                    temp.prev.next = temp.next;
                    temp.next.prev = temp.prev;
                }
                count--;
                System.out.println("Book with ID " + bookId + " removed.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book with ID " + bookId + " not found.");
    }

    void searchBookByTitle(String title) {
        Node temp = head;
        while (temp != null) {
            if (temp.data.title.equals(title)) {
                System.out.println("Book found: " + temp.data);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book with title " + title + " not found.");
    }

    void searchBookByAuthor(String author) {
        Node temp = head;
        while (temp != null) {
            if (temp.data.author.equals(author)) {
                System.out.println("Book found: " + temp.data);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book by author " + author + " not found.");
    }

    void updateAvailability(String bookId, boolean available) {
        Node temp = head;
        while (temp != null) {
            if (temp.data.bookId.equals(bookId)) {
                temp.data.available = available;
                System.out.println("Availability status updated for book ID " + bookId);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book with ID " + bookId + " not found.");
    }

    void forwardPrint() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    void backwardPrint() {
        Node temp = tail;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.prev;
        }
    }

    int countBooks() {
        return count;
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();

        Book book1 = new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", "Science Fiction", "B001", true);
        Book book2 = new Book("Pride and Prejudice", "Jane Austen", "Romance", "B002", false);
        Book book3 = new Book("1984", "George Orwell", "Dystopian", "B003", true);
        Book book4 = new Book("To Kill a Mockingbird", "Harper Lee", "Classic", "B004", true);

        library.insertAtHead(book1);
        library.insertAtTail(book2);
        library.insertAtIndex(book3, 1);
        library.insertAtTail(book4);

        System.out.println("Books (forward):");
        library.forwardPrint();

        System.out.println("\nBooks (backward):");
        library.backwardPrint();

        library.removeBook("B002");

        System.out.println("\nBooks after removal:");
        library.forwardPrint();

        library.searchBookByTitle("1984");
        library.searchBookByAuthor("Harper Lee");

        library.updateAvailability("B001", false);

        System.out.println("\nTotal books: " + library.countBooks());
    }
}