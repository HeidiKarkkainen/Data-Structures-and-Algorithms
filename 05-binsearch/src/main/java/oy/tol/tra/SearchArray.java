package oy.tol.tra;

/**
 * For searching a number from an array of integers.
 * 
 * @author Antti Juustila
 * @version 1.0
 */
public class SearchArray {

   private SearchArray() {
      // Empty
   }

   /**
    * Finds a number from the specified array using linear search, -1 if one could not be found.
    * @param aValue The value to find.
    * @param fromArray The array to search.
    * @param fromIndex The index to start searching from.
    * @param toIndex The index to search to in the array.
    * @return The index of the number in the array, -1 if not found.
    */
   public static int linearSearch(int aValue, int [] fromArray, int fromIndex, int toIndex) {
      for (int index = fromIndex; index < toIndex; index++) {
         if (fromArray[index] == aValue) {
            return index;
         }
      }
      return -1;
   }

   /**
    * Finds a number from the specified array using binary search, -1 if one could not be found.
    * Note that binary search works only with sorted arrays.
    * <p>
    * TODO: implement the binary search algorithm.
    * @param aValue The value to find.
    * @param fromArray The array to search.
    * @param fromIndex The index to start searching from.
    * @param toIndex The index to search to in the array.
    * @return The index of the number in the array, -1 if not found.
    */
    public static int binarySearch(int aValue, int [] fromArray, int fromIndex, int toIndex) {
       throw new RuntimeException("Not yet implemented");
   }
}
