package coms309.Replies;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Sami Bensellam
 *
 */

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    Reply findById(int id);
    void deleteById(int id);
}
