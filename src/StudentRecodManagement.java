
class Student{
    int rollNumber,age;
    String name;
    char grade;

    Student(int rollNumber,String name,char grade,int age){
        this.rollNumber=rollNumber;
        this.name=name;
        this.grade=grade;
        this.age=age;
    }
}

class SingleLinkedList{
    class Node{
        Student data;
        Node next;

        Node(){
            data=null;
            next=null;
        }

        Node(Student data){
            this.data=data;
            this.next=null;
        }
    }

    Node head,tail;
    SingleLinkedList(){
        head=null;
        tail=null;
    }

    void insertAtHead(Student data){
        if(head==null){
            Node n=new Node(data);
            head=n;
            tail=n;
        }
        else{
            Node n=new Node(data);
            n.next=head;
            head=n;
        }
    }

    void insertAtTail(Student data){
        if(head==null){
            Node n=new Node(data);
            head=n;
            tail=n;
        }
        else{
            Node n=new Node(data);
            tail.next=n;
            tail=n;
        }

    }

    void insertAtPosition(int index,Student data){
        Node n=new Node(data);
        if(head==null){
            head=n;
            tail=n;
        }
        else{
            Node temp=head;
            int counter=0;
            while(temp!=null && counter<index){
                temp=temp.next;
                counter++;
            }
            if(temp==null){
                tail.next=n;
                tail=n;
            }
            else if (temp==head){
                n.next=head;
                head=n;
            }
            else{
                n.next=temp.next;
                temp.next=n;
            }
        }
    }

    void printStudents(){
        Node temp=head;
        while(temp!=null){
            System.out.println(temp.data.name+" "+temp.data.rollNumber+" "+temp.data.age+" "+temp.data.grade);
            temp=temp.next;
        }
    }

    void DeleteByRollnumber(int rollNumber){
        if(head.data.rollNumber==rollNumber){
            head=head.next;
            System.out.println("found student at the head of the list ");
            return ;
        }
        Node temp=head ;
        while(temp!=null && (temp.next!=null && temp.next.data.rollNumber!=rollNumber)){
            temp=temp.next;
        }
        if(temp==null || temp.next==null){
            System.out.println("No Student with given roll number exist");
        }
        if(temp.next!=null) {
            temp.next = temp.next.next;//deleting the node
        }

    }

    void findStudent(int rollNumber){
        Node temp=head;
        while(temp!=null){
            if(temp.data.rollNumber==rollNumber){
                System.out.println("Student found ");
                return ;
            }
            temp=temp.next;
        }
        System.out.println("Student not found");
    }

    void updateStudentGrades(char grade,int rollNumber){
        Node temp=head;
        while(temp!=null){
            if(temp.data.rollNumber==rollNumber){
                System.out.println("Updating student Grades ");
                temp.data.grade=grade;
                return ;
            }
            temp=temp.next;
        }
        System.out.println("Student not found");
    }
}

public class StudentRecodManagement {
    public static void main(String[] args) {

        SingleLinkedList list = new SingleLinkedList();

        Student student1 = new Student(101, "Alice", 'A', 20);
        Student student2 = new Student(102, "Bob", 'B', 21);
        Student student3 = new Student(103, "Charlie", 'C', 22);
        Student student4 = new Student(104, "David", 'A', 20);
        Student student5 = new Student(105, "Eve", 'B', 21);


        list.insertAtPosition(1, student2);
        list.insertAtPosition(0, student4);
        list.insertAtPosition(3, student5);

        list.insertAtHead(student1);
        list.insertAtTail(student3);

        list.printStudents();

        list.DeleteByRollnumber(200);

        list.printStudents();

        list.findStudent(1098);

        list.updateStudentGrades('F',101);

        list.printStudents();

    }
}
