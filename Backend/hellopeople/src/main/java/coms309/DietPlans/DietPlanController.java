package coms309.DietPlans;

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
import coms309.Users.User;

/**
 *
 * @author Sami Bensellam
 *
 */

@RestController
//@RequestMapping("/api/v1")
public class DietPlanController {
    @Autowired
    DietPlanRepository dietPlanRepository;

    @Autowired
    UserRepository userRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";
    @ApiOperation(value = "returns all dietplans ", response = Iterable.class)
    /**
     * returns all dietplans
     * @return DietPlan List
     */
    @GetMapping(path = "/diets")
    List<DietPlan> getAllDiets(){
        return dietPlanRepository.findAll();
    }
    @ApiOperation(value = "gets a specific diet from the diets table ", response = Iterable.class)
    /**
     * gets a specific diet from the diets table
     * @param id
     * @return DietPlan
     */
    @GetMapping(path = "/diets/{id}")
    DietPlan getDietPlanById( @PathVariable int id){
        return dietPlanRepository.findById(id);
    }
    @ApiOperation(value = "gets Dietplan of user ", response = Iterable.class)
    /**
     * gets Dietplan of user
     * @param id
     * @return returns DietPlan
     */
    @GetMapping(path = "/diets/{id}/diets")
    DietPlan getUserDiets( @PathVariable int id){
        return userRepository.findById(id).getDietPlan();
    }
    @ApiOperation(value = "creates empty diet ", response = Iterable.class)
    /**
     * creates empty diet
     * @param diet
     * @return success if successful
     */
    @PostMapping(path = "/diets")
    String createDiet(DietPlan diet){
        if (diet == null)
            return failure;
        dietPlanRepository.save(diet);
        return success;
    }

//    @PostMapping(path = "/users/{id}/diets/{p}")
//    String createDietPlan(@PathVariable int id, @PathVariable String p){
//        User thisUser1 = userRepository.findById(id);
//        if (p == "loseWeight"){
//            DietPlan dietLose = new DietPlan(1800, 80, 50, 30,2, thisUser1);
//            dietPlanRepository.save(dietLose);
//            userRepository.findById(id).addDiets(dietLose);
//        } else if (p == "gainWeight") {
//            DietPlan dietGain = new DietPlan(2300, 150, 80, 50, 2, thisUser1);
//            dietPlanRepository.save(dietGain);
//            userRepository.findById(id).addDiets(dietGain);
//        } else {
//            DietPlan dietMaintain = new DietPlan(2000, 100, 60,40, 2, thisUser1);
//            dietPlanRepository.save(dietMaintain);
//            userRepository.findById(id).addDiets(dietMaintain);
//        }
//        return success;
//    }
@ApiOperation(value = "changes dietplan by id ", response = Iterable.class)
    /**
     * changes Dietplan by id
     * @param id
     * @param request
     * @return returns the changed dietplan
     */
    @PutMapping("/diets/{id}")
    DietPlan updateDiet(@PathVariable int id, @RequestBody DietPlan request) {
        DietPlan dietChange = dietPlanRepository.findById(id);
        if (dietChange == null)
            return null;
        dietPlanRepository.save(request);
        return dietPlanRepository.findById(id);
    }
    /**
     *creates dietplan based off of user information
     * @param id
     * @return newly created dietplan
     */
    @ApiOperation(value = "creates dietplan based off of user information ", response = Iterable.class)
    @PostMapping(path = "/users/{id}/dietcreate")
    DietPlan createDietPlan(@PathVariable int id){

//        HEIGHT IN INCHES
//        WEIGHT IN LBS
//        AGE IN YEARS
//        GENDER TRUE = MALE
//        GENDER FALSE = FEMALE

        User thisuser = userRepository.findById(id);
        int weight = thisuser.getWeight();
        int height = thisuser.getHeight();
        int age = thisuser.getAge();
        int gain = thisuser.getGain();
        boolean gender = thisuser.getGender();
        int baseCalories = 0;
        int targetCalories = 0;
        int carbs = 0;
        int fat = 0;
        int protein = 0;

        if(gender){
            baseCalories = (int)(66 + (6.2 * weight) + (12.7 * height) - (6.76 * age));
        }
        else{
            baseCalories = (int)(655.1 + (4.35 * weight) + (4.7 * height) - (4.7 * age));
        }

        if(gain == 0){
            targetCalories = (int)(baseCalories * 1- 400);
        }
        else if(gain == -1){
            targetCalories = (int)(baseCalories * 1);
        }
        else if(gain == 1){
            targetCalories = (int)(baseCalories * 1 + 400);
        }

//        CARBS IN GRAMS
//        FAT IN GRAMS
//        PROTEIN IN GRAMS

        if(gain == 1){
            carbs = (int)((targetCalories * 0.4)/4);
            fat = (int)((targetCalories * 0.35)/8);
            protein = (int)((targetCalories * 0.25)/4);
        }

        else if(gain == 0){
            carbs = (int)((targetCalories * 0.4)/4);
            fat = (int)((targetCalories * 0.3)/8);
            protein = (int)((targetCalories * 0.30)/4);
        }

        else if(gain == -1){
            carbs = (int)((targetCalories * 0.3)/4);
            fat = (int)((targetCalories * 0.25)/8);
            protein = (int)((targetCalories * 0.45)/4);
        }
        int waterIntake = (int)((weight/2) * 0.0295735296); //LITRES
        DietPlan newPlan = new DietPlan(targetCalories, protein, carbs, fat, waterIntake, thisuser);
        dietPlanRepository.save(newPlan);
        thisuser.setDietPlan(newPlan);
        userRepository.save(thisuser);
        return newPlan;
    }







}
