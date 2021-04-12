package se.sdaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.sdaproject.model.Topic;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findByArticlesId(Long articlesID);
}
