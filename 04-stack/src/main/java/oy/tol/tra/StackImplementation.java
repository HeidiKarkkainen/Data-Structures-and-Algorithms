package oy.tol.tra;

import java.lang.reflect.Array;

/**
 * An implementation of the StackInterface.
 * <p>
 * TODO: Students, implement this so that the tests pass.
 */
public class StackImplementation<E> implements StackInterface<E> {

   private E[] itemArray;
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
         itemArray = (E[]) Array.newInstance(elementClass, capacity);
      } catch (Exception e) {
         throw new StackAllocationException(e.getMessage());
      }
   }

   @Override
   public int capacity() {     
      return capacity;
   }

   @Override
   public void push(E element) throws StackAllocationException, NullPointerException {
         
      if (element == null){
         throw new NullPointerException();
      }
      
      if (currentIndex == itemArray.length-1){
         int newCapacity = capacity * 2;
         E[] newItemArray;
         
         try {
            newItemArray = (E[]) Array.newInstance(itemArray[0].getClass(), newCapacity);
         } catch (Exception e){
            throw new StackAllocationException("Unable to increase the stack size!");
         }

         for (int i = 0; i < itemArray.length; i++){
               newItemArray[i] = itemArray[i];
         }
          
         capacity = newCapacity;
         itemArray = newItemArray; 
      }

      currentIndex += 1;
      itemArray[currentIndex] = element;     
   }

   @Override
   public E pop() throws StackIsEmptyException {
      if (currentIndex == -1) {
         throw new StackIsEmptyException("Stack is empty!");
      }
      currentIndex--;
      E temp = itemArray[currentIndex + 1];
      itemArray[currentIndex + 1] = null;
      return temp;
   }

   @Override
   public E peek() throws StackIsEmptyException {
      if (currentIndex == -1) {
         throw new StackIsEmptyException("Stack is empty!");
      }
      return itemArray[currentIndex];
   }

   @Override
   public int count() {   
      return currentIndex + 1;
   }

   @Override
   public void reset() {
      currentIndex = -1;   
   }

   @Override
   public boolean empty() {     
      if (currentIndex == -1) {
         return true;
      }
      return false;
   }
}
