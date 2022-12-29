package coms309.Workouts;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Sami Bensellam
 *
 */

public interface WorkoutRepository extends JpaRepository<Workout, Long>{
    Workout findById(int id);

}
