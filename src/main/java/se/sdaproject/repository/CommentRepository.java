package se.sdaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.sdaproject.model.Comment;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByAuthorName(String authorName);
    List<Comment> findByArticleId(Long articleID);
}
