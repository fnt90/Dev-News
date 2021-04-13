package se.sdaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.sdaproject.model.Reaction;
import java.util.List;

@Repository
public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    List<Reaction> findByArticleId(Long articleId);
    List<Reaction> findByCommentId(Long commentId);
}
