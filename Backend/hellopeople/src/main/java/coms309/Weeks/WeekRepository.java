package coms309.Weeks;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Sami Bensellam
 *
 */

public interface WeekRepository extends JpaRepository<Week, Long> {
    Week findById(int id);
    void deleteById(int id);
}
