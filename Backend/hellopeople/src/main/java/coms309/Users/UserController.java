package coms309.Users;


import java.util.List;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author Sami Bensellam
 *
 */

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;


    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";
    @ApiOperation(value = "returns all of the users in the database ", response = Iterable.class)
    /**
     * returns all of the users in the database
     * @return List of the Users
     */
    @GetMapping(path = "/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }
    @ApiOperation(value = "returns user with specific id ", response = Iterable.class)
    /**
     * returns user with specific id
     * @param id
     * @return User
     */
    @GetMapping(path = "/users/{id}")
    User getUserById( @PathVariable int id){
        return userRepository.findById(id);
    }
    @ApiOperation(value = "returns username of the specific user by Id ", response = Iterable.class)
    /**
     * returns username of the specific user by Id
     * @param id
     * @return String username
     */
    @GetMapping(path = "/users/{id}/username")
    String getUsernamebyId( @PathVariable int id){
        return userRepository.findById(id).getUserName();
    }
    @ApiOperation(value = "creates empty user ", response = Iterable.class)
    /**
     * creates empty user
     * @param user
     * @return success
     */
    @PostMapping(path = "/users")
    String createUser(@RequestBody User user){
        if (user == null)
            return failure;
        userRepository.save(user);
        return success;
    }
//    @ApiOperation(value = "creates new user with username and password ", response = Iterable.class)
//    /**
//     * creates new user with username and password
//     * @param username
//     * @param password
//     * @return User
//     */
//    @PostMapping("users/register/{username}/{password}")
//    User createNUser(@PathVariable String username, @PathVariable String password){
//        User newUser = new User(username,password,"",false);
//        userRepository.save(newUser);
//        return newUser;
//    }
    @ApiOperation(value = "creates a user with all the needed attributes ", response = Iterable.class)
    /**
     * creates a user with all the needed attributes
     * @param username
     * @param password
     * @param weight
     * @param height
     * @param age
     * @param gain
     * @param gender
     * @return User
     */
    @PostMapping("users/register/{username}/{password}/{email}/{weight}/{height}/{age}/{gain}/{gender}")
    User createNFUser(@PathVariable String username, @PathVariable String password, @PathVariable String email, @PathVariable int weight, @PathVariable int height, @PathVariable int age, @PathVariable int gain, @PathVariable boolean gender){
        User newUser = new User(username,password,"",false);
        newUser.setWeight(weight);
        newUser.setHeight(height);
        newUser.setEmailId(email);
        newUser.setAge(age);
        newUser.setGain(gain);
        newUser.setGender(gender);
        userRepository.save(newUser);
        return newUser;
    }
    @ApiOperation(value = "changes user with the specific id ", response = Iterable.class)
    /**
     * changes user with the specific id
     * @param id
     * @param request
     * @return User
     */
    @PutMapping("/users/{id}")
    User updateUser(@PathVariable int id, @RequestBody User request){
        User user = userRepository.findById(id);
        if(user == null)
            return null;
        userRepository.save(request);
        return userRepository.findById(id);
    }


    @DeleteMapping(path = "/users/{id}")
    String deleteWorkout(@PathVariable int id){
        userRepository.deleteById(id);
        return success;
    }

}

