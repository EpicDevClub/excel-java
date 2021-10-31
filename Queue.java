import java.util.*;

class Queue { 
    private static int start, end, capacity; 
    private static int queue[]; 
   
    Queue(int size) { 
        start = end = 0; 
        capacity = size; 
        queue = new int[capacity]; 
    } 
   
    // insert an element into the queue
    static void queueEnqueue(int item)  { 
        // check if the queue is full
        if (capacity == end) { 
             throw new ArrayOutOfBoundsException("the size of the queue is full");
            return; 
        } 
   
        // insert element at the end 
        else { 
            queue[end] = item; 
            end++; 
        } 
        return; 
    } 
   
    //remove an element from the queue
    static void queueDequeue()  { 
        // check if queue is empty 
        if (start == end) { 
         	throw new EmptyQueueException();
            return; 
        } 
   
        // shift elements to the right by one place uptil end 
        else { 
            for (int i = 0; i < end - 1; i++) { 
                queue[i] = queue[i + 1]; 
            } 
   
       
      // set queue[end] to 0
            if (end < capacity) 
                queue[end] = 0; 
   
            // decrement end 
            end--; 
        } 
        return; 
    } 
   
    // print queue elements 
    static void queueDisplay() 
    { 
        int i; 
        if (start == end) { 
            System.out.printf("Queue is Empty\n"); 
            return; 
        } 
   
        // traverse start to end and print elements 
        for (i = start; i < end; i++) { 
            System.out.printf(" %d = ", queue[i]); 
        } 
        return; 
    } 
   
    // print start of queue 
    static void queuestart() 
    { 
        if (start == end) { 
            System.out.printf("Queue is Empty\n"); 
            return; 
        } 
        System.out.printf("\nstart Element of the queue: %d", queue[start]); 
        return; 
    } 
} 
 
public class Main {
    public static void main(String[] args) { 
        // Create a queue of capacity 4 
        Queue q = new Queue(4); 
   
        System.out.println("Initial Queue:");
       // print Queue elements 
        q.queueDisplay(); 
   
        // inserting elements in the queue 
        q.queueEnqueue(10); 
        q.queueEnqueue(30); 
        q.queueEnqueue(50); 
        q.queueEnqueue(70); 
   
        // print Queue elements 
        System.out.println("Queue after Enqueue Operation:");
        q.queueDisplay(); 
   
        // print start of the queue 
        q.queuestart(); 
         
        // insert element in the queue 
        q.queueEnqueue(90); 
   
        // print Queue elements 
        q.queueDisplay(); 
   
        q.queueDequeue(); 
        q.queueDequeue(); 
        System.out.printf("\nQueue after two dequeue operations:"); 
   
        // print Queue elements 
        q.queueDisplay(); 
   
        // print start of the queue 
        q.queuestart(); 
    } 
}