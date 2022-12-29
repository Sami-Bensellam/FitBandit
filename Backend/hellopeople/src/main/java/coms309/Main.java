package coms309;

import java.io.Console;
import java.util.Date;

import coms309.DietPlans.DietPlanRepository;
import coms309.Threads.Thread;
import coms309.Threads.ThreadRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import coms309.Workouts.WorkoutRepository;
import coms309.Workouts.Workout;
import coms309.Users.User;
import coms309.Users.UserRepository;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner initUser(UserRepository userRepository, WorkoutRepository workoutRepository, ThreadRepository threadRepository) {
        return args -> {
            User user1 = new User("Admin", "User", "john@somemail.com", true);
            userRepository.save(user1);
            Thread thread1 = new Thread("Dieting chat", "Rules:\n This is strictly about diet not misinformation allowed.",user1);
            Thread thread2 = new Thread("Weightloss chat","Rules:\n This is strictly about diet not misinformation allowed.",user1);
            Thread thread3 = new Thread("Weightdain chat","Rules:\n This is strictly about diet not misinformation allowed.",user1);
            Thread thread4 = new Thread("Offtopic","Rules: \n its the wild west here go crazy",user1);
            threadRepository.save(thread1);
            threadRepository.save(thread2);
            threadRepository.save(thread3);
            threadRepository.save(thread4);
            user1.addThread(thread1);
            user1.addThread(thread2);
            user1.addThread(thread3);
            user1.addThread(thread4);


//            User user2 = new User("Micheal", "Myers", "idk@test.com", false);
//            User user3 = new User("Serial", "Killer", "idk@test2.com", false);
//
            workoutRepository.save(new Workout("Aerobics",211,"cardio"));
            workoutRepository.save(new Workout("Stationary Bike",176,"cardio"));
            workoutRepository.save(new Workout("Stationary Bike medium effort",247,"cardio"));
            workoutRepository.save(new Workout("dusting",70,"cardio"));
            workoutRepository.save(new Workout("gardening",176,"cardio"));
            workoutRepository.save(new Workout("grocery shopping",106,"cardio"));
            workoutRepository.save(new Workout("hiking",211,"cardio"));
            workoutRepository.save(new Workout("house cleaning",106,"cardio"));
            workoutRepository.save(new Workout("jogging",247,"cardio"));
            workoutRepository.save(new Workout("running 12 minute miles",282,"cardio"));
            workoutRepository.save(new Workout("running 10 minute miles",352,"cardio"));
            workoutRepository.save(new Workout("running 7.5 minute miles",428,"cardio"));
            workoutRepository.save(new Workout("laundry, including folding clothes",70,"cardio"));
            workoutRepository.save(new Workout("mowing the lawn",141,"cardio"));
            workoutRepository.save(new Workout("brisk walking",141,"cardio"));
            workoutRepository.save(new Workout("weightlifting",106,"resistance Training"));
            workoutRepository.save(new Workout("yoga",141,"cardio"));

//
//
//            user1.addWorkout(workoutRepository.findById(1));
//            user1.addWorkout(workoutRepository.findById(2));
//            user2.addWorkout(workoutRepository.findById(3));
//            user2.addWorkout(workoutRepository.findById(4));
////
            userRepository.save(user1);
//            userRepository.save(user2);
//            userRepository.save(user3);





        };
   }
    @Bean
    public Docket myDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                //.apiInfo(apiInfo())
                .select()
                //.apis(RequestHandlerSelectors.basePackage("CyAuction"))
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }


}

