package coms309.DietPlans;

import coms309.Users.User;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Sami Bensellam
 *
 */

@Entity
public class DietPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int calories;
    private int protein;
    private int carbs;
    private int fat;
    private int waterIntake;
    private int userID;
    /*
    * One to one relationship with user where we are going to be allocating one diet plan based on their preferences.
    * We can change the recommended diet plan if they change their preferences through the controller
    * */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public DietPlan(int calories, int protein, int carbs, int fat, int waterIntake, User user) {
        this.calories = calories;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
        this.waterIntake = waterIntake;
        this.user = user;
        this.userID = user.getId();

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public DietPlan(){

    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public int getCalories() {
        return calories;
    }

    public int setCalories(int calories) {
        this.calories = calories;
        return calories;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getWaterIntake() {
        return waterIntake;
    }

    public void setWaterIntake(int waterIntake) {
        this.waterIntake = waterIntake;
    }
}
