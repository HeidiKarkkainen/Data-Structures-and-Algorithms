package oy.tol.tra;

public class FastPhoneBook implements PhoneBook {

    Person[] persons = new Person[200000];
    int collisionCount = 0;

    /**
     * @param person The person to add to the phone book.
     * @return True if managed to add the person, otherwise false.
     */
    @Override
    public boolean add(Person person) throws IllegalArgumentException {

    int i = 0;

    while (i < persons.length){
        int hashValue = person.hashCode();
        int hashValue2 = person.hashCode2();
        int index = ((hashValue + i * hashValue2) & 0x7fffffff) % persons.length;
        if (persons[index] == null){
            persons[index] = person;
            return true;
        }
        collisionCount++;
        i++;
    }
    return false;
}

    @Override
    public int size() {
        return persons.length;
    }

    /**
     * @param number The phone number to search from the phone book.
     * @return Return the Person object, or if not found null.
     */
    @Override
    public Person findPersonByPhone(String number) throws IllegalArgumentException {

        Person person = new Person(number);
        int i = 0;
        int hashValue = person.hashCode();
        int hashValue2 = person.hashCode2();
        int index = ((hashValue + i * hashValue2) & 0x7fffffff ) % persons.length;

        while (i < persons.length - 1 && persons[index] != null){
            if (persons[index].getPhoneNumber().equals(number)){
                return persons[index];
            }
            i++;
            index = ((hashValue + i * hashValue2) & 0x7fffffff ) % persons.length;
        }
        return null;
    }

	@Override
	public Person[] getPersons() {
		return null;
	}

    /**
     * Prints out the statistics of the phone book.
     */
    @Override
    public void printStatus() {
        System.out.println("The number of collisions: " + collisionCount);
    }
    
}
