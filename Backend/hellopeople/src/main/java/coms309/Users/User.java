package coms309.Users;

import coms309.Replies.Reply;
import coms309.Threads.Thread;
import coms309.Workouts.Workout;
import coms309.DietPlans.DietPlan;
import coms309.Weeks.Week;
import coms309.Days.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Sami Bensellam
 *
 */

@Entity
public class User {

    /*
     * The annotation @ID marks the field below as the primary key for the table created by springboot
     * The @GeneratedValue generates a value if not already present, The strategy in this case is to start from 1 and increment for each table
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String password;
    private String emailId;
    private int Weight;

    private int Height;
    private int gain;
    private int muscle;

    private int age;
    private boolean Gender;

    private boolean isAdmin;

    /*
     * @OneToOne creates a relation between the current entity/table(Laptop) with the entity/table defined below it(User)
     * cascade is responsible propagating all changes, even to children of the class Eg: changes made to laptop within a user object will be reflected
     * in the database (more info : https://www.baeldung.com/jpa-cascade-types)
     * @JoinColumn defines the ownership of the foreign key i.e. the user table will have a field called laptop_id
     */

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dietPlan_id")
    private DietPlan dietPlan;

    @ManyToMany
    private List<Workout> workouts;

    @OneToMany
    private List<Week> weeks;

    @OneToMany
    private List<Day> days;

    @OneToMany
    private List<Thread> threads;

    @OneToMany
    private List<Reply> replies;


    public User(String firstName, String lastName, String emailId, boolean isAdmin) {
        this.userName = firstName;
        this.password = lastName;
        this.emailId = emailId;
        this.isAdmin = true;
        workouts = new ArrayList<Workout>();
        weeks = new ArrayList<Week>();
        days = new ArrayList<Day>();
        threads = new ArrayList<Thread>();
        replies = new ArrayList<Reply>();

    }

    public User() {
    }

    // =============================== Getters and Setters for each field ================================== //

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }


    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getEmailId(){
        return emailId;
    }

    public void setEmailId(String emailId){
        this.emailId = emailId;
    }

    public boolean getAdmin(){
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin){
        this.isAdmin = isAdmin;
    }

    public void addWorkout(Workout workout){
        this.workouts.add(workout);
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }


    public boolean isAdmin() {
        return isAdmin;
    }

    public List<Week> getWeeks() {
        return weeks;
    }

    public void setWeeks(List<Week> weeks) {
        this.weeks = weeks;
    }


    public void setWorkouts(List<Workout> workouts) {
        this.workouts = workouts;
    }

    public List<Workout> getWorkouts(){
        return workouts;
    }

    public int getWeight(){
        return Weight;
    }

    public void setWeight(int Weight){
        this.Weight = Weight;
    }

    public void setDietPlan(DietPlan dietPlan) {
        this.dietPlan = dietPlan;
    }

    public DietPlan getDietPlan() {
        return dietPlan;
    }

    public int getHeight(){
        return Height;
    }

    public void setHeight(int Height){
        this.Height = Height;
    }
    public int getGain(){
        return gain;
    }

    public void setGain(int gain){
        this.gain = gain;
    }
    public int getMuscle(){
        return muscle;
    }

    public void setMuscle(int muscle){
        this.muscle = muscle;
    }

    public boolean getGender(){
        return Gender;
    }

    public void setGender(boolean Gender){
        this.Gender = Gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public List<Thread> getThreads() {
        return threads;
    }
    public void addThread(Thread thread){
        threads.add(thread);
    }

    public void addReply(Reply reply) {
        replies.add(reply);
    }
}
