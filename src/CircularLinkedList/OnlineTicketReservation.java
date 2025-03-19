package CircularLinkedList;

class Ticket {
    String ticketId;
    String customerName;
    String movieName;
    int seatNumber;
    String bookingTime;

    Ticket(String ticketId, String customerName, String movieName, int seatNumber, String bookingTime) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
    }

    @Override
    public String toString() {
        return "Ticket ID: " + ticketId + ", Customer: " + customerName +
                ", Movie: " + movieName + ", Seat: " + seatNumber +
                ", Time: " + bookingTime;
    }
}

class TicketReservationSystem {
    class Node {
        Ticket data;
        Node next;

        Node(Ticket data) {
            this.data = data;
            this.next = null;
        }
    }

    Node head, tail;
    int ticketCount;

    TicketReservationSystem() {
        head = null;
        tail = null;
        ticketCount = 0;
    }

    void addTicket(Ticket ticket) {
        Node newNode = new Node(ticket);
        if (head == null) {
            head = newNode;
            tail = newNode;
            newNode.next = head;
        } else {
            tail.next = newNode;
            newNode.next = head;
            tail = newNode;
        }
        ticketCount++;
    }

    void removeTicket(String ticketId) {
        if (head == null) {
            System.out.println("No tickets found.");
            return;
        }

        Node current = head;
        Node prev = tail;

        do {
            if (current.data.ticketId.equals(ticketId)) {
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
                ticketCount--;
                System.out.println("Ticket with ID " + ticketId + " removed.");
                return;
            }
            prev = current;
            current = current.next;
        } while (current != head);

        System.out.println("Ticket with ID " + ticketId + " not found.");
    }

    void displayTickets() {
        if (head == null) {
            System.out.println("No tickets reserved.");
            return;
        }

        Node current = head;
        do {
            System.out.println(current.data);
            current = current.next;
        } while (current != head);
    }

    void searchTicketByCustomerName(String customerName) {
        if (head == null) {
            System.out.println("No tickets found.");
            return;
        }

        Node current = head;
        boolean found = false;
        do {
            if (current.data.customerName.equals(customerName)) {
                System.out.println("Ticket found: " + current.data);
                found = true;
            }
            current = current.next;
        } while (current != head);

        if (!found) {
            System.out.println("No tickets found for customer: " + customerName);
        }
    }

    void searchTicketByMovieName(String movieName) {
        if (head == null) {
            System.out.println("No tickets found.");
            return;
        }

        Node current = head;
        boolean found = false;
        do {
            if (current.data.movieName.equals(movieName)) {
                System.out.println("Ticket found: " + current.data);
                found = true;
            }
            current = current.next;
        } while (current != head);

        if (!found) {
            System.out.println("No tickets found for movie: " + movieName);
        }
    }

    int getTotalBookedTickets() {
        return ticketCount;
    }
}

public class OnlineTicketReservation {
    public static void main(String[] args) {
        TicketReservationSystem reservationSystem = new TicketReservationSystem();

        Ticket ticket1 = new Ticket("T101", "Alice", "Avengers: Endgame", 10, "10:00 AM");
        Ticket ticket2 = new Ticket("T102", "Bob", "Spider-Man: No Way Home", 15, "11:30 AM");
        Ticket ticket3 = new Ticket("T103", "Charlie", "Inception", 20, "02:00 PM");
        Ticket ticket4 = new Ticket("T104", "David", "Avengers: Endgame", 5, "03:30 PM");

        reservationSystem.addTicket(ticket1);
        reservationSystem.addTicket(ticket2);
        reservationSystem.addTicket(ticket3);
        reservationSystem.addTicket(ticket4);

        System.out.println("Current Booked Tickets:");
        reservationSystem.displayTickets();

        reservationSystem.removeTicket("T102");

        System.out.println("\nTickets after removal:");
        reservationSystem.displayTickets();

        reservationSystem.searchTicketByCustomerName("Alice");
        reservationSystem.searchTicketByMovieName("Avengers: Endgame");

        System.out.println("\nTotal Booked Tickets: " + reservationSystem.getTotalBookedTickets());
    }
}