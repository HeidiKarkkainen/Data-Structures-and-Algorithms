package oy.tol.tra;

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
      throw new LinkedListAllocationException("Not implemented yet!");
   }

   @Override
   public void add(int index, Element element) throws NullPointerException, LinkedListAllocationException, IndexOutOfBoundsException {
      throw new LinkedListAllocationException("Not implemented yet!");
   }

   @Override
   public boolean remove(Element element) throws NullPointerException {
      return false;
   }

   @Override
   public Element remove(int index) throws IndexOutOfBoundsException {
      return null;
   }

   @Override
   public Element get(int index) throws IndexOutOfBoundsException {
      return null;
   }

   @Override
   public int indexOf(Element element) {
      return -1;
   }

   @Override
   public int size() {
      return -1;
   }

   @Override
   public void reset() {
   }

   @Override
   public void reverse() {
      // TODO: implement this only when doing the task explained the TASK-2.md.
      // This method is not needed in doing the task in the README.md.
   }
   
}
