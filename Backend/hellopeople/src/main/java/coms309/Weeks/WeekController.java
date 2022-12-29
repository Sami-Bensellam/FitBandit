package coms309.Weeks;

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
public class WeekController {

    @Autowired
    WeekRepository weekRepository;

    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";

    @GetMapping(path = "/weeks")
    List<Week> getAllWeeks(){
        return weekRepository.findAll();
    }

    @GetMapping(path = "/weeks/{id}")
    Week getWeekById(@PathVariable int id){
        return weekRepository.findById(id);
    }

    @PostMapping(path = "/weeks")
    String createWeek(@RequestBody Week week){
        if (week == null)
            return failure;
        weekRepository.save(week);
        return success;
    }

    @PutMapping(path = "/weeks/{id}")
    Week updateWeek(@PathVariable int id, @RequestBody Week request){
        Week week = weekRepository.findById(id);
        if(week == null)
            return null;
        weekRepository.save(request);
        return weekRepository.findById(id);
    }

    @DeleteMapping(path = "/weeks/{id}")
    String deletePremium(@PathVariable int id) {
        weekRepository.deleteById(id);
        return success;
    }
}

