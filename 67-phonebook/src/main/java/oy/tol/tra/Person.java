package oy.tol.tra;

public class Person implements Comparable<Person> {
    private String phoneNumber;
    private String firstName;
    private String lastName;

    public Person(String phone) {
        phoneNumber = phone;
    }

    public Person(String phone, String firstName, String lastName) {
        phoneNumber = phone;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

    /**
     * TODO: Implement the method below to return a hash value. It can be based on the phone number
     * of the person alone.
     * 
     * If you implement hash based on other than phone number, do note the relationship between the
     * hashCode() and equals() that is assumed in Java.
     * @return Hash value of the person.
     */
    @Override
    // public int hashCode() {
    //     int hash = 7919;
    //     for (int i = 0; i < phoneNumber.length(); i++){
    //         hash = (7919 * hash + phoneNumber.charAt(i));
    //     }
    //     return hash;
    // }

    public int hashCode() {
        int hash = 37;
        
        for (int i = 0; i < phoneNumber.length(); i++){
            hash = (hash * 33) + phoneNumber.charAt(i);
        }
        return hash;
    }

    public int hashCode2() {
        int hash = 5381;
        
        for (int i = 0; i < phoneNumber.length(); i++){
            hash = ((hash << 5) + hash) + phoneNumber.charAt(i);
        }
        return hash;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Person) {
            return this.phoneNumber.equals(((Person)other).phoneNumber);
        }
        return false;
    }

    /**
     * Compares two persons, this and the other one.
     * <p>
     * In a phonebook, persons are identified by the phone number.
     * So if a person is the same or another, depends on if they have
     * the same phone number.
     * <p>
     * Return <0 if the phone number (as string) is smaller than the others.
     * Return 0 if the phone numbers are identical.
     * Return >0 if the other persons phone number is larger (as string).
     * Note: String class also implements <code>compareTo()</code> you can use here.
     * @returns Returns 0 if persons are the same otherwise depending on the number, <0 or >0.
     */
    @Override
    public int compareTo(Person other) {

          
        int l1 = phoneNumber.length();
        int l2 = other.getPhoneNumber().length();
        int lmin = Math.min(l1, l2);
  
        for (int i = 0; i < lmin; i++) {
            int str1_ch = (int)phoneNumber.charAt(i);
            int str2_ch = (int)other.getPhoneNumber().charAt(i);
  
            if (str1_ch != str2_ch) {
                return str1_ch - str2_ch;
            }
        }
  
        // Edge case for strings like
        // String 1="Geeks" and String 2="Geeksforgeeks"
        if (l1 != l2) {
            return l1 - l2;
        }
  
        // If none of the above conditions is true,
        // it implies both the strings are equal
        else {
            return 0;
        }

        //return phoneNumber.compareTo(other.phoneNumber);
    }
}
