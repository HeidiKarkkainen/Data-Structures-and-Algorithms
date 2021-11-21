package oy.tol.tra;

import java.lang.reflect.Array;


public class QueueImplementation<E> implements QueueInterface<E> {
    
    private int capacity;
    private E[] itemArray;
    private int head = 0;
    private int tail = 0;
    private int counter = 0;

    @Override
    public void init(Class<E> elementClass, int size) throws QueueAllocationException {
        if (size < 2) {
            throw new QueueAllocationException("Queue size should be greater than 1");
        }
        try {
            capacity = size;
            head = 0;
            tail = 0;
            itemArray = (E []) Array.newInstance(elementClass, capacity);
        } catch (Exception e) {
            throw new QueueAllocationException(e.getMessage());
        }
    }

     /**
    * For querying the current capacity of the queue.
    @return The number of elements the queue can currently hold.
    */
    @Override
    public int capacity() {

        return capacity;
    }

     /**
    * Add an element to the queue.
    * @param element The element to add, must not be null.
    * @return True if was able to add the item to queue, otherwise false.
    * @throw QueueAllocationException If the reallocation for the queue failed in case queue needs reallocation.
    * @throw NullPointerException If the element is null.

    //Tuossa @return -kommentit kertovat että enqueue() palauttaa true tai false. 
    //Metodi on kuitenkin void, joten se ei palauta mitään. 
    //Virhetilanteessa (jos elementtiä ei voida lisätä jonoon) metodi falsen palauttamisen sijaan heittää
    //QueueAllocationException -poikkeuksen.
    */
    @Override
    public void enqueue(E element) throws QueueAllocationException, NullPointerException{
        
        if (element == null){
            throw new NullPointerException();
        }

        if (counter == capacity){
            int newCapacity = capacity * 2;
            E[] newItemArray;
         
            try {
                newItemArray = (E[]) Array.newInstance(itemArray[0].getClass(), newCapacity);
            } catch (Exception e){
                throw new QueueAllocationException("Unable to increase the queue size!");
            }

            int j = 0;
            
            for (int i = head; i < itemArray.length; i++){
                newItemArray[j] = itemArray[i];
                j++;
            }
            
            for (int i = 0; i == tail; i++){
                newItemArray[j] = itemArray[i];
                j++;
            }

            head = 0;
            tail = j;
            capacity = newCapacity;
            itemArray = newItemArray; 
        }

        if (tail >= capacity && head > 0){
            tail = 0;
        }

        itemArray[tail++] = element;  
        counter++;
    }

       /**
    * Removes an element from the queue.
    * @return The element from the head of the queue.
    * @throws QueueIsEmptyException If the queue is empty.
    */
    @Override
    public E dequeue() throws QueueIsEmptyException {
        
        if (empty()){
            throw new QueueIsEmptyException("Queue is empty");
        }

        E headElement = itemArray[head];
        itemArray[head] = null;
        head++;
        counter--;

        if (head >= capacity){
            head = 0;
        }

        return headElement;
    }


   /**
    * Returns the element at the head of the queue, not removing it from the queue.
    * @return The element in the head of the queue.
    * @throws QueueIsEmptyException If the queue is empty.
    */
    @Override
    public E element() throws QueueIsEmptyException{
        if (empty()){
            throw new QueueIsEmptyException("Queue is empty");
        }
        return itemArray[head];
    }

    /**
    * Resets the queue to empty state. State of the queue should be as if it was just created.
    */
    @Override
    public void reset(){

        for (int i = 0; i < capacity; i++){
            itemArray[i] = null;
        }
        counter = 0;
        head = 0;
        tail = 0;
    }

    /**
    * Can be used to check if the queue is empty.
    * @return True if the queue is empty, false otherwise.
    */
    @Override
    public boolean empty() {

        if (counter == 0) {
            return true;
        }
        return false;
    }


   /**
    * Returns the count of elements currently in the queue.
    * @return Count of elements in the queue.
    */
    @Override
    public int count() {

        return counter;
    }

    /**
    * Returns the queue as a string in the format "[1, 2, 3, 4, 5]", where 1 is the next
    * element to take out from the queue (head), and 5 was the element most recenty entered
    * into the queue (tail). If the queue is empty, returns "[]". Must not print the "empty"
    * slots in the queue, just the elements between head and tail, in that order.
    * Note that the internal array may at some point have the elements there like this (an
    * example with ints, array size is 10):
    *  0   1  2 3 4 5 6 7 8  9
    * [11,12,13,_,_,_,_,8,9,10]
    *           ^       ^
    *         tail     head
    * User has added numbers from 1 onwards and removed some (1...7) while also adding numbers from 8...13.
    * In the example above, this method should return "[8, 9, 10, 11, 12, 13]"
    @return The queue as a string.
    */
    @Override
    public String toString() {

        if (empty()){
            return "[]";
        }

        StringBuilder builder = new StringBuilder();

        int i = head;
        int count = counter;

        builder.append("[");

        while (count > 0) {
            builder.append(itemArray[i]);
            i++;
            count--;

            if (count > 0){
                builder.append(", ");
            }

            if (i >= itemArray.length) {
                i = 0;
            }
        }

        builder.append("]");
        return builder.toString();
    }
}
