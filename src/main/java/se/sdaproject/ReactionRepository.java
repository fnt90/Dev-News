package se.sdaproject;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    List<Reaction> findByArticleId(Long articleId);
    List<Reaction> findByCommentId(Long commentId);
}
