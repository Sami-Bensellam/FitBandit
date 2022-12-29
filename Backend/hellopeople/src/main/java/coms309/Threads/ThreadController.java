package coms309.Threads;

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
public class ThreadController {

    @Autowired
    ThreadRepository threadRepository;
    @Autowired
    UserRepository userRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/threads")
    List<Thread> getAllThreads(){
        return threadRepository.findAll();
    }

    @GetMapping(path = "/threads/{id}")
    Thread getThreadById(@PathVariable int id){
        return threadRepository.findById(id);
    }

    @PostMapping(path = "/threads")
    String createThread(@RequestBody Thread thread){
        if (thread == null)
            return failure;
        threadRepository.save(thread);
        return success;
    }

    @PostMapping(path = "/threads/{title}/{txt}/{UID}")
    Thread createNThread(@PathVariable String title, @PathVariable String txt, @PathVariable int UID){
        User user = userRepository.findById(UID);
        Thread newthread = new Thread(title, txt, user);
        user.addThread(newthread);
        threadRepository.save(newthread);
        userRepository.save(user);
        return newthread;
    }


    @PutMapping(path = "/thread/{id}")
    Thread updateThread(@PathVariable int id, @RequestBody Thread request){
        Thread thread = threadRepository.findById(id);
        if(thread == null)
            return null;
        threadRepository.save(request);
        return threadRepository.findById(id);
    }

    @DeleteMapping(path = "/thread/{id}")
    String deleteThread(@PathVariable int id) {
        threadRepository.deleteById(id);
        return success;
    }
}
