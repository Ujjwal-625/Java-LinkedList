import java.lang.management.MonitorInfo;

class Movie{
    String title,director;
    int yearOfRelease;
    double rating;
    Movie(String title,String director,int yearOfRelease,double rating){
        this.title=title;
        this.director=director;
        this.yearOfRelease=yearOfRelease;
        this.rating=rating;
    }
}

class DoubleLinkedList{
    class Node{
        Movie data;
        Node next;
        Node prev;
        Node(Movie data){
            this.data=data;
            this.next=null;
            this.prev=null;
        }
    }

    Node head,tail;

    DoubleLinkedList(){
        head=null;
        tail=null;
    }

    void insertAtHead(Movie data){
        Node n=new Node(data);
        if(head==null){
            head=n;
            tail=n;
        }
        else{
            n.next=head;
            head.prev=n;
            head=n;
        }
    }

    void insertAtTail(Movie data){
        Node n=new Node(data);
        if(head==null){
            head=n;
            tail=n;
        }
        else{
            tail.next=n;
            n.prev=tail;
            tail=n;
        }
    }

    void insertAtIndex(Movie data, int index) {
        Node n = new Node(data);
        int counter = 0;

        // Case 1: Insert at the beginning (index == 0)
        if (index == 0) {
            n.next = head;   // New node points to the current head
            if (head != null) {
                head.prev = n; // If the list is not empty, update the previous pointer of the old head
            }
            head = n;         // Set the new node as the head
            return;
        }

        Node temp = head;
        while (temp != null && counter < index) {
            temp = temp.next;
            counter++;
        }

        // Case 2: Index out of bounds
        if (temp == null) {
            System.out.println("Index Out of bound");
            return;
        }

        // Case 3: Insert between nodes
        n.next = temp;
        n.prev = temp.prev;

        if (temp.prev != null) {
            temp.prev.next = n;
        }

        temp.prev = n;
    }

    void removeMovie(String title){
        if(head !=null && head.data.title.equals(title)){
            head=head.next;
            System.out.println("found student at the head of the list ");
            return ;
        }
        Node temp=head ;
        while(temp!=null){
            if(temp.data.title.equals(title)){
                break;
            }
            temp=temp.next;
        }

        if(temp==null){
            System.out.println("No movie found with given title");
        }
        else{
            temp.prev.next=temp.next;
            if(temp.next!=null){
                temp.next.prev=temp.prev;
            }
            System.out.println("Movie Deleted ");
        }
    }

    void searchMovieByDirector(String director){
        Node temp=head;
        while(temp!=null){
            if(temp.data.director.equals(director)){
                System.out.println("Movie found ");
                System.out.println("Movie title is : "+temp.data.title);
                break;
            }
            temp=temp.next;
        }

        if(temp==null){
            System.out.println("Movie Not Found ");
        }


    }
    
    void forwardPrint(){
        Node temp=head;
        while(temp!=null){
            System.out.println(temp.data);
            temp=temp.next;
        }
    }

    void backwardPrint(){
        Node temp=tail;
        while(temp!=null){
            System.out.println(temp.data);
            temp=temp.prev;
        }
    }

    void updateMovieRating(String title,double rating){
        Node temp=head;
        while(temp!=null){
            if(temp.data.title.equals(title)){
                break;
            }
            temp=temp.next;
        }

        if(temp!=null){
            temp.data.rating=rating;
            System.out.println("Updated the rating ");
        }
    }


}

public class MovieManagementSystem {
    public static void main(String[] args) {
        DoubleLinkedList movieList = new DoubleLinkedList();

        // Create movie objects
        Movie movie1 = new Movie("Inception", "Christopher Nolan", 2010, 8.8);
        Movie movie2 = new Movie("The Matrix", "The Wachowskis", 1999, 8.7);
        Movie movie3 = new Movie("Interstellar", "Christopher Nolan", 2014, 8.6);
        Movie movie4 = new Movie("The Dark Knight", "Christopher Nolan", 2008, 9.0);
        Movie movie5 = new Movie("The Godfather", "Francis Ford Coppola", 1972, 9.2);

        // Insert movies at the head
        movieList.insertAtHead(movie1);
        movieList.insertAtHead(movie2);

        // Insert movie at the tail
        movieList.insertAtTail(movie3);

        // Insert movie at a specific index
        movieList.insertAtIndex(movie4, 1);  // Insert The Dark Knight at index 1
        movieList.insertAtIndex(movie5, 3);  // Insert The Godfather at index 3

        // Print the list of movies forward
        System.out.println("Movies list (forward):");
        movieList.forwardPrint();

        // Print the list of movies backward
        System.out.println("\nMovies list (backward):");
        movieList.backwardPrint();

        // Remove a movie by title
        System.out.println("\nRemoving a movie with title 'The Matrix':");
        movieList.removeMovie("The Matrix");

        // Print the updated list of movies forward
        System.out.println("\nUpdated Movies list (forward):");
        movieList.forwardPrint();

        // Search for a movie by director
        System.out.println("\nSearching for movies by director 'Christopher Nolan':");
        movieList.searchMovieByDirector("Christopher Nolan");
    }
}

