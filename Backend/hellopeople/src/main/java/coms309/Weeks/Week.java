package coms309.Weeks;

import com.fasterxml.jackson.annotation.JsonIgnore;
import coms309.Users.User;

import javax.persistence.*;
import java.util.List;

/**
 *
 * @author Sami Bensellam
 *
 */


@Entity
public class Week {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int Weight;


    @ManyToOne
    @JsonIgnore
    private User user;


//    @OneToMany
//    private List<Diet> diet;

    public Week(int Weight, User user) {
        this.Weight = Weight;
        this.user = user;
    }

    public Week() {
    }

    // =============================== Getters and Setters for each field ================================== //

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getWeight(){
        return Weight;
    }

    public void setWeight(int Weight){
        this.Weight = Weight;
    }

//    public List<Diet> getDiet(){
//        return diet;
//    }

    public void addDiet(int id){
        this.id = id;
    }
    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }






}
