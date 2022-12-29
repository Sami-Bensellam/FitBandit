package coms309.Workouts;

import coms309.Days.Day;
import coms309.Users.User;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String wName;
    private String wType;
    private String weight;
    private int caloriesB;

    /*
     * @OneToOne creates a relation between the current entity/table(Laptop) with the entity/table defined below it(User)
     * cascade is responsible propagating all changes, even to children of the class Eg: changes made to laptop within a user object will be reflected
     * in the database (more info : https://www.baeldung.com/jpa-cascade-types)
     * @JoinColumn defines the ownership of the foreign key i.e. the user table will have a field called laptop_id
     */
    @ManyToMany
    @JsonIgnore
    private List<User> user;

    @ManyToMany
    private List<Day> days;

    public Workout(String wName, int caloriesB, String wType) {
        this.wName = wName;
        this.caloriesB = caloriesB;
        this.wType = wType;
        user = new ArrayList<>();
        days = new ArrayList<>();

    }

    public Workout() {
    }

    public String getwName(){
        return this.wName;
    }

    public int getCaloriesB() {
        return caloriesB;
    }

    public void setCaloriesB(int caloriesB) {
        this.caloriesB = caloriesB;
    }

    public void setwName(String wName){
        this.wName = wName;
    }

    public String getWeight(){
        return this.weight;
    }
    public void setWeight(String weight){
        this.weight = weight;
    }
    public String getwType(){
        return this.wType;
    }
    public void setwType(String wType){
        this.wType = wType;
    }

    public List<User> getUsers() {
        return user;
    }

    public List<Day> getDays() {
        return days;
    }


    public void setUser(List<User> user) {
        this.user = user;
    }

    public List<User> getUser() {
        return user;
    }

    public void addUser(User user) {
        this.user.add(user) ;
    }
}
