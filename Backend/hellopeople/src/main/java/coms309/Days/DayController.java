package coms309.Days;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
public class DayController {

    @Autowired
    DayRepository dayRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/days")
    List<Day> getAllDays(){
        return dayRepository.findAll();
    }

    @GetMapping(path = "/days/{id}")
    Day getWeekById(@PathVariable int id){
        return dayRepository.findById(id);
    }

    @PostMapping(path = "/days")
    String createWeek(@RequestBody Day day){
        if (day == null)
            return failure;
        dayRepository.save(day);
        return success;
    }

    @PutMapping(path = "/days/{id}")
    Day updateDay(@PathVariable int id, @RequestBody Day request){
        Day day = dayRepository.findById(id);
        if(day == null)
            return null;
        dayRepository.save(request);
        return dayRepository.findById(id);
    }

    @DeleteMapping(path = "/days/{id}")
    String deleteDay(@PathVariable int id) {
        dayRepository.deleteById(id);
        return success;
    }
}
