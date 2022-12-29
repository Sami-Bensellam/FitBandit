package coms309.Replies;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import coms309.Threads.Thread;
import coms309.Threads.ThreadRepository;
import coms309.Users.User;
import coms309.Users.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller      // this is needed for this to be an endpoint to springboot
@ServerEndpoint(value = "/chat/{userID}/{threadID}")  // this is Websocket url
public class ReplySocket {

    // cannot autowire static directly (instead we do it by the below
    // method
    private static ReplyRepository replyRepo;
    private static UserRepository userRepo;
    private static ThreadRepository threadRepo;

    /*
     * Grabs the MessageRepository singleton from the Spring Application
     * Context.  This works because of the @Controller annotation on this
     * class and because the variable is declared as static.
     * There are other ways to set this. However, this approach is
     * easiest.
     */
    @Autowired
    public void setMessageRepository(ReplyRepository repo) {
        replyRepo = repo;  // we are setting the static variable
    }
    @Autowired
    public void setUserRepository(UserRepository repo) {
        userRepo = repo;  // we are setting the static variable
    }
    @Autowired
    public void setThreadRepository(ThreadRepository repo) {
        threadRepo = repo;  // we are setting the static variable
    }

    // Store all socket session and their corresponding username.
    private static Map<Session, String> sessionUsernameMap = new Hashtable<>();
    private static Map<String, Session> usernameSessionMap = new Hashtable<>();
    private static Map<String, String> userThreadMap = new Hashtable<>();




    private final Logger logger = LoggerFactory.getLogger(ReplySocket.class);

    @OnOpen
    public void onOpen(Session session, @PathParam("userID") String userID, @PathParam("threadID") String TID)
            throws IOException {

        logger.info("Entered into Open");

        // store connecting user information
        sessionUsernameMap.put(session, userID);
        usernameSessionMap.put(userID, session);
        userThreadMap.put(userID, TID);

        //Send chat history to the newly connected user
        sendMessageToPArticularUser(userID, getChatHistory(TID));

        // broadcast that new user joined
//        String message = "User:" + userID + " has Joined the Chat";
//        broadcast(message);
    }


    @OnMessage
    public void onMessage(Session session, String message) throws IOException {

        // Handle new messages
        logger.info("Entered into Message: Got Message:" + message);
        String userID = sessionUsernameMap.get(session);
        String TID = userThreadMap.get(userID);

        // Direct message to a user using the format "@userID <message>"

            // send the message to the sender and receiver
//            sendMessageToPArticularUser(destUsername, "[DM] " + userID + ": " + message);
//            sendMessageToPArticularUser(userID, "[DM] " + userID + ": " + message);


        Reply newreply = new Reply(message, threadRepo.findById(Integer.parseInt(TID)),userRepo.findById(Integer.parseInt(userID)));
        // Saving chat history to repository
        replyRepo.save(newreply);
        message = "USERNAME "+ userRepo.findById(Integer.parseInt(userID)).getUserName()+" "+message;
        broadcast(message, TID);

    }


    @OnClose
    public void onClose(Session session) throws IOException {
        logger.info("Entered into Close");

        // remove the user connection information
        String username = sessionUsernameMap.get(session);
        sessionUsernameMap.remove(session);
        usernameSessionMap.remove(username);
        userThreadMap.remove(username);
    }


    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
        logger.info("Entered into Error");
        throwable.printStackTrace();
    }


    private void sendMessageToPArticularUser(String username, String message) {
        try {
            usernameSessionMap.get(username).getBasicRemote().sendText(message);
        }
        catch (IOException e) {
            logger.info("Exception: " + e.getMessage().toString());
            e.printStackTrace();
        }
    }


    private void broadcast(String message,String ThreadID) {
        sessionUsernameMap.forEach((session, username) -> {
            try {
                if(ThreadID.equals(userThreadMap.get(username))){
                    session.getBasicRemote().sendText(message);
                }
            }
            catch (IOException e) {
                logger.info("Exception: " + e.getMessage().toString());
                e.printStackTrace();
            }

        });

    }


    // Gets the Chat history from the repository
    private String getChatHistory(String TID) {
        List<Reply> messages = replyRepo.findAll();

        // convert the list to a string
        StringBuilder sb = new StringBuilder();
        if(messages != null && messages.size() != 0) {
            for (Reply message : messages) {
                Thread thready = message.getThread();
                if(TID.equals(thready.getId() + "")){
                    String username = message.getUser().getUserName();
                    String replytxt = message.getTxt();
                    sb.append("USERNAME " + username + " " + replytxt+ "\n");
            }}
        }
        return sb.toString();
    }

} // end of Class
