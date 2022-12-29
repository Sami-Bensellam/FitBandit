package coms309.Replies;

import coms309.Threads.Thread;
import coms309.Threads.ThreadRepository;
import coms309.Users.User;
import coms309.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author Sami Bensellam
 *
 */

@RestController
public class ReplyController {

    @Autowired
    ReplyRepository replyRepository;
    @Autowired
    ThreadRepository threadRepository;
    @Autowired
    UserRepository userRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/replies")
    List<Reply> getAllReplies(){
        return replyRepository.findAll();
    }

    @GetMapping(path = "/replies/{id}")
    Reply getReplyById(@PathVariable int id){
        return replyRepository.findById(id);
    }

    @PostMapping(path = "/replies")
    String createReply(@RequestBody Reply reply){
        if (reply == null)
            return failure;
        replyRepository.save(reply);
        return success;
    }


    @PostMapping(path = "/replies/{txt}/{threadID}/{UID}")
    Reply createNReply(@PathVariable String txt, @PathVariable int threadID, @PathVariable int UID){
        User user = userRepository.findById(UID);
        Thread thread = threadRepository.findById(threadID);
        Reply reply = new Reply(txt, thread, user);
        replyRepository.save(reply);
        user.addReply(reply);
        thread.addReplies(reply);
        threadRepository.save(thread);
        userRepository.save(user);
        return reply;
    }

    @PutMapping(path = "/replies/{id}")
    Reply updateReply(@PathVariable int id, @RequestBody Reply request){
        Reply day = replyRepository.findById(id);
        if(day == null)
            return null;
        replyRepository.save(request);
        return replyRepository.findById(id);
    }

    @DeleteMapping(path = "/replies/{id}")
    String deleteReply(@PathVariable int id) {
        replyRepository.deleteById(id);
        return success;
    }
}
