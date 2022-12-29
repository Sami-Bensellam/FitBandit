package coms309.Replies;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import coms309.Users.User;
import coms309.Threads.Thread;

import javax.persistence.*;
import java.util.List;

/**
 *
 * @author Sami Bensellam
 *
 */


@Entity
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String txt;


    @ManyToOne
    @JsonIgnore
    private User user;


    @ManyToOne
    @JsonIgnore
    private Thread thread;

    public Reply(String txt, Thread thread,  User user) {
        this.txt = txt;
        this.user = user;
        this.thread = thread;

    }

    public Reply() {
    }

    // =============================== Getters and Setters for each field ================================== //

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getTxt() {
        return txt;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }






}
