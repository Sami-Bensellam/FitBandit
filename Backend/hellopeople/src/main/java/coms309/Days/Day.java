package coms309.Days;

import com.fasterxml.jackson.annotation.JsonIgnore;
import coms309.Users.User;
import coms309.Workouts.Workout;

import javax.persistence.*;
import java.util.List;

/**
 *
 * @author Sami Bensellam
 *
 */


@Entity
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int caloriesConsumed;



    @ManyToOne
    @JsonIgnore
    private User user;



    @ManyToMany
    private List<Workout> workouts;

    public Day(int caloriesConsumed, User user) {
        this.caloriesConsumed = caloriesConsumed;
        this.user = user;

    }

    public Day() {
    }

    // =============================== Getters and Setters for each field ================================== //

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getCaloriesConsumed(){
        return caloriesConsumed;
    }

    public void setCaloriesConsumed(int caloriesConsumed) {
        this.caloriesConsumed = caloriesConsumed;
    }

    public void addWorkouts(Workout workout) {
        this.workouts.add(workout);
    }

    public List<Workout> getWorkouts(){
        return workouts;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }






}
