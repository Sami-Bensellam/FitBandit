package coms309.Threads;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Sami Bensellam
 *
 */

public interface ThreadRepository extends JpaRepository<Thread, Long> {
    Thread findById(int id);
    void deleteById(int id);
}
