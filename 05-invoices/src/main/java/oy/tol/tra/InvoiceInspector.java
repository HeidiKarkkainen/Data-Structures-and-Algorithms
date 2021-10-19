package oy.tol.tra;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    * TODO: Implement this method so that the time complexity of creating new
    * invoices to clients is significantly faster than the naive
    * handleInvoicesAndPaymentsSlow().
    * How to do this:
    * 1. Both invoices and payments need to be sorted, so implement sorting with any method.
    * 2. When going through invoices, search for the corresponding payment using binary search, so implement that.
    * 3. If a payment was found, deduct from the invoice what was paid. If must still pay something, create invoice.
    * 4. If payment was not found, create a new invoice with the same invoice.
    * @throws IOException
    */
   public void handleInvoicesAndPaymentsFast() {

   }

}
