package SingleLinkedList;

class Item {
    String itemName;
    String itemId;
    int quantity;
    double price;

    Item(String itemName, String itemId, int quantity, double price) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
    }
}

class InventoryList {
    class Node {
        Item data;
        Node next;

        Node(Item data) {
            this.data = data;
            this.next = null;
        }
    }

    Node head, tail;

    InventoryList() {
        head = null;
        tail = null;
    }

    // Add item at the beginning
    void insertAtHead(Item data) {
        Node n = new Node(data);
        if (head == null) {
            head = n;
            tail = n;
        } else {
            n.next = head;
            head = n;
        }
    }

    // Add item at the end
    void insertAtTail(Item data) {
        Node n = new Node(data);
        if (head == null) {
            head = n;
            tail = n;
        } else {
            tail.next = n;
            tail = n;
        }
    }

    // Add item at a specific position
    void insertAtPosition(int index, Item data) {
        Node n = new Node(data);
        if (head == null || index == 0) {
            insertAtHead(data);
            return;
        }

        Node temp = head;
        int counter = 0;
        while (temp != null && counter < index - 1) {
            temp = temp.next;
            counter++;
        }

        if (temp != null) {
            n.next = temp.next;
            temp.next = n;
            if (n.next == null) tail = n;
        } else {
            insertAtTail(data);
        }
    }

    // Remove an item by Item ID
    void removeByItemId(String itemId) {
        if (head == null) {
            System.out.println("Inventory is empty");
            return;
        }

        if (head.data.itemId.equals(itemId)) {
            head = head.next;
            if(head == null) tail = null;
            System.out.println("Item with ID " + itemId + " removed.");
            return;
        }

        Node temp = head;
        while (temp.next != null && !temp.next.data.itemId.equals(itemId)) {
            temp = temp.next;
        }

        if (temp.next == null) {
            System.out.println("Item with ID " + itemId + " not found.");
        } else {
            temp.next = temp.next.next;
            if (temp.next == null) tail = temp;
            System.out.println("Item with ID " + itemId + " removed.");
        }
    }

    // Update the quantity of an item by Item ID
    void updateQuantityByItemId(String itemId, int newQuantity) {
        Node temp = head;
        while (temp != null) {
            if (temp.data.itemId.equals(itemId)) {
                temp.data.quantity = newQuantity;
                System.out.println("Updated quantity for item ID " + itemId);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item with ID " + itemId + " not found.");
    }

    // Search for an item by Item ID
    void searchByItemId(String itemId) {
        Node temp = head;
        while (temp != null) {
            if (temp.data.itemId.equals(itemId)) {
                System.out.println("Item found: " + temp.data.itemName + " " + temp.data.itemId);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item with ID " + itemId + " not found.");
    }

    // Search for an item by Item Name
    void searchByItemName(String itemName) {
        Node temp = head;
        while (temp != null) {
            if (temp.data.itemName.equals(itemName)) {
                System.out.println("Item found: " + temp.data.itemName + " " + temp.data.itemId);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item with name " + itemName + " not found.");
    }

    // Calculate total value of inventory (Price * Quantity)
    void calculateTotalValue() {
        double totalValue = 0;
        Node temp = head;
        while (temp != null) {
            totalValue += temp.data.price * temp.data.quantity;
            temp = temp.next;
        }
        System.out.println("Total value of inventory: $" + totalValue);
    }



    // Print inventory
    void printInventory() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data.itemName + " | " + temp.data.itemId + " | Quantity: " + temp.data.quantity + " | Price: $" + temp.data.price);
            temp = temp.next;
        }
    }
}

public class InventoryManagement {
    public static void main(String[] args) {
        InventoryList inventory = new InventoryList();

        Item item1 = new Item("Apple", "I001", 100, 0.5);
        Item item2 = new Item("Banana", "I002", 150, 0.3);
        Item item3 = new Item("Orange", "I003", 200, 0.7);
        Item item4 = new Item("Mango", "I004", 50, 1.2);
        Item item5 = new Item("Grapes", "I005", 80, 2.0);

        inventory.insertAtHead(item1);
        inventory.insertAtTail(item2);
        inventory.insertAtTail(item3);
        inventory.insertAtPosition(2, item4);
        inventory.insertAtTail(item5);

        System.out.println("Inventory List:");
        inventory.printInventory();

        inventory.removeByItemId("I003");

        System.out.println("\nInventory after removal:");
        inventory.printInventory();

        inventory.updateQuantityByItemId("I002", 180);

        System.out.println("\nInventory after update:");
        inventory.printInventory();

        inventory.searchByItemId("I004");
        inventory.searchByItemName("Banana");

        inventory.calculateTotalValue();

        System.out.println("\nSorted Inventory by Item Name (Ascending):");
        inventory.printInventory();
    }
}