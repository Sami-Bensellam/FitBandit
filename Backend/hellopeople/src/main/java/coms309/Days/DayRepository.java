package coms309.Days;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Sami Bensellam
 *
 */

public interface DayRepository extends JpaRepository<Day, Long> {
    Day findById(int id);
    void deleteById(int id);
}
