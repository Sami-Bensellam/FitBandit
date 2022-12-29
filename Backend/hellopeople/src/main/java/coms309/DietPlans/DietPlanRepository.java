package coms309.DietPlans;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Sami Bensellam
 *
 */

public interface DietPlanRepository extends JpaRepository<DietPlan, Long>{
    DietPlan findById(int id);

}
