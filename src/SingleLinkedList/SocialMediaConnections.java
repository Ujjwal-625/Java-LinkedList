package SingleLinkedList;

import java.util.ArrayList;
import java.util.List;

class User {
    String userId;
    String name;
    int age;
    List<String> friendIds;

    User(String userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friendIds = new ArrayList<>();
    }
}

class SocialNetwork {
    class Node {
        User data;
        Node next;

        Node(User data) {
            this.data = data;
            this.next = null;
        }
    }

    Node head;

    SocialNetwork() {
        head = null;
    }

    void addUser(User user) {
        Node newNode = new Node(user);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    User findUserById(String userId) {
        Node temp = head;
        while (temp != null) {
            if (temp.data.userId.equals(userId)) {
                return temp.data;
            }
            temp = temp.next;
        }
        return null;
    }

    User findUserByName(String name) {
        Node temp = head;
        while (temp != null) {
            if (temp.data.name.equals(name)) {
                return temp.data;
            }
            temp = temp.next;
        }
        return null;
    }

    void addFriendConnection(String userId1, String userId2) {
        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);

        if (user1 != null && user2 != null) {
            if (!user1.friendIds.contains(userId2)) {
                user1.friendIds.add(userId2);
            }
            if (!user2.friendIds.contains(userId1)) {
                user2.friendIds.add(userId1);
            }
            System.out.println("Friend connection added between " + userId1 + " and " + userId2 + ".");
        } else {
            System.out.println("One or both users not found.");
        }
    }

    void removeFriendConnection(String userId1, String userId2) {
        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);

        if (user1 != null && user2 != null) {
            user1.friendIds.remove(userId2);
            user2.friendIds.remove(userId1);
            System.out.println("Friend connection removed between " + userId1 + " and " + userId2 + ".");
        } else {
            System.out.println("One or both users not found.");
        }
    }

    List<String> findMutualFriends(String userId1, String userId2) {
        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);

        List<String> mutualFriends = new ArrayList<>();
        if (user1 != null && user2 != null) {
            for (String friendId : user1.friendIds) {
                if (user2.friendIds.contains(friendId)) {
                    mutualFriends.add(friendId);
                }
            }
        }
        return mutualFriends;
    }

    void displayFriends(String userId) {
        User user = findUserById(userId);
        if (user != null) {
            System.out.println("Friends of " + user.name + " (" + userId + "):");
            for (String friendId : user.friendIds) {
                User friend = findUserById(friendId);
                if (friend != null) {
                    System.out.println(friend.name + " (" + friendId + ")");
                }
            }
        } else {
            System.out.println("User not found.");
        }
    }

    void countFriends(String userId) {
        User user = findUserById(userId);
        if (user != null) {
            System.out.println(user.name + " (" + userId + ") has " + user.friendIds.size() + " friends.");
        } else {
            System.out.println("User not found.");
        }
    }

    void displayAllUsers() {
        Node temp = head;
        while(temp!=null){
            System.out.println("User ID: "+ temp.data.userId + ", Name: "+ temp.data.name + ", Age: "+ temp.data.age);
            temp = temp.next;
        }
    }

}

public class SocialMediaConnections {
    public static void main(String[] args) {
        SocialNetwork network = new SocialNetwork();

        User user1 = new User("U001", "Alice", 25);
        User user2 = new User("U002", "Bob", 30);
        User user3 = new User("U003", "Charlie", 28);
        User user4 = new User("U004", "David", 32);

        network.addUser(user1);
        network.addUser(user2);
        network.addUser(user3);
        network.addUser(user4);

        network.addFriendConnection("U001", "U002");
        network.addFriendConnection("U001", "U003");
        network.addFriendConnection("U002", "U003");
        network.addFriendConnection("U003", "U004");

        network.displayFriends("U001");
        network.displayFriends("U003");

        System.out.println("Mutual friends of Alice and Bob: " + network.findMutualFriends("U001", "U002"));

        network.removeFriendConnection("U002", "U003");
        network.displayFriends("U003");

        network.countFriends("U001");
        network.countFriends("U003");

        network.displayAllUsers();

        User foundUser = network.findUserById("U004");
        if (foundUser != null) {
            System.out.println("Found user by ID: " + foundUser.name);
        }

        foundUser = network.findUserByName("Alice");
        if (foundUser != null) {
            System.out.println("Found user by Name: " + foundUser.userId);
        }
    }
}