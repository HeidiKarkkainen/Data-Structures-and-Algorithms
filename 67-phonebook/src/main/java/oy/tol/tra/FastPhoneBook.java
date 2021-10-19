package oy.tol.tra;

public class FastPhoneBook implements PhoneBook {

    /**
     * TODO: Implement the add depending on your selected data structure; either hash table or BST.
     * In both cases, you need to calculate the hash code in the Person class.
     * 
     * @param person The person to add to the phone book.
     * @return True if managed to add the person, otherwise false.
     */
    @Override
    public boolean add(Person person) throws IllegalArgumentException {
        throw new RuntimeException("Not implemented yet!");
    }

    @Override
    public int size() {
        return -1;
    }

    /**
     * TODO: Implement the search function.
     * Implementation depends on which data structure you use, either hash table or BST.
     *
     * @param number The phone number to search from the phone book.
     * @return Return the Person object, or if not found null.
     */
    @Override
    public Person findPersonByPhone(String number) throws IllegalArgumentException {
        throw new RuntimeException("Not implemented yet!");
    }

	@Override
	public Person[] getPersons() {
        // Students: You do not need to implemented this here.
        // Just let it return null.
		return null;
	}

    /**
     * Prints out the statistics of the phone book.
     * Here you should print out member variable information which tell something about
     * your implementation.
     * <p>
     * For example, if you implement this using a hash table, update member variables of the class
     * (int counters) in add(Person) whenever a collision happen. Then print this counter value here. 
     * You will then see if you have too many collisions. It will tell you that your hash function
     * is not good. In the teacher's implementation, there were 864 collisions and when using 
     * linear probing to handle the collision, max number of steps to find a free slot
     * in the table was 2. This is not too bad.
     */
    @Override
    public void printStatus() {
        System.out.println("TODO: IMPLEMENT THIS");
    }
    
}
