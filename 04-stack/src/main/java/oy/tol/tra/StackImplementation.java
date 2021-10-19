package oy.tol.tra;

import java.lang.reflect.Array;

/**
 * An implementation of the StackInterface.
 * <p>
 * TODO: Students, implement this so that the tests pass.
 */
public class StackImplementation<E> implements StackInterface<E> {

   private E [] itemArray;
   private int capacity;
   private int currentIndex = -1;

   @Override
   public void init(Class<E> elementClass, int size) throws StackAllocationException {
      if (size < 2) {
         throw new StackAllocationException("Stack size should be greater than 1");
      }
      try {
         capacity = size;
         currentIndex = -1;
         itemArray = (E []) Array.newInstance(elementClass, capacity);   
      } catch (Exception e) {
         throw new StackAllocationException(e.getMessage());
      }
   }

   @Override
   public int capacity() {
      // TODO: Implement this
      return -1;
   }

   @Override
   public void push(E element) throws StackAllocationException, NullPointerException {
      // TODO: Implement this
      throw new StackAllocationException("Missing implementation!");
   }

   @Override
   public E pop() throws StackIsEmptyException {
      // TODO: Implement this
      throw new StackIsEmptyException("Missing implementation!");
   }

   @Override
   public E peek() throws StackIsEmptyException {
      // TODO: Implement this
      throw new StackIsEmptyException("Missing implementation!");
   }

   @Override
   public int count() {
      // TODO: Implement this
      return -1;
   }

   @Override
   public void reset() {
      // TODO: Implement this
   }

   @Override
   public boolean empty() {
      // TODO: Implement this
      return false;
   }

}
