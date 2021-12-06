package oy.tol.tra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class InvoiceInspector {

   /** List of invoices sent to customer. */
   Invoice[] invoices = null;
   /** List of payments received from customers. */
   Payment[] payments = null;
   /**
    * Based on invoices and payments, a list of new invoices to be sent to
    * customers. DO NOT use Java containers in your implementation, it is used ONLY here
    * to store the invoices to collect. Use plain Java arrays {@code Invoice[]} and {@code Payment[]} 
    */
   List<Invoice> toCollect = new ArrayList<>();

   /**
    * Reads the invoices and payments to memory from csv text files.
    * 
    * @param invoicesFile The file containing the invoice data.
    * @param paymentsFile The file containing the payments data.
    * @throws IOException If file thing goes wrong, there will be exception.
    */
   public void readInvoicesAndPayments(String invoicesFile, String paymentsFile) throws IOException {
      BufferedReader invoiceReader = new BufferedReader(new FileReader(invoicesFile));
      String line = null;
      line = invoiceReader.readLine();
      int itemCount = 0;
      if (null != line && line.length() > 0) {
         itemCount = Integer.parseInt(line);
         invoices = new Invoice[itemCount];
      } else {
         invoiceReader.close();
         throw new IOException("Could not read the invoice file");
      }
      itemCount = 0;
      while ((line = invoiceReader.readLine()) != null && line.length() > 0) {
         String[] items = line.split(",");
         invoices[itemCount++] = new Invoice(Integer.parseInt(items[0]), Integer.parseInt(items[1]));
      }
      invoiceReader.close();
      BufferedReader paymentsReader = new BufferedReader(new FileReader(paymentsFile));
      line = paymentsReader.readLine();
      itemCount = 0;
      if (null != line && line.length() > 0) {
         itemCount = Integer.parseInt(line);
         payments = new Payment[itemCount];
      } else {
         paymentsReader.close();
         throw new IOException("Could not read the invoice file");
      }
      itemCount = 0;
      while ((line = paymentsReader.readLine()) != null && line.length() > 0) {
         String[] items = line.split(",");
         payments[itemCount++] = new Payment(Integer.parseInt(items[0]), Integer.parseInt(items[1]));
      }
      paymentsReader.close();
   }

   /**
    * A naive simple implementation handling the creation of new invoices based on
    * old invoices and payments received from customers.
    * 
    * @throws IOException
    */
   public void handleInvoicesAndPaymentsSlow() throws IOException {
      for (int counter = 0; counter < invoices.length; counter++) {
         Invoice invoice = invoices[counter];
         boolean noPaymentForInvoiceFound = true;
         for (int paymentCounter = 0; paymentCounter < payments.length; paymentCounter++) {
            Payment payment = payments[paymentCounter];
            if (invoice.number.compareTo(payment.number) == 0) {
               noPaymentForInvoiceFound = false;
               if (invoice.sum.compareTo(payment.sum) > 0) {
                  toCollect.add(new Invoice(invoice.number, invoice.sum - payment.sum));
                  break;
               }
            }
         }
         if (noPaymentForInvoiceFound) {
            toCollect.add(invoice);
         }
      }
      int i = toCollect.size()-1;
      while (i > 0) {
         for (int index = i - 1; index >= 0; index--) {
            if (toCollect.get(i).getID() < toCollect.get(index).getID()) {
               Invoice tmp = toCollect.get(i);
               toCollect.set(i, toCollect.get(index));
               toCollect.set(index, tmp);
            }
         }
         i--;
      }
   }

   public void saveNewInvoices(String outputFile) throws IOException {
      BufferedWriter toCollectWriter = new BufferedWriter(new FileWriter(outputFile));
      for (Invoice invoice : toCollect) {
         toCollectWriter.write(invoice.number.toString() + "," + invoice.sum.toString());
         toCollectWriter.newLine();
      }
      toCollectWriter.close();
   }


   /**
    * 1. Both invoices and payments need to be sorted, so implement sorting with any method.
    * 2. When going through invoices, search for the corresponding payment using binary search, so implement that.
    * 3. If a payment was found, deduct from the invoice what was paid. If must still pay something, create invoice.
    * 4. If payment was not found, create a new invoice with the same invoice.
    * @throws IOException
    */
    public void handleInvoicesAndPaymentsFast() {

      heapsort(invoices, invoices.length);
      heapsort(payments, payments.length);

      for (int i = 0; i < invoices.length; i++) {
         Invoice invoice = invoices[i];
         boolean noPaymentForInvoiceFound = true;

         int match = binarySearch(invoice.getID(), payments, 0, payments.length - 1);

         if (match >= 0) {
            noPaymentForInvoiceFound = false;
            Payment payment = payments[match];
            if (invoice.sum.compareTo(payment.sum) > 0) {
               toCollect.add(new Invoice(invoice.number, invoice.sum - payment.sum));
            }
         }

         if (noPaymentForInvoiceFound) {
            toCollect.add(invoice);
         }
      }
   }

   public static int binarySearch(int aValue, Identifiable [] fromArray, int fromIndex, int toIndex) {

      int splicedIndex;

      if (fromIndex == toIndex){
         if (fromArray[fromIndex].getID() == aValue) {
            return fromIndex;
         } else {
            return -1;
         }   
      } else {
         splicedIndex = (fromIndex + toIndex) / 2;
         if (aValue <= fromArray[splicedIndex].getID()){
            return  binarySearch(aValue, fromArray, fromIndex, splicedIndex);
         } else {
            return binarySearch(aValue, fromArray, splicedIndex +1, toIndex);
         }
      }   
   }

   private void heapsort(Identifiable[] A, int length){

      heapify(A, length);
      int end = length - 1;
      while (end > 0) {
         swap(A, end, 0);
         end -=1;
         siftDown(A, 0, end); 
      }
   }

   private void heapify(Identifiable[] A, int count){
      int start = parent(count-1);
      while (start >= 0){
         siftDown(A, start, count - 1);
         start -=1; 
      }
   }

   private void siftDown(Identifiable[] A, int start, int end) {
      int root = start;
      while (leftChild(root)<= end) { 
         int child = leftChild(root);
         int swap = root;
         if (A[swap].getID() < A[child].getID()) {
            swap = child;
         }
         if (child + 1 <= end && A[swap].getID() < A[child + 1].getID()) 
            swap = child + 1;
         if (swap == root){  
            return;   
         } else {
            swap(A, root, swap);
            root = swap;
         }
      }   
   }

   private int parent(int i){
      return (int)Math.floor((i-1)/2);
   }

   private int leftChild(int i){
      return 2 * i + 1;
   }
 
   private int rightChild(int i){
      return 2 * i + 2;
   }

   private void swap(Identifiable[] A, int a, int b) {
      Identifiable temp = A[a];
      A[a] = A[b];
      A[b] = temp;
   }
 
}
