package coms309.Workouts;

import java.util.List;

import coms309.Users.UserRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import coms309.Users.User;

/**
 *
 * @author Sami Bensellam
 *
 */

@RestController
//@RequestMapping("/api/v1")
public class WorkoutController {
    @Autowired
    WorkoutRepository workoutRepository;

    @Autowired
    UserRepository userRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";
    @ApiOperation(value = "returns all the workouts ", response = Iterable.class)
    /**
     * returns all the workouts
     * @return workout List
     */
    @GetMapping(path = "/workouts")
    List<Workout> getAllWorkouts(){

        return workoutRepository.findAll();
    }
    @ApiOperation(value = "returns workout with a specific id ", response = Iterable.class)
    /**
     * returns workout with a specific id
     * @param id
     * @return workout
     */
    @GetMapping(path = "/workouts/{id}")
    Workout getWorkoutById( @PathVariable int id){
        return workoutRepository.findById(id);
    }
    @ApiOperation(value = "returns all the workouts ", response = Iterable.class)
    /**
     * returns all the workouts
     * @param id
     * @return List of workouts
     */
    @GetMapping(path = "/user/{id}/workouts")
    List<Workout> getUserWorkouts( @PathVariable int id){
        return userRepository.findById(id).getWorkouts();
    }
    @ApiOperation(value = "creates empty workout ", response = Iterable.class)
    /**
     * creates empty workout
     * @param workout
     * @return success if worked
     */
    @PostMapping(path = "/workouts")
    String createWorkout(@RequestBody Workout workout){
        if (workout == null)
            return failure;
        workoutRepository.save(workout);
        return success;
    }

    @PostMapping(path = "user/workouts/{id}/{wid}")
    User createWorkout(@PathVariable int id, @PathVariable int wid){
        Workout workout = workoutRepository.findById(wid);
        User user = userRepository.findById(id);
        user.addWorkout(workout);
        workout.addUser(user);
        workoutRepository.save(workout);
        userRepository.save(user);
        return user;
    }

    @ApiOperation(value = "creates workouts based on user preferences ", response = Iterable.class)
/**
 * creates workouts based on user preferences
 * @param id
 * @param p
 * @return success line
 */
//    @PostMapping(path = "/users/{id}/workouts/{p}")
//    String createNWorkout(@PathVariable int id, @PathVariable String p){
//        if (p.equals("loseWeight")){
//            Workout newWorkout = new Workout("Running","",p);
//            workoutRepository.save(newWorkout);
//            userRepository.findById(id).addWorkout(newWorkout);
//            Workout newWorkout1 = new Workout("Jump Rope","",p);
//            workoutRepository.save(newWorkout1);
//            userRepository.findById(id).addWorkout(newWorkout1);
//            Workout newWorkout2 = new Workout("Swimming","",p);
//            workoutRepository.save(newWorkout2);
//            User Nuser = userRepository.findById(id);
//            Nuser.addWorkout(newWorkout);
//        } else if (p.equals("gainWeight")) {
//            Workout newWorkout = new Workout("RestanceTraining","",p);
//            workoutRepository.save(newWorkout);
//            User Nuser = userRepository.findById(id);
//            Nuser.addWorkout(newWorkout);
//            Workout newWorkout1 = new Workout("Heavy Weight Training","",p);
//            workoutRepository.save(newWorkout1);
//            Nuser.addWorkout(newWorkout1);
//            userRepository.save(Nuser);
//        } else {
//            Workout newWorkout = new Workout("Soccer","",p);
//            workoutRepository.save(newWorkout);
//            User Nuser = userRepository.findById(id);
//            Nuser.addWorkout(newWorkout);
//        }
//        return success;
//    }


    @PutMapping("/workouts/{id}")
    Workout updatePhone(@PathVariable int id, @RequestBody Workout request) {
        Workout phone = workoutRepository.findById(id);
        if (phone == null)
            return null;
        workoutRepository.save(request);
        return workoutRepository.findById(id);
    }





}
