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

   @Override
   public void add(int index, Element element) throws NullPointerException, LinkedListAllocationException, IndexOutOfBoundsException {
      
      if (index < 0 || index > size()) {
         throw new IndexOutOfBoundsException();
      }

      if (element == null){
         throw new NullPointerException();
      }


      Node<Element> node;
      Node<Element> current = head;
      Node<Element> previous; 
      
      try {
         node = new Node<Element>(element);
      } catch(Exception e) {
         throw new LinkedListAllocationException("Out of memory!");
      } 

      if (index == 0){
         node.next = head;
         head = node;
      } else {
         current = head.next;
         previous = head;
         // We have already passed element at index 0
         for (int i = 1; i < index; i++){
            previous = current;
            current = current.next;
         }
         node.next = previous.next;
         previous.next = node;
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

      Node<Element> current = head;
      int counter = 0;

      while (current != null){
         if (current.element.equals(element)){
            return counter;
         }
         current = current.next;
         counter++;
      }
      return -1;

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

      Node<Element> previous = null;
      Node<Element> current = head; 
      Node<Element> next = null;
      
      while (current != null){
         next = current.next; // Hold on to next node
         current.next = previous; // Reverse current's next
         previous = current; // Move previous forward to current
         current = next; // Move current forward to next
      }

      head = previous;
   }
   
}
