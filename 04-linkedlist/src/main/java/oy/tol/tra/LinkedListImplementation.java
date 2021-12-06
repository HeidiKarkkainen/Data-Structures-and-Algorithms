package oy.tol.tra;

import javax.lang.model.element.Element;

public class LinkedListImplementation<Element> implements LinkedListInterface<Element> {

   private class Node<E> {
      Node(E data) {
         element = data;
         next = null;
      }
      E element;
      Node<E> next;
   }

   private Node<Element> head = null;
   private int size = 0;

   @Override
   public void add(Element element) throws NullPointerException, LinkedListAllocationException {
      if (element == null){
         throw new NullPointerException();
      }

      Node<Element> node;
      Node<Element> current;
      
      try {
         node = new Node<Element>(element);
      } catch(Exception e) {
         throw new LinkedListAllocationException("Out of memory!");
      }           

      if (head == null) {
         head = node;
         head.next = null;
      } else {
         current = head;
         while(current.next != null){
            current = current.next;
         }
         current.next = node;
         node.next = null;
      }

      size++;
   }

      /**
    * Add an element to the specified index in the list.
    * @param index The index where to add the element, must be 0...count().
    * @param element The element to add, must not be null.
    * @throws NullPointerException If the parameter element is null.
    * @throws LinkedListAllocationException If failed to allocate a new list element.
    * @throws IndexOutOfBoundsException If the specified index to the list is out of bounds.
    */
   @Override
   public void add(int index, Element element) throws NullPointerException, LinkedListAllocationException, IndexOutOfBoundsException {
      
      if (element == null){
         throw new NullPointerException();
      }

      if (index < 0 || index >= size()) {
         throw new IndexOutOfBoundsException();
      }

      Node<Element> node;
      Node<Element> current;
      int counter = 0; 
      
      try {
         node = new Node<Element>(element);
      } catch(Exception e) {
         throw new LinkedListAllocationException("Out of memory!");
      } 
      
      if (head == null) {
         head = node;
         head.next = null;
      } else {
         current = head;
         while(counter != index){
            current = current.next;
            counter++;
         }
         node.next = current;
         current.next = node;        
      }

      size++;
   }

   @Override
   public boolean remove(Element element) throws NullPointerException {

      if (element == null){
         throw new NullPointerException();
      }

      Node<Element> node;
      Node<Element> current;
      Node<Element> previous = null;

      if (head == null){
         return false;
      }

      current = head;
      
      while((current != null) && (current.element != element)){
         previous = current;
         current = current.next; 
      }

      if (current == null){
         return false;
      }

      node = current;

      if (previous != null){
         previous.next = node.next;
      } else {
         head = node.next;
      } 

      size--;

      return true;
   }

   @Override
   public Element remove(int index) throws IndexOutOfBoundsException {

      if (index < 0 || index >= size()) {
         throw new IndexOutOfBoundsException();
      }

      Node<Element> node;
      Node<Element> current;
      Node<Element> previous = null;

      if (head == null){
         return null;
      } else {
         int counter = 0;
         current = head;
         while (current != null && counter != index){
            previous = current;
            current = current.next;
            counter++;
         }
      }

      if (current == null){
         return null;
      }

      node = current;

      if (previous != null){
         previous.next = node.next;
      } else {
         head = node.next;
      } 

      size--;

      return node.element;
   }

   @Override
   public Element get(int index) throws IndexOutOfBoundsException {

      if (index < 0 || index >= size()) {
         throw new IndexOutOfBoundsException();
      }
      
      Node<Element> current;

      if (head == null){
         return null;
      } else {
         int counter = 0;
         current = head;
         while (current != null && counter != index){
            current = current.next;
            counter++;
         }
      }

      return current.element;
   }

   @Override
   public int indexOf(Element element) {

      if (element == null){
         throw new NullPointerException();
      }

      Node<Element> current;
      int counter = 0;

      if (head == null){
         return -1;
      }

      current = head;
      
      while((current != null) && (current.element != element)){
         current = current.next; 
         counter++;
      }  

      if (current == null){
         return -1;
      }

      return counter;
   }

   @Override
   public int size() {

      return size;
   }

   @Override
   public void reset() {

      head = null;
      size = 0;
   }

   @Override
   public void reverse() {
      // TODO: implement this only when doing the task explained the TASK-2.md.
      // This method is not needed in doing the task in the README.md.
   }
   
}
