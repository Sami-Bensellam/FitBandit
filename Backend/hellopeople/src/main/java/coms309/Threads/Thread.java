package coms309.Threads;

import com.fasterxml.jackson.annotation.JsonIgnore;
import coms309.Replies.Reply;
import coms309.Users.User;

import javax.persistence.*;
import java.util.List;

/**
 *
 * @author Sami Bensellam
 *
 */


@Entity
public class Thread {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String text;


    @ManyToOne
    @JsonIgnore
    private User user;



    @OneToMany
    private List<Reply> replies;

    public Thread(String title,String text, User user) {
        this.title = title;
        this.text = text;
        this.user = user;

    }

    public Thread() {
    }

    // =============================== Getters and Setters for each field ================================== //

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public void addReplies(Reply reply) {
        this.replies.add(reply);
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }






}
