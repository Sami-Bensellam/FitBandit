package coms309.people;


/**
 * Provides the Definition/Structure for the people row
 *
 * @author Sami Bensellam
 */

public class Person {

    private String firstName;

    private String lastName;

    private String bodyweight;

    private String gender;

    public Person(){
        
    }

    public Person(String firstName, String lastName, String address, String telephone){
        this.firstName = firstName;
        this.lastName = lastName;
        this.bodyweight = address;
        this.gender = telephone;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBodyweight() {
        return this.bodyweight;
    }

    public void setBodyweight(String bodyweight) {
        this.bodyweight = bodyweight;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return firstName + " " 
               + lastName + " "
               + bodyweight + " "
               + gender;
    }
}
