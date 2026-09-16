/**
 * Author: Beck Bledsoe
 * Version: 09/16/2026
 * 
 */

package patient_intake;

/**
 * Summary: This class is the parent class for patients and includes firstName, lastName, and age data.
 * Person
 */
public class Person {
    protected String firstName = "";
    protected String lastName = "";
    protected int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName; //Stores the shared first name, last name, and age.
        this.lastName = lastName;
        this.age = age;
    }
}