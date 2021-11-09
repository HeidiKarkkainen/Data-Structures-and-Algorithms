package oy.tol.tra;
import java.util.Arrays;

/**
 * For finding the mode of a number from an array of integers.
 * 
 * Usage: Mode.Result foundMode = Mode.findMode(array);
 * 
 * Result contains the number most often found in the array,
 * as well as the count of the said number.
 */
public class Mode {

   public static class Result {
      public int number = 0;
      public int count = 0;
   }

   private Mode() {
      // Empty
   }

   /**
    * Finds the mode of the specified array, -1 if one could not be found. Mode
    * means the number most often found in the array.
    * 
    * @return The Result, containing the mode number and the count how many time it was in the array.
    */
   public static Result findMode(int [] array) {
      // TODO: implement this to find the mode of the integer array.

      Arrays.sort(array);
      Result result = new Result();
      result.number = -1;
      result.count = -1;
      int mode = array[0];
      int modeLength = 1;
      int currentLength = 1;

      for (int i = 1; i < array.length; i++){
         
         if (array[i-1] == array[i]){
            currentLength += 1;
            if (modeLength < currentLength) {
               mode = array[i];
               modeLength = currentLength;
            }
         } else {
            currentLength = 1;
         }
      }
      result.number = mode;
      result.count = modeLength;
      return result;
   }
}
